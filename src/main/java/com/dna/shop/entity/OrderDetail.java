package com.dna.shop.entity;

import com.dna.shop.entity.base.BaseAuditEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseAuditEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderDetail")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CartEntity> cart;
    @Column(name = "total_money")
    private double totalMoney;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;
}
