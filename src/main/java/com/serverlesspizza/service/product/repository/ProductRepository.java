package com.serverlesspizza.service.product.repository;

import com.serverlesspizza.service.product.domain.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductRepository extends CrudRepository<Product, String> {

}
