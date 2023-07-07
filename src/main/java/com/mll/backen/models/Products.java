package com.mll.backen.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameProduct;

    private String introduce;

    private String detail;

    private Integer price;

    private String img;

    private Integer quantity;

    private String location;

    private String material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productTypeId")
    private ProductType productTypeId;

    private String size;
}
