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
    private PostDAO postDAO;

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


    @RequestMapping("/parameter")
    public ModelAndView parameters(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminSettings");
            modelAndView.addObject("users", administratorDAO.getAllUsers());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value="/user/{id}")
    public ModelAndView userAdmin(@PathVariable int id,
                                  HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminUser");
            modelAndView.addObject("user", administratorDAO.getAdminById(id));
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value="/user/edit/{id}")
    public ModelAndView editAdmin(@PathVariable int id,
                                  HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminUserEdit");
            modelAndView.addObject("user", administratorDAO.getAdminById(id));
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value="/user/save/{id}",  method = RequestMethod.POST)
    public ModelAndView saveEditedAdmin  (@PathVariable int id,
                                          @RequestParam String role,
                                          @RequestParam String username,
                                          @RequestParam String domainName,
                                          @RequestParam String password,
                                          @RequestParam String emailAddress,
                                          @RequestParam String phoneNumber1,
                                          @RequestParam String phoneNumber2,
                                          ModelMap model,
                                          HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            administratorDAO.saveAdmin(id, role, password, username, domainName, emailAddress, phoneNumber1, phoneNumber2);
            return new ModelAndView("redirect:/admin/user/{id}");
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value="/user/add",  method = RequestMethod.POST)
    public ModelAndView saveAdmin(@RequestParam(value="role") String role,
                                  @RequestParam(value="username") String username,
                                  @RequestParam(value = "domainName") String domainName,
                                  @RequestParam(value = "password") String password,
                                  @RequestParam(value = "emailAddress") String emailAddress,
                                  @RequestParam(value = "phoneNumber1") String phoneNumber1,
                                  @RequestParam(value = "phoneNumber2") String phoneNumber2,
                                  ModelMap model,
                                  HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            Administrator administrator = new Administrator(role, password, username, domainName, emailAddress, phoneNumber1, phoneNumber2);
            administratorDAO.saveAdmin(administrator);
            return new ModelAndView("redirect:/admin/parameter", model);
        }

       return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public ModelAndView allPosts(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminPosts");
            modelAndView.addObject("posts", postDAO.getAllPosts());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/post/sort/dateUp", method = RequestMethod.GET)
    public ModelAndView allPostsDateUp(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminPosts");
            modelAndView.addObject("posts", postDAO.getAllPostAsc());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/post/sort/dateDown", method = RequestMethod.GET)
    public ModelAndView allPostsDateDown(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminPosts");
            modelAndView.addObject("posts", postDAO.getAllPostsDesc());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/post/sort/nameUp", method = RequestMethod.GET)
    public ModelAndView allPostsNameUp(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminPosts");
            modelAndView.addObject("posts", postDAO.getAllPostsNameUp());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/post/sort/nameDown", method = RequestMethod.GET)
    public ModelAndView allPostsNameDown(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminPosts");
            modelAndView.addObject("posts", postDAO.getAllPostsNameDown());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ModelAndView allPosts(@PathVariable int id,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminPost");
            modelAndView.addObject("post", postDAO.get(id));
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value="/post/edit/{id}", method = RequestMethod.GET)
    public ModelAndView adminPostEdit(@PathVariable int id,
                                          HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminPostEdit");
            modelAndView.addObject("post", postDAO.get(id));
            return  modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/post/save/{id}", method = RequestMethod.POST)
    public  ModelAndView savePost(@PathVariable int id,
                                  @RequestParam String title,
                                  @RequestParam String imagePath,
                                  @RequestParam String shortDescription,
                                  @RequestParam String dateOfPublication,
                                  @RequestParam String buttonText,
                                  @RequestParam String content,
                                  @RequestParam String metaDescription,
                                  @RequestParam String metaKeyWords,
                                  @RequestParam String metaTitle,
                                  ModelMap model,
                                  HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            postDAO.save(id, title, imagePath, shortDescription,
                    new SimpleDateFormat("dd.MM.yyyy").parse(dateOfPublication),
                    buttonText, content, metaDescription, metaKeyWords, metaTitle);
            return new ModelAndView("redirect:/admin/post/{id}", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/post/remove/{id}", method = RequestMethod.GET)
    public ModelAndView deleteArticle(@PathVariable int id,
                                      ModelMap model,
                                      HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            postDAO.remove(id);
            return new ModelAndView("redirect:/admin/post", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value="/post/add", method = RequestMethod.POST)
    public ModelAndView saveNewArticle(@RequestParam(value="title") String title,
                                       @RequestParam(value="imagePath") String imagePath,
                                       @RequestParam(value="shortDescription") String shortDescription,
                                       @RequestParam(value="dateOfPublication") String dateOfPublication,
                                       @RequestParam(value="buttonText") String buttonText,
                                       @RequestParam(value="content") String content,
                                       @RequestParam(value="metaDescription") String metaDescription,
                                       @RequestParam(value="metaKeyWords") String metaKeyWords,
                                       @RequestParam(value="metaTitle") String metaTitle,
                                       ModelMap model,
                                       HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            Post infoToAdd = new Post(title, imagePath, shortDescription,
                    new SimpleDateFormat("dd.MM.yyyy").parse(dateOfPublication),
                    buttonText, content, metaDescription, metaKeyWords, metaTitle);
            postDAO.save(infoToAdd);
            return new ModelAndView("redirect:/admin/post", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/catalog/product/edit/{id}",  method = RequestMethod.GET)
    public ModelAndView productEdit(@PathVariable int id,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminProductEdit");
            modelAndView.addObject("product", productDAO.getProductById(id));
            modelAndView.addObject("categories", categoryDAO.getAllCategories());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/catalog/product/save", method = RequestMethod.POST)
    public  ModelAndView saveProduct(@RequestParam int id,
                                     @RequestParam String name,
                                     @RequestParam int price,
                                     @RequestParam String currency,
                                     @RequestParam int productCategory,
                                     @RequestParam String brand,
                                     @RequestParam int amount,
                                     @RequestParam String inStock,
                                     @RequestParam String description,
                                     @RequestParam String shortDesc,
                                     @RequestParam String metaDescription,
                                     @RequestParam String metaKeyWords,
                                     @RequestParam String metaTitle,
                                     @RequestParam String image1,
                                     @RequestParam String image2,
                                     @RequestParam String image3,
                                     @RequestParam String image4,
                                     @RequestParam String isNew,
                                     @RequestParam int discount,
                                     ModelMap model,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            Category category = categoryDAO.getCategoryById(productCategory);
            productDAO.saveProduct(id, name, price, currency, category, amount, inStock, description, shortDesc, metaDescription, metaKeyWords, metaTitle,
                    image1, image2, image3, image4, "yes".equals(isNew), discount, brand );
            return new ModelAndView("redirect:/admin/catalog", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/catalog/product/{id}")
    public ModelAndView productView(@PathVariable int id,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminProduct");
            modelAndView.addObject("product", productDAO.getProductById(id));
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/catalog/product")
    public ModelAndView productAll(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminProducts");
            modelAndView.addObject("products", productDAO.getAllProducts());
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/catalog/product/remove/{id}")
    public ModelAndView productRemove(@PathVariable int id,
                                      ModelMap model,
                                      HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            productDAO.remove(id);
            return new ModelAndView("redirect:/admin/catalog", model);
        }

        return new ModelAndView("adminLogin", model);
    }




    @RequestMapping("/catalog")
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
    public ModelAndView categoryView(@PathVariable("name") String name,
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

    @RequestMapping(value = "/catalog/edit/{name}")
    public ModelAndView categoryEdit(@PathVariable("name") String name,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (checkStatus(session)) {
            modelAndView.setViewName("adminCategoryEdit");
            modelAndView.addObject("category", categoryDAO.getCategoryByName(name));
            return modelAndView;
        }

        modelAndView.setViewName("adminLogin");
        return modelAndView;

    }

    @RequestMapping(value = "/catalog/save/{id}")
    public ModelAndView categorySave(@PathVariable int id,
                                     @RequestParam(value="name") String name,
                                     @RequestParam(value="info") String info,
                                     @RequestParam(value="metaDescription") String metaDescription,
                                     @RequestParam(value="metaKeyWords") String metaKeyWords,
                                     @RequestParam(value="metaTitle") String metaTitle,
                                     ModelMap model,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            categoryDAO.saveCategory(id, name, info, metaDescription, metaKeyWords, metaTitle);
            return new ModelAndView("redirect:/admin/catalog", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/catalog/remove/{id}")
    public ModelAndView categoryDelete(@PathVariable int id,
                                     ModelMap model,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            productDAO.removeFromCategory(id);
            categoryDAO.remove(id);
            return new ModelAndView("redirect:/admin/catalog", model);
        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value="/catalog/product/add", method = RequestMethod.POST)
    public ModelAndView addProduct(@RequestParam String name,
                                   @RequestParam int price,
                                   @RequestParam String currency,
                                   @RequestParam int id,
                                   @RequestParam String brand,
                                   @RequestParam int amount,
                                   @RequestParam String inStock,
                                   @RequestParam String description,
                                   @RequestParam String shortDesc,
                                   @RequestParam String metaDescription,
                                   @RequestParam String metaKeyWords,
                                   @RequestParam String metaTitle,
                                   @RequestParam String image1,
                                   @RequestParam String image2,
                                   @RequestParam String image3,
                                   @RequestParam String image4,
                                   @RequestParam String isNew,
                                   @RequestParam int discount,
                                   ModelMap model,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            Category category = categoryDAO.getCategoryById(id);
            Product product = new Product(name, price, currency, category, amount, inStock, description,  shortDesc, metaDescription, metaKeyWords, metaTitle,
                     image1, image2, image3, image4, "yes".equals(isNew), discount, brand);
            productDAO.saveProduct(product);
           return new ModelAndView("redirect:/admin/catalog", model);

        }

        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value="/catalog/add", method = RequestMethod.POST)
    public ModelAndView addCategory (@RequestParam(value="name") String name,
                                     @RequestParam(value="info") String info,
                                     @RequestParam(value="metaDescription") String metaDescription,
                                     @RequestParam(value="metaKeyWords") String metaKeyWords,
                                     @RequestParam(value="metaTitle") String metaTitle,
                                     ModelMap model,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (checkStatus(session)) {
            Category category = new Category(name, info, metaDescription, metaKeyWords, metaTitle);
            categoryDAO.saveCategory(category);
            return new ModelAndView("redirect:/admin/catalog", model);
        }

        return new ModelAndView("adminLogin");
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
                        product.getImage1(), product.getName(), product.getPrice(), currency, quantity);
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
                                    productById.getImage1(), productById.getName(), productById.getPrice(),
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
            modelAndView.setViewName("adminFeedbackEdit");
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
