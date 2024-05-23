package com.unknown.delivery.domain.option.dao;

import com.unknown.delivery.domain.option.entity.OptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionDetailRepository extends JpaRepository<OptionDetail, Long> {
}
