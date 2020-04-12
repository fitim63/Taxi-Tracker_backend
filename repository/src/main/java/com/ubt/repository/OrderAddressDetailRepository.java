package com.ubt.repository;

import com.ubt.model.OrderAddressDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderAddressDetailRepository extends JpaRepository<OrderAddressDetail, Integer> {
}
