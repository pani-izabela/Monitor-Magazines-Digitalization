package com.monitor.magazines.service;

import com.monitor.magazines.domain.Stage;
import com.monitor.magazines.repository.StageRepozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StageService {
    @Autowired
    private StageRepozitory stageRepozitory;

    public Optional<Stage> getStage(Integer stage){
        return stageRepozitory.findById(stage);
    }

    public Double getPriceStage(Integer stage){
        Stage stageNumber = getStage(stage).get();
        return stageNumber.getPrice();
    }
}
