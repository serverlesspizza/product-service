package com.serverlesspizza.service.product.controller;

import com.serverlesspizza.service.product.domain.Product;
import com.serverlesspizza.service.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@EnableWebMvc
@CrossOrigin(
    origins = {"https://www.serverlesspizza.com", "https://dev.serverlesspizza.com", "http://localhost:3000"},
    allowCredentials = "true",
    allowedHeaders = {"Authorization", "x-amz-security-token", "x-amz-date", "x-amz-algorithm", "x-amz-credential", "x-amz-expires", "x-amz-signedHeaders", "x-amz-signature"},
    methods = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
@RequestMapping(path = "/")
public class ProductServiceRestController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts() {
        final List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(product -> products.add(product));

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(products, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable final String id) {
        final Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(path = "/products", method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
        final Product savedProduct = productRepository.save(product);

        return ResponseEntity
            .created(URI.create("/products/" + savedProduct.getProductId() + "/"))
            .build();
    }

    @RequestMapping(path = "/products", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@RequestBody final Product product) {
        if (!productRepository.existsById(product.getProductId())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productRepository.save(product));
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable final String id) {
        productRepository.deleteById(id);

        return ResponseEntity
            .noContent()
            .build();
    }
}
