package com.dnahealth.entity;

import com.dnahealth.entity.base.BaseAuditEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseAuditEntity {
    private String name;
    private String categoryCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductEntity> products;
}
