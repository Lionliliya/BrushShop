package com.gmail.liliyayalovchenko.Controllers;

import com.gmail.liliyayalovchenko.DAO.CategoryDAO;
import com.gmail.liliyayalovchenko.DAO.PostDAO;
import com.gmail.liliyayalovchenko.DAO.ProductDAO;
import com.gmail.liliyayalovchenko.Domains.Category;
import com.gmail.liliyayalovchenko.Domains.Product;
import com.gmail.liliyayalovchenko.Domains.ProductInCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class CategoryController {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private PostDAO postDAO;

    @RequestMapping("/")
    public ModelAndView main(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("brands", productDAO.getAllBrands());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("articles", postDAO.getTwoLatest());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping("/catalog/brand/{brand}")
    public ModelAndView brand(@PathVariable("brand") String brand,
                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("brands", productDAO.getAllBrands());
        modelAndView.addObject("curBrand", brand);
        modelAndView.addObject("productBrand", productDAO.getProductsByBrand(brand));
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("brand");
        return modelAndView;
    }

    @RequestMapping("/catalog/{id}")
    public ModelAndView category(@PathVariable("id") int id,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("currentCategory", categoryDAO.getCategoryById(id));
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productDAO.getAllBrands());
        List<Product> products = productDAO.getProductsByCategoryId(id);
        if (products.size() == 0) {
            modelAndView.addObject("productsSize", 0);
        } else {
            modelAndView.addObject("products", products);
            modelAndView.addObject("productsSize", products.size());
        }
        modelAndView.setViewName("category");
        return modelAndView;
    }

    @RequestMapping("/catalog/priceDown/{id}")
    public ModelAndView sortByPriceDown(@PathVariable("id") int id,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        Category category = categoryDAO.getCategoryById(id);
        List<Product> productList = productDAO.getProductsByCategoryPriceDown(id);
        modelAndView.addObject("currentCategory", category);
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productDAO.getAllBrands());

        if (productList.size() == 0) {
            modelAndView.addObject("productsSize", 0);
        } else {
            modelAndView.addObject("products", productList);
            modelAndView.addObject("productsSize", productList.size());
        }

        modelAndView.setViewName("category");
        return modelAndView;
    }

    @RequestMapping("/catalog/brand/priceUp/{curBrand}")
    public ModelAndView sortBrandByPriceUp(@PathVariable("curBrand") String curBrand,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productDAO.getProductsByBrandPriceUp(curBrand);
        modelAndView.addObject("curBrand", curBrand);
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productDAO.getAllBrands());

        if (productList.size() == 0) {
            modelAndView.addObject("productsSize", 0);
        } else {
            modelAndView.addObject("productBrand", productList);
            modelAndView.addObject("productsSize", productList.size());
        }

        modelAndView.setViewName("brand");
        return modelAndView;
    }

    @RequestMapping("/catalog/brand/priceDown/{curBrand}")
    public ModelAndView sortBrandByPriceDown(@PathVariable("curBrand") String curBrand,
                                           HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productDAO.getProductsByBrandPriceDown(curBrand);
        modelAndView.addObject("curBrand", curBrand);
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productDAO.getAllBrands());

        if (productList.size() == 0) {
            modelAndView.addObject("productsSize", 0);
        } else {
            modelAndView.addObject("productBrand", productList);
            modelAndView.addObject("productsSize", productList.size());
        }

        modelAndView.setViewName("brand");
        return modelAndView;
    }

    @RequestMapping("/catalog/priceUp/{id}")
    public ModelAndView sortByPriceUp(@PathVariable("id") int id,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        Category category = categoryDAO.getCategoryById(id);
        List<Product> productList = productDAO.getProductsByCategoryPriceUp(id);
        modelAndView.addObject("currentCategory", category);
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productDAO.getAllBrands());

        if (productList.size() == 0) {
            modelAndView.addObject("productsSize", 0);
        } else {
            modelAndView.addObject("products", productList);
            modelAndView.addObject("productsSize", productList.size());
        }

        modelAndView.setViewName("category");
        return modelAndView;
    }



    @RequestMapping("/packing")
    public ModelAndView packing(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("packing");
        return modelAndView;
    }

    @RequestMapping("/deliveryAndPayments")
    public ModelAndView delivery(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.setViewName("delivery");
        return modelAndView;
    }

    @RequestMapping("/contacts")
    public ModelAndView contacts(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryDAO.getAllCategories());
        modelAndView.addObject("cartSize", session.getAttribute("cartSize"));
        modelAndView.addObject("brands", productDAO.getAllBrands());
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    public void checkSession(HttpSession session) {
        try {
            ArrayList<ProductInCart> ProductsInCart = (ArrayList<ProductInCart>)
                    session.getAttribute("ProductsInCart");
            int cartCount = (int)session.getAttribute("cartSize");
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
