package com.dna.shop.repository;

import com.dna.shop.entity.SizeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeDetailRepository extends JpaRepository<SizeDetailEntity, Long> {
}
