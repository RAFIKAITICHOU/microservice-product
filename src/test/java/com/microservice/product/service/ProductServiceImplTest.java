package com.microservice.product.service;

import com.microservice.product.config.AppConfig;
import com.microservice.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppConfig.class)
@Transactional
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void addProductPersistsIt() {
        int before = productService.getAllProducts().size();
        Product p = new Product("Microservice Gateway", 149.99);
        productService.addProduct(p);

        assertNotNull(p.getId());
        assertEquals(before + 1, productService.getAllProducts().size());
    }

    @Test
    void addProductWithNegativePriceIsRejected() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct(new Product("Invalide", -5)));
        assertEquals("Le prix ne peut pas être négatif", ex.getMessage());
    }

    @Test
    void updateAndDeleteWork() {
        Product p = new Product("Tablette", 200);
        productService.addProduct(p);

        p.setName("Tablette Pro");
        productService.updateProduct(p);
        assertEquals("Tablette Pro", productService.getProductById(p.getId()).getName());

        productService.deleteProduct(p);
        assertNull(productService.getProductById(p.getId()));
    }
}
