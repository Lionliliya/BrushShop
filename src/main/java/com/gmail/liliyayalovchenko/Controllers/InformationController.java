package com.gmail.liliyayalovchenko.Controllers;

import com.gmail.liliyayalovchenko.DAO.CategoryDAO;
import com.gmail.liliyayalovchenko.DAO.PostDAO;
import com.gmail.liliyayalovchenko.DAO.ProductDAO;
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
    private PostDAO postDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductDAO productDAO;

    @RequestMapping("/news")
    public ModelAndView news(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        session.setAttribute("startRow", 1);
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("articles", postDAO.getNextPosts(1));
//        modelAndView.addObject("articles", postDAO.getAllPostAsc());
        modelAndView.addObject("brands", productDAO.getAllBrands());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("news");
        return modelAndView;
    }

    @RequestMapping("/news/next")
    public ModelAndView newsNextPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        Integer startRow = (Integer) session.getAttribute("startRow");
        session.setAttribute("startRow", startRow + 3);
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("articles", postDAO.getNextPosts(startRow + 3));
        modelAndView.addObject("brands", productDAO.getAllBrands());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("news");
        return modelAndView;
    }

    @RequestMapping("/news/previous")
    public ModelAndView newsPrevPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        Integer startRow = (Integer) session.getAttribute("startRow");
        startRow -= 3;
        session.setAttribute("startRow",startRow);
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("articles", postDAO.getNextPosts(startRow));
        modelAndView.addObject("brands", productDAO.getAllBrands());
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
        modelAndView.addObject("articles", postDAO.getAllPostsDesc());
        modelAndView.addObject("brands", productDAO.getAllBrands());
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
        modelAndView.addObject("article", postDAO.get(id));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(postDAO.get(id).getDateOfPublication());
        modelAndView.addObject("date", date);
        modelAndView.addObject("brands", productDAO.getAllBrands());
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
