package com.gmail.liliyayalovchenko.Configurations;

import com.gmail.liliyayalovchenko.DAO.*;
import com.gmail.liliyayalovchenko.DAOImplementation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Configuration
@ComponentScan("com.gmail.liliyayalovchenko")
@EnableAspectJAutoProxy
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {


        @Bean
        public EntityManager entityManager() {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BeautyJPA");
            return emf.createEntityManager();
        }

        @Bean
        public AdministratorDAO administratorDAO() {
            return new AdministratorDAOImpl();
        }

        @Bean
        public CategoryDAO categoryDAO() {
            return new CategoryDAOImpl();
        }

        @Bean
        public ClientDAO clientDAO() {
            return new ClientDAOImpl();
        }

        @Bean
        public FeedBackDAO feedBackDAO() {
            return new FeedBackDAOImpl();
        }

        @Bean
        public PostDAO informationDAO() {
            return new PostDAOImpl();
        }

        @Bean
        public OrderDAO orderDAO() {
            return new OrderDAOImpl();
        }

        @Bean
        public ProductDAO productDAO() {
            return new ProductDAOImpl();
        }

        @Bean
        public ProductInCartDAO productInCartDAO() {
            return new ProductInCartDAOImpl();
        }

        @Bean
        public UrlBasedViewResolver setupViewResolver() {
            UrlBasedViewResolver resolver = new UrlBasedViewResolver();
            resolver.setContentType("text/html;charset=UTF-8");
            resolver.setPrefix("/WEB-INF/pages/");
            resolver.setSuffix(".jsp");
            resolver.setViewClass(JstlView.class);
            resolver.setOrder(1);
            return resolver;
        }

        @Bean
        public CommonsMultipartResolver multipartResolver() {
            return new CommonsMultipartResolver();
        }


        @Bean
        AppLogging aspectLogging() {
        return new AppLogging();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    }

