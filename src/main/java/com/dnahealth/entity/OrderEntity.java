package com.dnahealth.entity;

import com.dnahealth.entity.base.BaseAuditEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_cart")
public class OrderEntity extends BaseAuditEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetailEntity> orderDetails;
    @Column(name = "total_money")
    private double totalMoney;
    @Column(name = "status")
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;
}
