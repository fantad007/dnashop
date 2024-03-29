package com.dnahealth.repository;

import com.dnahealth.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(value = "select c.* from category c " +
            "inner join product p on c.id = p.category_id " +
            "where p.id = ?1", nativeQuery = true)
    Optional<CategoryEntity> findByProductId(long proId);
}
