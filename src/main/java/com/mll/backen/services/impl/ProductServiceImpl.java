package com.mll.backen.services.impl;

import com.mll.backen.DTO.Request.ProductRequest;
import com.mll.backen.models.Products;
import com.mll.backen.repository.ProductRepository;
import com.mll.backen.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(ProductRequest request) {
        var product = Products.builder()
                .nameProduct(request.getNameProduct())
                .introduce(request.getIntroduce())
                .detail(request.getDetail())
                .price(request.getPrice())
                .img(request.getImg())
                .quantity(request.getQuantity())
                .location(request.getLocation())
                .material(request.getMaterial())
                .productTypeId(request.getProductTypeId())
                .size(request.getSize())
                .build();
        productRepository.save(product);
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Products getProductById(Integer id) {
        Products products=productRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid user Id:"+id));
    return products;
    }

    @Override
    public void updateProduct(Integer id, ProductRequest request) {
        var product = Products.builder()
                .nameProduct(request.getNameProduct())
                .introduce(request.getIntroduce())
                .detail(request.getDetail())
                .price(request.getPrice())
                .img(request.getImg())
                .quantity(request.getQuantity())
                .location(request.getLocation())
                .material(request.getMaterial())
                .productTypeId(request.getProductTypeId())
                .size(request.getSize())
                .build();
        product.setId(id);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        Products products = productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:"+id));
        productRepository.delete(products);
    }

    @Override
    public List<Products> getProductWithSort(String field) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @Override
    public Page<Products> findProductsWithPagination(Integer offset, Integer pageSize) {
        Page<Products> products = productRepository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }

    @Override
    public Page<Products> findProductsWithPaginationAndSorting(Integer offset, Integer pageSize, String field) {
        Page<Products> products = productRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
        return products;
    }


}
