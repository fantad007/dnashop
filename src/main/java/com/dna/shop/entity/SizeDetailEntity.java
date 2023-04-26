package com.dna.shop.entity;

import com.dna.shop.entity.base.BaseAuditEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "size_detail")
public class SizeDetailEntity extends BaseAuditEntity {
    private String shirtLength;
    private String chestCircumference;
    private String shoulderWidth;
    private String sleeveLength;
    private String weight;
    private String height;
    @ManyToOne
    @JoinColumn(name = "size_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SizeEntity size;
    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
