package com.dna.shop.entity;

import com.dna.shop.entity.base.BaseAuditEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class CartEntity extends BaseAuditEntity {
    @Column(name = "quantity")
    private int quantity = 1;
    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @OneToOne
    @JoinColumn(name = "size_id")
    private SizeEntity size;
    @OneToOne
    @JoinColumn(name = "color_id")
    private ColorEntity color;
    @ManyToOne
    @JoinColumn(name = "orderDetail_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderDetail orderDetail;
}
