package com.mll.backen.controllers;

import com.mll.backen.DTO.Request.ProductRequest;
import com.mll.backen.DTO.Response.ResponseObject;
import com.mll.backen.models.Products;
import com.mll.backen.repository.ProductRepository;
import com.mll.backen.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/addProduct")
    public ResponseEntity<Object> addProduct(@RequestBody ProductRequest products) {
        try {
            productService.addProduct(products);
            System.out.println(products.getNameProduct());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("200", "Success", products)
            );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("404", ex.getMessage(), "")
            );
        }
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<Object> getAllProducts() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("200", "Success", productService.getAllProducts())
            );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("404", ex.getMessage(), "")
            );
        }
    }

    @GetMapping("/getProductById")
    public ResponseEntity<ResponseObject> getProductById(@RequestParam Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("200", "Success", productService.getProductById(id))
            );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("404", ex.getMessage(), "")
            );
        }
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Integer id, @RequestBody ProductRequest productRequest) {
        Optional<Products> existProduct = productRepository.findById(id);
        if (existProduct.isPresent()) {
            try {
                productService.updateProduct(id, productRequest);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("200", "Update Succeed", productService.getProductById(id))
                );
            } catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject("400", "Có lỗi xảy ra!", "")
                );
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("404", "Not found ID product", "")
            );
        }
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
        Optional<Products> existProduct = productRepository.findById(id);
        if (existProduct.isPresent()) {

            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("200", "Deleted Succeed", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("404", "Not found ID product", "")
            );
        }
    }
    @GetMapping("getProduct/{field}")
    public ResponseEntity<Object> getProductByField(@PathVariable String field)
    {
        List<Products> gProducts = productService.getProductWithSort(field);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("200", "Succeed", gProducts)
        );
    }

    @GetMapping("pagination/{offset}/{pageSize}")
    public ResponseEntity<Object> getProductsWithPagination(@PathVariable Integer offset,@PathVariable Integer pageSize)
    {
        Page<Products> productsWithPagination = productService.findProductsWithPagination(offset,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("200", "Succeed", productsWithPagination)
        );
    }
    @GetMapping("paginationAndSort/{offset}/{pageSize}/{field}")
   public ResponseEntity<Object> findProductsWithPaginationAndSorting(@PathVariable Integer offset, @PathVariable Integer pageSize, @PathVariable String field)
    {
        Page<Products> productsPage = productService.findProductsWithPaginationAndSorting(offset,pageSize,field);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("200", "Succeed", productsPage)
        );
    }
}

