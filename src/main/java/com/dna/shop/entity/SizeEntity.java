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
@Table(name = "size")
public class SizeEntity extends BaseAuditEntity {
    private String size;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "size")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<SizeDetailEntity> sizeDetails;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "size")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ColorEntity> colors;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductEntity product;
}
