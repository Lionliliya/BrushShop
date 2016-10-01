package com.gmail.liliyayalovchenko.Controllers;

import com.gmail.liliyayalovchenko.DAO.*;
import com.gmail.liliyayalovchenko.Domains.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private ProductInCartDAO productInCartDAO;

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
            modelAndView.addObject("clients", clientDAO.getClients());
            modelAndView.addObject("products", productDAO.getAllProducts());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping("/parameters/add")
    public ModelAndView addAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("addAdmin");
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            administratorDAO.saveAdmin(id, role, password, username, domainName, emailAddress, phoneNumber1, phoneNumber2);
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView adminAccess (@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password,
                                     ModelMap model,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if((username.equals(administratorDAO.getAdminUsername("admin")))&&(password.equals(administratorDAO.getAdminPassword("admin")))) {
            session.setAttribute("status", "admin");
            return new ModelAndView("redirect:/admin/", model);
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
            return modelAndView;
        }

        modelAndView.setViewName("index");
        return modelAndView;
    }



//    @RequestMapping("/catalog") /**добавление категорий и товаров, удаление**/
//    public ModelAndView ProductCatalog(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        ModelAndView modelAndView = new ModelAndView();
//
//        if (checkStatus(session)) {
//            modelAndView.setViewName("adminCatalog");
//            modelAndView.addObject("products", productDAO.getAllProducts());
//            modelAndView.addObject("categories", categoryDAO.getAllCategories());
//            return  modelAndView;
//        }
//
//        modelAndView.setViewName("adminLogin");
//        return modelAndView;
//    }

    @RequestMapping("/articles") /**добавление статтей и новостей, удаление и редактирование**/
    public ModelAndView adminArticles(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminArticles");
            modelAndView.addObject("articles", informationDAO.getAllArticles());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return  modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return  modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/articles/add")
    public ModelAndView addArticle (HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("addArticle");
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return  modelAndView;
    }

    @RequestMapping("/catalog") /**добавление категорий и товаров, удаление**/
    public ModelAndView addPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminCatalog");
            modelAndView.addObject("categories", categoryDAO.getAllCategories());
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/catalog/{name}")
    public ModelAndView categoryEdit(@PathVariable("name") String name,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminCategory");
            modelAndView.addObject("category", categoryDAO.getCategoryByName(name));
            modelAndView.addObject("products", productDAO.getProductsByCategory(name));
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
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
            return modelAndView;

        }

        modelAndView.setViewName("adminLogin");
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
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }


    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    public ModelAndView addOrder(@RequestParam(value="date") String date,
                                 @RequestParam(value="delivery") String delivery,
                                 @RequestParam(value="comments") String comments,
                                 @RequestParam(value = "currency") String currency,
                                 @RequestParam(value = "client") int client,
                                 @RequestParam(value = "quantity") int quantity,
                                 ModelMap model,
                                 HttpServletRequest request) throws ParseException {

        HttpSession session = request.getSession();

        if (checkStatus(session)) {

            List<String> productsId = Arrays.asList(request.getParameterValues("product"));
            List<ProductInCart> productInCarts = new ArrayList<>();

            for (String s : productsId) {
                Product product = productDAO.getProductById(Integer.valueOf(s));
                ProductInCart productInCart = new ProductInCart(product, product.getProductCategory().getName(),
                        product.getSmallimage(), product.getName(), product.getPrice(), currency, quantity);
                productInCarts.add(productInCart);
            }

            int amount = 0;

            for (ProductInCart productInCart : productInCarts) {
                amount += productInCart.getPrice()*productInCart.getQuantity();
            }

            Date orderDate = new SimpleDateFormat("dd.MM.yyyy hh:mm").parse(date);
            Order order = new Order();
            order.setDate(orderDate);
            order.setDelivery(delivery);
            order.setComments(comments);
            order.setTotalAmount(amount);
            order.setClient(clientDAO.getClient(client));

            for (ProductInCart productInCart : productInCarts) {
                productInCart.setOrder(order);
            }
            orderDAO.saveOrder(order);
            productInCartDAO.saveProductInCart(productInCarts);
            return new ModelAndView("redirect:/admin/", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/order/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editOrder (@PathVariable int id,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            Order order = orderDAO.getOrder(id);
            modelAndView.addObject("order", order);
            modelAndView.addObject("products", productDAO.getAllProducts());
            session.setAttribute("productList", productInCartDAO.getProductsInCart(id));
            modelAndView.setViewName("adminOrderEdit");
        } else {
            modelAndView.setViewName("adminLogin");
          }
        return modelAndView;
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ModelAndView viewOrder (@PathVariable int id,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.addObject("order", orderDAO.getOrder(id));
            modelAndView.addObject("products", productInCartDAO.getProductsInCart(id));
            modelAndView.setViewName("adminOrder");
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/order/remove/{id}", method = RequestMethod.GET)
    public ModelAndView deleteOrder (@PathVariable int id,
                                     ModelMap model,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            orderDAO.deleteOrder(id);
            return new ModelAndView("redirect:/admin/", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/order/removeProduct/{id}", method = RequestMethod.POST)
    public ModelAndView deleteFromOrder(@PathVariable int id,
                                        @RequestParam int delete,
                                        HttpServletRequest request,
                                        ModelMap model) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            ProductInCart product = productInCartDAO.getById(delete);
            orderDAO.updateOrderAmount(id, product, false);
            productInCartDAO.delete(product);
            return new ModelAndView("redirect:/admin/", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/order/addProduct/{id}", method = RequestMethod.POST)
    public ModelAndView addToOrder(@PathVariable int id,
                                   @RequestParam int product,
                                   HttpServletRequest request,
                                   ModelMap model) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {

            Product productById = productDAO.getProductById(product);
            List<String> quantities = Arrays.asList(request.getParameterValues("quantity"));
            int quantity = 1;

            for (String s : quantities) {

                if (!"".equals(s)) {
                    quantity = Integer.valueOf(s);
                }
            }

            ProductInCart productInCart = new ProductInCart(productById, productById.getProductCategory().getName(),
                                    productById.getSmallimage(), productById.getName(), productById.getPrice(),
                                    productById.getCurrency(), quantity);

            productInCart.setOrder(orderDAO.getOrder(id));
            productInCartDAO.saveProductInCart(productInCart);
            orderDAO.updateOrderAmount(id, productInCart, true);
            return new ModelAndView("redirect:/admin/", model);
        }

        return new ModelAndView("adminLogin", model);
    }


    @RequestMapping(value = "/order/save/{id}", method = RequestMethod.POST)
    public ModelAndView saveOrder (@PathVariable int id,
                                   @RequestParam(value="date") String date,
                                   @RequestParam(value = "totalAmount") int totalAmount,
                                   @RequestParam(value="delivery") String delivery,
                                   @RequestParam(value="comments") String comments,
                                   ModelMap model,
                                   HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            Date orderDate = new SimpleDateFormat("dd.MM.yyyy hh:mm").parse(date);
            orderDAO.saveOrder(id, orderDate, totalAmount, delivery, comments);
            return new ModelAndView("redirect:/admin/", model);
        }

        return new ModelAndView("adminLogin", model);

    }

    @RequestMapping(value = "/order/sort/{dateUp}", method = RequestMethod.GET)
    public ModelAndView sortOrder(@PathVariable String dateUp,
                                  ModelMap model,
                                  HttpServletRequest request) {

        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            List<Order> orderList;
            if ("dateUp".equals(dateUp)) {
                orderList =  orderDAO.getSortedByDateUp();
            } else {
                orderList = orderDAO.getSortedByDateDown();
            }
            modelAndView.addObject("orders", orderList);
            modelAndView.setViewName("adminIndex");
            return modelAndView;
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/order/sort/amount", method = RequestMethod.GET)
    public ModelAndView sortOrderByAmount(HttpServletRequest request) {

        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.addObject("orders", orderDAO.getSortedByAmountDown());
            modelAndView.setViewName("adminIndex");
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping("/client")
    public ModelAndView clientsPage (HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminClients");
            modelAndView.addObject("clients", clientDAO.getClients());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping("/client/sort/name")
    public ModelAndView clientSortName (HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminClients");
            modelAndView.addObject("clients", clientDAO.getSortedByName());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping("/client/sort/email")
    public ModelAndView clientSortEmail (HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("adminClients");
            modelAndView.addObject("clients", clientDAO.getSortedByEmail());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public ModelAndView clientView (@PathVariable int id,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session)) {
            modelAndView.setViewName("adminClient");
            modelAndView.addObject("client", clientDAO.getClient(id));
            modelAndView.addObject("feedBacks", feedBackDAO.getFeedBacksByClientId(id));
            modelAndView.addObject("products", productDAO.getAllProducts());
            modelAndView.addObject("orders", orderDAO.getAllOrdersByClient(id));
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/client/remove/{id}", method = RequestMethod.GET)
    public ModelAndView clientRemove (@PathVariable int id,
                                      ModelMap model,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            Client client = clientDAO.getClient(id);
            clientDAO.remove(client);
            return new ModelAndView("redirect:/admin/client", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/client/removeFeedback/{id}", method = RequestMethod.POST)
    public ModelAndView removeFeedBackFromClient(@PathVariable int id,
                                                 HttpServletRequest request,
                                                 ModelMap model) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            String feedBackId = request.getParameter("delete");
            FeedBack feedBack = feedBackDAO.getFeedBackById(Integer.valueOf(feedBackId));
            System.out.println(feedBack.getId() + "id of feedback");
            productDAO.removeFeedBack(feedBack);
            feedBackDAO.delete(feedBack);
            return new ModelAndView("redirect:/admin/client/{id}", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/client/edit/{id}", method = RequestMethod.GET)
    public ModelAndView clientEdit (@PathVariable int id,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminClientEdit");
            Client client = clientDAO.getClient(id);
            modelAndView.addObject("feedBacks", feedBackDAO.getFeedBacksByClientId(id));
            modelAndView.addObject("client", client);
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/clients/save", method = RequestMethod.POST)
    public ModelAndView clientSave (@RequestParam(value="id") int id,
                                    @RequestParam(value="firstName") String firstName,
                                    @RequestParam(value="phoneNumber") String phoneNumber,
                                    @RequestParam(value="email") String email,
                                    ModelMap model,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            clientDAO.saveClient(id, firstName, phoneNumber, email);
            return new ModelAndView("redirect:/admin/client", model);
        }

       return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/client/add", method = RequestMethod.POST)
    public ModelAndView saveNewClient (@RequestParam(value="firstName") String firstName,
                                       @RequestParam(value="phoneNumber") String phoneNumber,
                                       @RequestParam(value="email") String email,
                                       ModelMap model,
                                       HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            Client client = new Client (firstName, phoneNumber, email);
            clientDAO.addClient(client);
            return new ModelAndView("redirect:/admin/client", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/feedback")
    public ModelAndView adminFeedbacks (HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminFeedbacks");
            modelAndView.addObject("feedbacks", feedBackDAO.getAllFeedBacks());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/feedback/sort/dateUp", method = RequestMethod.GET)
    public ModelAndView sortFeedBackDateUp(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminFeedbacks");
            modelAndView.addObject("feedbacks", feedBackDAO.getAllFeedBacksDateUp());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/feedback/sort/dateDown", method = RequestMethod.GET)
    public ModelAndView sortFeedBackDateDown(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminFeedbacks");
            modelAndView.addObject("feedbacks", feedBackDAO.getAllFeedBacksDateDown());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/feedback/sort/rateDown", method = RequestMethod.GET)
    public ModelAndView sortFeedBackRateDown(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminFeedbacks");
            modelAndView.addObject("feedbacks", feedBackDAO.getAllFeedBacksRateDown());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/feedback/sort/rateUp", method = RequestMethod.GET)
    public ModelAndView sortFeedBackRateUp(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminFeedbacks");
            modelAndView.addObject("feedbacks", feedBackDAO.getAllFeedBacksRateUp());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }



    @RequestMapping(value = "/feedback/edit/{id}", method = RequestMethod.GET)
    public ModelAndView feedbackEdit (@PathVariable int id,
                                      HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("feedbackEdit");
            modelAndView.addObject("feedback", feedBackDAO.getFeedBackById(id));
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/feedback/save/{id}", method = RequestMethod.POST)
    public ModelAndView feedbackSave (@PathVariable int id,
                                      @RequestParam String date,
                                      @RequestParam int evaluation,
                                      @RequestParam String feedback,
                                      ModelMap model,
                                      HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {

            feedBackDAO.saveFeedBack(id, new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(date), evaluation, feedback);
            return new ModelAndView("redirect:/admin/feedback", model);
        }

        return new ModelAndView("adminLogin");
    }

    @RequestMapping(value = "/feedback/add/{id}", method = RequestMethod.POST)
    public ModelAndView saveNewFeedBack (@PathVariable int id,
                                         @RequestParam int product,
                                         @RequestParam String date,
                                         @RequestParam int evaluation,
                                         @RequestParam String feedback,
                                         ModelMap model,
                                         HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            Product productById = productDAO.getProductById(product);
            Client client = clientDAO.getClient(id);
            Date dateOfFeedBack = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(date);
            FeedBack feedBack = new FeedBack(productById, dateOfFeedBack, client, evaluation, feedback);
            productById.addFeedBack(feedBack);
            feedBackDAO.saveFeedBack(feedBack);
            productDAO.updateProduct(productById);
            return new ModelAndView("redirect:/admin/client/{id}", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/feedback/remove/{id}", method = RequestMethod.GET)
    public ModelAndView removeNewFeedBack (@PathVariable int id,
                                           ModelMap model,
                                           HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            feedBackDAO.remove(feedBackDAO.getFeedBackById(id));
            return new ModelAndView("redirect:/admin/feedback", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    public boolean checkStatus(HttpSession session){
        return Objects.equals(session.getAttribute("status"), "admin");
    }
}
