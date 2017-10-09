package com.monitor.magazines.repository;

import com.monitor.magazines.domain.Magazine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MagazineRepository extends CrudRepository<Magazine, Long>{
    @Override
    List<Magazine> findAll();

    @Override
    Magazine save(Magazine magazine);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Magazine> findById(Long magazineId);

}
