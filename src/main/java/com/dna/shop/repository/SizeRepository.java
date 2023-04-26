package com.dna.shop.repository;

import com.dna.shop.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, Long> {
    List<SizeEntity> findAllByProductId(long proId);
}
