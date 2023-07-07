package com.mll.backen.models.Cart;

import com.mll.backen.models.Products;
import com.mll.backen.models.User.User;
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
@Table(name = "cart")
@IdClass(CartId.class)
public class Cart {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Products productId;

    private String cartProductSize;

    private Integer cartProductQuantity;
}
