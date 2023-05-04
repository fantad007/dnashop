package com.dna.shop.entity;

import com.dna.shop.entity.base.BaseAuditEntity;
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
    private double price;
    private String shortDesc;
    private String fullDesc;
    private String thumbnail;
    private String cartImage;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CategoryEntity category;
}
