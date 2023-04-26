package com.dna.shop.repository;

import com.dna.shop.entity.ImageRepresentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepresentRepository extends JpaRepository<ImageRepresentEntity, Long> {
    @Query(value = "select * from image_represent ir inner join product p \n" +
            "on ir.product_id = p.id where p.id = ?1", nativeQuery = true)
    List<ImageRepresentEntity> getImageRepresentsByProductId(long proId);
}
