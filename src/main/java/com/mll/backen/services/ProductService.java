package com.mll.backen.services;

import com.mll.backen.DTO.Request.ProductRequest;
import com.mll.backen.models.Products;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    void addProduct(ProductRequest products);

    List<Products> getAllProducts();

    Products getProductById(Integer id);

    void updateProduct(Integer id, ProductRequest products);

    void deleteProduct(Integer id);

    List<Products> getProductWithSort(String field);

    Page<Products> findProductsWithPagination(Integer offset, Integer pageSize);

    Page<Products> findProductsWithPaginationAndSorting(Integer offset, Integer pageSize, String field);
}