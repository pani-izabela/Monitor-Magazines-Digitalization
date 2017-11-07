package com.monitor.magazines.service;

import com.monitor.magazines.domain.Stage;
import com.monitor.magazines.repository.StageRepozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {
    @Autowired
    private StageRepozitory stageRepozitory;


    /*public List<Stage> getStages(){
        return stageRepozitory.findAll();
    }

    czy ta metoda w ogóle jest mi potrzebna skoro nie mam tego w kontrolerze
    public Stage saveStage(Stage stage){
        return stageRepozitory.save(stage);
    }*/

    public Optional<Stage> getStage(Integer stage){
        return stageRepozitory.findById(stage);
    }

    public Double getPriceStage(Integer stage){
        Stage stage1 = getStage(stage).get();
        return stage1.getPrice();
    }
}
