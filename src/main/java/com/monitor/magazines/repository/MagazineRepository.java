package com.monitor.magazines.repository;

import com.monitor.magazines.domain.Magazine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MagazineRepository extends CrudRepository<Magazine, Long>{
    @Override
    List<Magazine> findAll();
}
