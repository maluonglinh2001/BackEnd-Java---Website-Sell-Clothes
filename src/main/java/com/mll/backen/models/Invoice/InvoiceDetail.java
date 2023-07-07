package com.mll.backen.models.Invoice;

import com.mll.backen.models.Products;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoiceDetail")
@IdClass(InvoiceDetailId.class)
public class InvoiceDetail {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoiceId")
    private Invoice invoiceId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Products productId;

    private String detailProductSize;

    private Integer detailProductQuantity;
}
