package com.gmail.liliyayalovchenko.Controllers;

import com.gmail.liliyayalovchenko.DAO.CategoryDAO;
import com.gmail.liliyayalovchenko.DAO.ClientDAO;
import com.gmail.liliyayalovchenko.DAO.OrderDAO;
import com.gmail.liliyayalovchenko.DAO.ProductInCartDAO;
import com.gmail.liliyayalovchenko.Domains.Client;
import com.gmail.liliyayalovchenko.Domains.Order;
import com.gmail.liliyayalovchenko.Domains.ProductInCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductInCartDAO productInCartDAO;

    @Autowired
    private ClientDAO clientDAO;

    @RequestMapping(value = "/updateCart", method = RequestMethod.POST)
    public ModelAndView updateCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();

        List<ProductInCart> productsCart = (ArrayList<ProductInCart>) session.getAttribute("ProductsInCart");

        List<String> quantityList = Arrays.asList(request.getParameterValues("quantity"));

        int n = 0;
        int totalValue = 0;

        for (ProductInCart productInCart : productsCart) {
            int quantity = Integer.valueOf(quantityList.get(n));
            productInCart.setQuantity(quantity);
            totalValue += quantity*productInCart.getPrice();
            n++;
        }
        modelAndView.addObject("totalValue", totalValue);
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("ProductsInCart", productsCart);
        modelAndView.addObject("cartSize", productsCart.size());

        session.setAttribute("ProductsInCart", productsCart);
        session.setAttribute("cartSize", productsCart.size());

        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @RequestMapping(value = "/ordering", method = RequestMethod.POST)
    public ModelAndView ordering(@RequestParam(value = "firstName") String firstName,
                                 @RequestParam(value = "phoneNumber") String phoneNumber,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "delivery") String delivery,
                                 @RequestParam(value = "comments") String comments,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();

        List<ProductInCart> productsInCart = (ArrayList<ProductInCart>) session.getAttribute("ProductsInCart");

        Client client = clientDAO.findClientByPhone(phoneNumber, email);
        if (client == null) {
            client = new Client(firstName, phoneNumber, email);
        }
        clientDAO.addClient(client);
        int amount = 0;
        for (ProductInCart aProductsCart1 : productsInCart) {
            amount += aProductsCart1.getPrice()*aProductsCart1.getQuantity();
        }
        Order order = new Order(new Date(), delivery, comments, client, amount);
        for (ProductInCart product : productsInCart) {
            product.setOrder(order);
        }
        orderDAO.saveOrder(order);
        productInCartDAO.saveProductInCart(productsInCart);

        modelAndView.addObject("client", client);
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("ProductsInCart", productsInCart);
        modelAndView.addObject("order", order);
        modelAndView.addObject("totalAmount", amount);
        session.removeAttribute("ProductsInCart");
        session.removeAttribute("cartSize");
        session.setAttribute("ProductsInCart", new ArrayList<>());
        session.setAttribute("cartSize", 0);
        modelAndView.setViewName("ordering");
        return modelAndView;
    }

    public void checkSession(HttpSession session) {
        try {
            ArrayList<ProductInCart> ProductsInCart = (ArrayList<ProductInCart>)
                    session.getAttribute("ProductsInCart");
            int cartCount = (int) session.getAttribute("cartSize");
            ProductsInCart.get(0);
        } catch (Exception e) {
            ArrayList<ProductInCart> ProductsInCart = new ArrayList<>();
            session.setAttribute("ProductsInCart", ProductsInCart);
            session.setAttribute("cartSize", ProductsInCart.size());
        }
    }

}
