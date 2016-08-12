package com.gmail.liliyayalovchenko.Controllers;

import com.gmail.liliyayalovchenko.DAO.*;
import com.gmail.liliyayalovchenko.Domains.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdministratorDAO administratorDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private InformationDAO informationDAO;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private FeedBackDAO feedBackDAO;

    @RequestMapping("/")
    public ModelAndView main(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
            if (checkStatus(session)) {
                modelAndView.setViewName("adminIndex");
                modelAndView.addObject("orders", orderDAO.getOrders());

            } else {
                modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping("/parameters")
    public ModelAndView parameters(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
            if (checkStatus(session)) {
                modelAndView.setViewName("adminParameters");
                modelAndView.addObject("admins", administratorDAO.getAllAdmins());
                modelAndView.addObject("adminsSize", administratorDAO.getAllAdmins().size());
            } else {
                modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping("/parameters/add")
    public ModelAndView addAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
            if (checkStatus(session)) {
                modelAndView.setViewName("addAdmin");
           } else {
                modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value="/edit/{role}")
    public ModelAndView editAdmin(@PathVariable("role") String role,
                                  HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
            if (checkStatus(session)) {
                modelAndView.setViewName("editAdmin");
                modelAndView.addObject("admin", administratorDAO.getAdminByRole(role));
           } else {
                modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value="/parameters/save-new-user",  method = RequestMethod.POST)
    public ModelAndView saveAdmin(@RequestParam(value="role") String role,
                                  @RequestParam(value="username") String username,
                                  @RequestParam(value = "domainName") String domainName,
                                  @RequestParam(value = "password") String password,
                                  @RequestParam(value = "emailAddress") String emailAddress,
                                  @RequestParam(value = "phoneNumber1") String phoneNumber1,
                                  @RequestParam(value = "phoneNumber2") String phoneNumber2,
                                  HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
            if (checkStatus(session)) {
                modelAndView.setViewName("adminParameters");
                Administrator administrator = new Administrator(role, password, username, domainName, emailAddress, phoneNumber1, phoneNumber2);
                administratorDAO.saveAdmin(administrator);
            } else {
                modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value="/parameters",  method = RequestMethod.POST)
    public ModelAndView saveEditedAdmin  (@RequestParam(value="id") int id,
                                          @RequestParam(value="role") String role,
                                          @RequestParam(value="username") String username,
                                          @RequestParam(value = "domainName") String domainName,
                                          @RequestParam(value = "password") String password,
                                          @RequestParam(value = "emailAddress") String emailAddress,
                                          @RequestParam(value = "phoneNumber1") String phoneNumber1,
                                          @RequestParam(value = "phoneNumber2") String phoneNumber2,
                                          HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
            if (checkStatus(session)) {
                modelAndView.setViewName("adminParameters");
                modelAndView.addObject("admins", administratorDAO.getAllAdmins());
                administratorDAO.saveAdmin(id, role,  password, username, domainName, emailAddress, phoneNumber1, phoneNumber2);
            } else {
                modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView adminAccess (@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if((username.equals(administratorDAO.getAdminUsername("admin")))&&(password.equals(administratorDAO.getAdminPassword("admin")))) {
            session.setAttribute("status", "admin");
            modelAndView.setViewName("adminIndex");
            modelAndView.addObject("orders", orderDAO.getOrders());
            return modelAndView;
        } else {
            modelAndView.setViewName("adminLogin");
            modelAndView.addObject("notification", "Неверный логин или пароль");
            return modelAndView;
        }
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)){
            session.removeAttribute("status");
            modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
            modelAndView.setViewName("index");
        }
        else {
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }



    @RequestMapping("/catalog") /**добавление категорий и товаров, удаление**/
    public ModelAndView ProductCatalog(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("adminCatalog");
            modelAndView.addObject("products", productDAO.getAllProducts());
            modelAndView.addObject("categories", categoryDAO.getAllCategories());

        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping("/articles") /**добавление статтей и новостей, удаление и редактирование**/
    public ModelAndView adminArticles(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("adminArticles");
            modelAndView.addObject("articles", informationDAO.getAllArticles());
            } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value="/articles/edit", method = RequestMethod.GET)
    public ModelAndView adminArticlesEdit(@RequestParam(value="id") int id,
                                          HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("articleEdit");
            modelAndView.addObject("article", informationDAO.getAllArticles(id));
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(informationDAO.getAllArticles(id).getDateOfPublication());
            modelAndView.addObject("date", date);
            } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/articles/save", method = RequestMethod.POST)
    public  ModelAndView saveArticle(@RequestParam(value="id") int id,
                                     @RequestParam(value="title") String title,
                                     @RequestParam(value="imagePath") String imagePath,
                                     @RequestParam(value="shortDescription") String shortDescription,
                                     @RequestParam(value="dateOfPublication") String dateOfPublication,
                                     @RequestParam(value="buttonText") String buttonText,
                                     @RequestParam(value="content") String content,
                                     @RequestParam(value="metaDescription") String metaDescription,
                                     @RequestParam(value="metaKeyWords") String metaKeyWords,
                                     @RequestParam(value="metaTitle") String metaTitle,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");

            Date date = null;
            try {
                date = parser.parse(dateOfPublication);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Ошибка в преобразованнии даты при сохранении статьи!");
            }
            informationDAO.changeArticle(id, title, imagePath, shortDescription, date, buttonText, content, metaDescription, metaKeyWords, metaTitle);
            modelAndView.setViewName("adminArticles");
            modelAndView.addObject("articles", informationDAO.getAllArticles());
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return  modelAndView;
    }

    @RequestMapping(value = "/articles/delete", method = RequestMethod.GET)
    public ModelAndView deleteArticle(@RequestParam(value = "id") int id,
                                      HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            informationDAO.deleteArticle(id);
            modelAndView.addObject("articles", informationDAO.getAllArticles());
            modelAndView.setViewName("adminArticles");
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/articles/add")
    public ModelAndView addArticle (HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("addArticle");
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value="/articles/save-new-article", method = RequestMethod.POST) /**Сохранение статьи**/
    public ModelAndView saveNewArticle(@RequestParam(value="title") String title,
                                   @RequestParam(value="imagePath") String imagePath,
                                   @RequestParam(value="shortDescription") String shortDescription,
                                   @RequestParam(value="dateOfPublication") String dateOfPublication,
                                   @RequestParam(value="buttonText") String buttonText,
                                   @RequestParam(value="content") String content,
                                   @RequestParam(value="metaDescription") String metaDescription,
                                   @RequestParam(value="metaKeyWords") String metaKeyWords,
                                   @RequestParam(value="metaTitle") String metaTitle,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");

            Date date = null;
            try {
                date = parser.parse(dateOfPublication);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Ошибка в преобразованнии даты при сохранении статьи!");
            }
            Information infoToAdd = new Information(title, imagePath, shortDescription, date, buttonText, content, metaDescription, metaKeyWords, metaTitle);
            informationDAO.addArticle(infoToAdd);
            modelAndView.setViewName("adminArticles");
            modelAndView.addObject("articles", informationDAO.getAllArticles());

        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/category/{name}")
    public ModelAndView categoryCatalog(@PathVariable("name") String name,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("categoryAdmin");
            modelAndView.addObject("category", categoryDAO.getCategoryByName(name).getName());
            modelAndView.addObject("products", productDAO.getProductsByCategory(name));
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/category/edit/{name}")
    public ModelAndView categoryEdit(@PathVariable("name") String name,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("categoryEdit");
            modelAndView.addObject("category", categoryDAO.getCategoryByName(name));
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/category/save")
    public ModelAndView categorySave(@RequestParam(value="id") int id,
                                     @RequestParam(value="name") String name,
                                     @RequestParam(value="info") String info,
                                     @RequestParam(value="metaDescription") String metaDescription,
                                     @RequestParam(value="metaKeyWords") String metaKeyWords,
                                     @RequestParam(value="metaTitle") String metaTitle,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            categoryDAO.saveCategory(id, name, info, metaDescription, metaKeyWords, metaTitle);
            modelAndView.setViewName("adminCatalog");
            modelAndView.addObject("products", productDAO.getAllProducts());
            modelAndView.addObject("categories", categoryDAO.getAllCategories());
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }



    @RequestMapping(value = "/product/edit",  method = RequestMethod.GET)
    public ModelAndView productEdit(@RequestParam(value="id") int id,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("productEdit");
            modelAndView.addObject("product", productDAO.getProductById(id));
            modelAndView.addObject("categories", categoryDAO.getAllCategories());
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/product/save", method = RequestMethod.POST)
    public  ModelAndView saveProduct(@RequestParam(value="id") int id,
                                     @RequestParam(value="name") String name,
                                     @RequestParam(value="price") int price,
                                     @RequestParam(value="currency") String currency,
                                     @RequestParam(value="productCategory") String productcategory,
                                     @RequestParam(value="amount") int amount,
                                     @RequestParam(value="inStock") String inStock,
                                     @RequestParam(value="description") String description,
                                     @RequestParam(value="shortDesc") String shortDesc,
                                     @RequestParam(value="metaDescription") String metaDescription,
                                     @RequestParam(value="metaKeyWords") String metaKeyWords,
                                     @RequestParam(value="metaTitle") String metaTitle,
                                     @RequestParam(value="smallimage") String smallimage,
                                     @RequestParam(value="smallimage1") String smallimage1,
                                     @RequestParam(value="image1") String image1,
                                     @RequestParam(value="image2") String image2,
                                     @RequestParam(value="image3") String image3,
                                     @RequestParam(value="image4") String image4,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            Category category = categoryDAO.getCategoryByName(productcategory);
            productDAO.saveProduct(id, name, price, currency, category, amount, inStock, description, shortDesc, metaDescription, metaKeyWords, metaTitle,
                    smallimage, smallimage1, image1, image2, image3, image4);
            modelAndView.setViewName("adminCatalog");
            modelAndView.addObject("categories", categoryDAO.getAllCategories());
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return  modelAndView;
    }

    @RequestMapping("/catalog/addPage") /**добавление категорий и товаров, удаление**/
    public ModelAndView addPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("addPage");
            modelAndView.addObject("categories", categoryDAO.getAllCategories());
            } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }
    @RequestMapping(value="/catalog/addProduct", method = RequestMethod.POST) /**Сохранение товра**/
    public ModelAndView addProduct(@RequestParam(value="name") String name,
                                   @RequestParam(value="price") int price,
                                   @RequestParam(value="currency") String currency,
                                   @RequestParam(value="productCategory") String productcategory,
                                   @RequestParam(value="amount") int amount,
                                   @RequestParam(value="inStock") String inStock,
                                   @RequestParam(value="description") String description,
                                   @RequestParam(value="shortDesc") String shortDesc,
                                   @RequestParam(value="metaDescription") String metaDescription,
                                   @RequestParam(value="metaKeyWords") String metaKeyWords,
                                   @RequestParam(value="metaTitle") String metaTitle,
                                   @RequestParam(value="smallimage") String smallimage,
                                   @RequestParam(value="smallimage1") String smallimage1,
                                   @RequestParam(value="image1") String image1,
                                   @RequestParam(value="image2") String image2,
                                   @RequestParam(value="image3") String image3,
                                   @RequestParam(value="image4") String image4,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            Category productCategory = categoryDAO.getCategoryByName(productcategory);
            Product product = new Product(name, price, currency, productCategory, amount, inStock, description,  shortDesc, metaDescription, metaKeyWords, metaTitle,
                    smallimage, smallimage1, image1, image2, image3, image4);
            productDAO.saveProduct(product);
            modelAndView.setViewName("addPage");
            modelAndView.addObject("categories", categoryDAO.getAllCategories());

        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value="/catalog/addCategory", method = RequestMethod.POST) /**Сохранение ктегории**/
    public ModelAndView addCategory (@RequestParam(value="name") String name,
                                     @RequestParam(value="info") String info,
                                     @RequestParam(value="metaDescription") String metaDescription,
                                     @RequestParam(value="metaKeyWords") String metaKeyWords,
                                     @RequestParam(value="metaTitle") String metaTitle,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            Category category = new Category(name, info, metaDescription, metaKeyWords, metaTitle);
            categoryDAO.saveCategory(category);
            modelAndView.setViewName("addPage");

        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }
    @RequestMapping(value = "/orders/edit", method = RequestMethod.GET)
    public ModelAndView editOrder (@RequestParam(value="id") int id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.addObject("order", orderDAO.getOrder(id));
            modelAndView.setViewName("editOrder");
        } else {
            modelAndView.setViewName("adminLogin");
          }
        return modelAndView;
    }

    @RequestMapping(value = "/orders/delete", method = RequestMethod.GET)
    public ModelAndView deleteOrder (@RequestParam(value="id") int id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            orderDAO.deleteOrder(id);
            modelAndView.addObject("orders", orderDAO.getOrders());
            modelAndView.setViewName("adminIndex");
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/orders/save", method = RequestMethod.POST)
    public ModelAndView saveOrder (@RequestParam(value="id") int id,
                                   @RequestParam(value="delivery") String delivery,
                                   @RequestParam(value="comments") String comments,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            orderDAO.saveOrder(id, delivery, comments);
            modelAndView.addObject("orders", orderDAO.getOrders());
            modelAndView.setViewName("adminIndex");
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }
    @RequestMapping("/clients")
    public ModelAndView clientsPage (HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("adminClients");
            modelAndView.addObject("clients", clientDAO.getClients());
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/clients/edit", method = RequestMethod.GET)
    public ModelAndView clientEdit (@RequestParam(value="id") int id,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("clientEdit");
            modelAndView.addObject("client", clientDAO.getClient(id));
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/clients/save", method = RequestMethod.POST)
    public ModelAndView clientSave (@RequestParam(value="id") int id,
                                    @RequestParam(value="firstName") String firstName,
                                    @RequestParam(value="phoneNumber") String phoneNumber,
                                    @RequestParam(value="email") String email,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            Client client = new Client(firstName, phoneNumber, email);
            clientDAO.saveClient(client, id);
            modelAndView.setViewName("adminClients");
            modelAndView.addObject("clients", clientDAO.getClients());
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return  modelAndView;
    }

    @RequestMapping(value = "/clients/add")
    public ModelAndView clientAdd (HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("addClient");

        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/clients/save-new-client", method = RequestMethod.POST)
    public ModelAndView saveNewClient (@RequestParam(value="firstName") String firstName,
                                       @RequestParam(value="phoneNumber") String phoneNumber,
                                       @RequestParam(value="email") String email,
                                       HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            Client client = new Client (firstName, phoneNumber, email);
            clientDAO.addClient(client);
            modelAndView.setViewName("adminClients");
            modelAndView.addObject("clients", clientDAO.getClients());

        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/feedbacks")
    public ModelAndView adminFeedbacks (HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {

            modelAndView.setViewName("adminFeedbacks");
            modelAndView.addObject("feedbacks", feedBackDAO.getAllFeedBacks());

        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/feedbacks/byClientId", method = RequestMethod.POST)
    public ModelAndView adminFeedbacksByClient (@RequestParam(value="id") int id,
                                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("adminFeedbacks");
            modelAndView.addObject("feedbacks", feedBackDAO.getFeedBacksByClientId(id));

        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/feedbacks/byProductId", method = RequestMethod.POST)
    public ModelAndView adminFeedbacksByProduct (@RequestParam(value="id") int id,
                                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("adminFeedbacks");
            modelAndView.addObject("feedbacks", feedBackDAO.getFeedBacksByProductId(id));

        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/feedbacks/edit", method = RequestMethod.GET)
    public ModelAndView feedbackEdit (@RequestParam(value="id") int id,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("feedbackEdit");
            modelAndView.addObject("feedback", feedBackDAO.getFeedBackById(id));
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/feedbacks/save", method = RequestMethod.POST)
    public ModelAndView feedbackSave (  @RequestParam(value="id") int id,
                                        @RequestParam(value="product") int productId,
                                        @RequestParam(value="data") Date data,
                                        @RequestParam(value = "client") int clientId,
                                        @RequestParam(value="evaluation") int evaluation,
                                        @RequestParam(value="feedback") String feedback,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            Client client = clientDAO.getClient(clientId);
            Product product = productDAO.getProductById(productId);
            FeedBack feedBack = new FeedBack(product, data, client, evaluation, feedback);
            feedBackDAO.saveFeedBack(feedBack, id);
            modelAndView.setViewName("adminFeedbacks");
            modelAndView.addObject("feedbacks", feedBackDAO.getAllFeedBacks());
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return  modelAndView;
    }

    @RequestMapping(value = "/feedbacks/add")
    public ModelAndView addFeedBack(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("addFeedBack");
            modelAndView.addObject("clients", clientDAO.getClients());
            modelAndView.addObject("products", productDAO.getAllProducts());
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/feedbacks/saveNewFeedback", method = RequestMethod.POST)
    public ModelAndView saveNewClient (@RequestParam(value="product") int id,
                                       @RequestParam(value = "client") int idclient,
                                       @RequestParam(value="evaluation") int evaluation,
                                       @RequestParam(value="feedback") String feedback,
                                       HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            Product resultproduct = productDAO.getProductById(id);
            Client resultclient = clientDAO.getClient(idclient);
            FeedBack feedBack = new FeedBack(resultproduct, new Date(), resultclient, evaluation, feedback);
            feedBackDAO.saveFeedBack(feedBack);
            modelAndView.setViewName("adminFeedbacks");
            modelAndView.addObject("feedbacks", feedBackDAO.getAllFeedBacks());
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }



    public boolean checkStatus(HttpSession session){
        boolean checking;
        String status = (String)session.getAttribute("status");
        if (status=="admin") checking = true;
        else checking=false;
        return checking;
    }



}
