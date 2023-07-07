package com.mll.backen.DTO.Request;

import com.mll.backen.models.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String nameProduct;
    private String introduce;
    private String detail;
    private Integer price;
    private String img;
    private Integer quantity;
    private String location;
    private String material;
    private ProductType productTypeId;
    private String size;
}
