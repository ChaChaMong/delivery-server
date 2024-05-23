package com.unknown.delivery.domain.option.dao;

import com.unknown.delivery.domain.option.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}
