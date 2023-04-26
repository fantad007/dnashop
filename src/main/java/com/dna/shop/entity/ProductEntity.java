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
@Table(name = "product")
public class ProductEntity extends BaseAuditEntity {
    private String productCode;
    private String name;
    private double price;
    private String shortDesc;
    private String fullDesc;
    private String thumbnail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<SizeEntity> sizes;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CategoryEntity category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ImageRepresentEntity> imageRepresents;
}
