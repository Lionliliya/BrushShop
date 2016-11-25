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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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

    @RequestMapping(value = "/cart-clear", method = RequestMethod.GET)
    public ModelAndView cartClearing(HttpServletRequest request,
                                     ModelMap model) {
        HttpSession session = request.getSession();
        checkSession(session);
        session.removeAttribute("ProductsInCart");
        session.removeAttribute("cartSize");
        session.setAttribute("ProductsInCart", new ArrayList<ProductInCart>());
        session.setAttribute("cartSize", 0);
        return new ModelAndView("redirect:/cart", model);
    }

    @RequestMapping(value = "/ordering", method = RequestMethod.POST)
    public ModelAndView ordering(@RequestParam(value = "firstName") String firstName,
                                 @RequestParam(value = "phoneNumber") String phoneNumber,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "delivery") String delivery,
                                 @RequestParam(value = "comments") String comments,
                                 HttpServletRequest request) throws MessagingException {
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

        sendClientMail(productsInCart, firstName, email, delivery, order.getId(), amount);

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

    private void sendClientMail(List<ProductInCart> productsInCart, String clientName, String clientEmail, String delivery, int orderId, int amount) throws MessagingException {
        Properties mailServerProperties = System.getProperties();

        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.setHeader("Content-Type", "text/html; charset=UTF-8");
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(clientEmail));
        generateMailMessage.setSubject("Заказ № " + orderId,  "utf-8");
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("<div class=\"container\">");
        emailBody.append("<div class=\"col-md-9\" id=\"customer-order\">");
        emailBody.append("<div class=\"box\">");
        emailBody.append("<img src=\"http://localhost:8081/resources/img/logo.png\">");

        emailBody.append("<h2>Здравствуйте, " + clientName + "!</h2>");
        emailBody.append("<p class=\"lead\">Ваш заказ подтвержден и передан для формирования на склад. </p>\n" +
                        "\n" +
                        "<p class=\"lead\">Срок доставки составляет от 1 до 3 рабочих дней.</p>\n");
        emailBody.append("<p class=\"text-muted\">" +
                "Номер заказа: " + orderId + "\n</p>" +
                "<p>Доставка: " +  delivery + "\n</p>" +
                "<p>Способ оплаты: Наличными при доставке." +
                "</p>");
        emailBody.append(
                "\n" +
                "                        <div>\n" +
                "                            <table style=\"border-collapse: collapse;\">\n" +
                "                                <thead>\n" +
                "                                    <tr style=\"background-color: #D9E5EE;\">\n" +
                "                                        <th align=\"center\" style=\" padding: 3px; border: 1px solid black;\">Товар</th>\n" +
                "                                        <th align=\"center\" style=\" padding: 3px; border: 1px solid black;\">Количество</th>\n" +
                "                                        <th align=\"center\" style=\" padding: 3px; border: 1px solid black;\">Цена</th>\n" +
                "                                        <th align=\"center\" style=\" padding: 3px; border: 1px solid black;\">Всего</th>\n" +
                "                                    </tr>\n" +
                "                                </thead>\n" +
                "                                <tbody>");
        for (ProductInCart productInCart : productsInCart) {
            emailBody.append(
                    "<tr>\n" +
                    "               <td align=\"center\" style=\" padding: 3px; border: 1px solid black;\"><a href=\"http://localhost:8081/product/ " +  productInCart.getProduct_id().getId() + "\"> " + productInCart.getName() + " </a>\n" +
                    "               <td align=\"center\" style=\" padding: 3px; border: 1px solid black;\">" + productInCart.getQuantity() + "</td>\n" +
                    "               <td align=\"center\" style=\" padding: 3px; border: 1px solid black;\">₴ " + productInCart.getPrice() + "</td>\n" +

                    "               <td align=\"center\" style=\" padding: 3px; border: 1px solid black;\">₴ " + productInCart.getPrice()*productInCart.getQuantity() + "</td>\n" +
                    "\n" +
                    "</tr>");
        }

        emailBody.append(" </tbody>\n" +
                "                                <tfoot>\n" +
                "                                    <tr>\n" +
                "                                        <th align=\"center\" colspan=\"3\" style=\" padding: 3px; border: 1px solid black;\">Итог</th>\n" +
                "                                        <th align=\"center\" style=\" padding: 3px; border: 1px solid black;\">₴" + amount + "</th>\n" +
                "                                    </tr>");
        emailBody.append("</tfoot>\n" +
                "                            </table>\n" +
                "\n" +
                "                        </div>");
        emailBody.append("</div> </div> </div>");
        generateMailMessage.setContent(emailBody.toString(), "text/html; charset=utf-8");
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "service.beautytree", "Mne_24_let");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
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
