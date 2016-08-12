package com.gmail.liliyayalovchenko.Controllers;

import com.gmail.liliyayalovchenko.DAO.CategoryDAO;
import com.gmail.liliyayalovchenko.DAO.InformationDAO;
import com.gmail.liliyayalovchenko.Domains.ProductInCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class InformationController {

    @Autowired
    private InformationDAO informationDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping("/news")
    public ModelAndView news(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("articles", informationDAO.getAllArticlesOrdered());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("news");
        return modelAndView;
    }

    @RequestMapping("/news/sort-desc")
    public ModelAndView news_sort(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("articles", informationDAO.getAllArticlesDesc());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("news");
        return modelAndView;
    }

    @RequestMapping("/news/{id}")
    public ModelAndView article(@PathVariable("id") int id,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("article", informationDAO.getAllArticles(id));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(informationDAO.getAllArticles(id).getDateOfPublication());
        modelAndView.addObject("date", date);
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("article");
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
