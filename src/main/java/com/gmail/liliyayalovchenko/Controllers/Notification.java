package com.gmail.liliyayalovchenko.Controllers;

import com.gmail.liliyayalovchenko.Domains.Order;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Notification {


    public void sendHttpEmail(String from, String to, String host, String subject,  Order order) {
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Send the actual HTML message, as big as you like
            message.setContent("<h2>Приветствуем тебя, " + order.getClient().getFirstName() + "</h2> <p> Спасибо за заказ на сайте Beauty Tree. </p> <p>Твой заказ принят! Номер заказа + " + order.getId() + "</p> <p>Сумма заказа: + " + order.getTotalAmount() +  " грн.</p> <p>Мы свяжемся с вами в течении суток.</p> ", "text/html");

            // Send message
            Transport.send(message);
            System.out.println("SEND EMAIL");System.out.println("SEND EMAIL");
            System.out.println("Sent message successfully for order#" + order.getId());
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
