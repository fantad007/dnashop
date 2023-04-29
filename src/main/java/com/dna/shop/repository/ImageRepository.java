package com.dna.shop.repository;

import com.dna.shop.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    @Query(value = "select * from image i where i.product_id = ?1", nativeQuery = true)
    List<ImageEntity> getImagesByProductId(long proId);
}
