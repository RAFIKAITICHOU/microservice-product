package com.microservice.product;

import com.microservice.product.config.AppConfig;
import com.microservice.product.entity.Product;
import com.microservice.product.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // On récupère le bean via l'INTERFACE : @Transactional crée un proxy JDK
        ProductService productService = context.getBean(ProductService.class);

        Product p = new Product("Microservice Gateway", 149.99);
        productService.addProduct(p);

        System.out.println("Produit ajouté avec succès ! Total en base : "
                + productService.getAllProducts().size());
        context.close();
    }
}
