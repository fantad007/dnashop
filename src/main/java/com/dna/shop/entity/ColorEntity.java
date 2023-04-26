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
@Table(name = "color")
public class ColorEntity extends BaseAuditEntity {
    private String name;
    private int quantity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "color")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ImageEntity> images;
    @ManyToOne
    @JoinColumn(name = "size_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SizeEntity size;
}
