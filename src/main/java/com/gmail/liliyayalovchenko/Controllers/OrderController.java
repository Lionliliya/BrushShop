package com.gmail.liliyayalovchenko.Controllers;

import com.gmail.liliyayalovchenko.DAO.CategoryDAO;
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
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("ProductsInCart", session.getAttribute("ProductsInCart"));
        List<ProductInCart> ProductsCart = (ArrayList<ProductInCart>) session.getAttribute("ProductsInCart");
        modelAndView.addObject("cartSize", 0);
        Client client = new Client(firstName, phoneNumber, email);
        modelAndView.addObject("client", client);

        int amount = 0;
        for (ProductInCart aProductsCart1 : ProductsCart) {
            amount += aProductsCart1.getPrice();
        }
        Order order = new Order(new Date(), delivery, comments, client, ProductsCart, amount);
        for (ProductInCart aProductsCart : ProductsCart) {
            aProductsCart.setOrder(order);
        }
        orderDAO.saveOrder(order);
        productInCartDAO.saveProductInCart(ProductsCart);

        modelAndView.addObject("orderId", order.getId());
        modelAndView.addObject("delivery", order.getDelivery());
        modelAndView.addObject("totalAmount", totalAmount(session));
        session.removeAttribute("ProductsInCart");
        session.removeAttribute("cartSize");
        ArrayList<ProductInCart> ProductsInCart = new ArrayList<>();
        session.setAttribute("ProductsInCart", ProductsInCart);
        session.setAttribute("cartSize", ProductsInCart.size());
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

    public int totalAmount(HttpSession session) {
        int totalAmount = 0;
        ArrayList<ProductInCart> ProductsInCart = (ArrayList<ProductInCart>) session.getAttribute("ProductsInCart");
        for (ProductInCart aProductsInCart : ProductsInCart) {
            totalAmount += aProductsInCart.getPrice();
        }
        return totalAmount;
    }

}
