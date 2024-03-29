package com.dnahealth.entity;

import com.dnahealth.entity.base.BaseAuditEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity extends BaseAuditEntity {
    private String productCode;
    private String name;
    private String trademark;
    private double price;
    private String shortDesc;
    private String fullDesc;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CategoryEntity category;
}
