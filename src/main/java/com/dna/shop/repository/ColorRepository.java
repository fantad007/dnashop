package com.dna.shop.repository;

import com.dna.shop.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Long> {
    @Query(value = "select * from color c inner join \"size\" s \n" +
            "on c.size_id = s.id where s.id = ?1", nativeQuery = true)
    List<ColorEntity> getAllBySizeId(long sizeId);
}
