package com.dealwars.repository;

import com.dealwars.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetail, Long> {
    List<ProductDetail> findTop4ByUserInputOrderByPrice(String input);

    List<ProductDetail> findAllByUserInput(String input);
}
