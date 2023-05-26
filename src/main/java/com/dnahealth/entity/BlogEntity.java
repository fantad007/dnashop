package com.dnahealth.entity;

import com.dnahealth.entity.base.BaseAuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blog")
public class BlogEntity extends BaseAuditEntity {
    private String title;
    private String shortDesc;
    private String fullDesc;
}
