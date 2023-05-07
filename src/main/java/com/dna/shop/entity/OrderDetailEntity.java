package com.dna.shop.entity;

import com.dna.shop.entity.base.BaseAuditEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetailEntity extends BaseAuditEntity {
    @Column(name = "quantity")
    private int quantity = 1;
    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderEntity order;
}
