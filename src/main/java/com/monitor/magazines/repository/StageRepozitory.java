package com.monitor.magazines.repository;


import com.monitor.magazines.domain.Stage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StageRepozitory extends CrudRepository<Stage, Integer> {
    @Override
    List<Stage> findAll();

    @Override
    Stage save(Stage stage);

    @Override
    Optional<Stage> findById(Integer stage);
}
