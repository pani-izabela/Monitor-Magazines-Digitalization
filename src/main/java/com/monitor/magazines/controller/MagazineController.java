package com.monitor.magazines.controller;

import com.monitor.magazines.domain.MagazineDto;
import com.monitor.magazines.mapper.MagazineMapper;
import com.monitor.magazines.service.MagazineService;
import com.monitor.magazines.service.StageService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("monitor/digitalization/magazines")
public class MagazineController {
    @Autowired
    private MagazineService magazineService;
    @Autowired
    private MagazineMapper magazineMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getMagazines")
    public List<MagazineDto> getMagazines(){
        return magazineMapper.mapToMagazineDtoList(magazineService.getMagazines());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMagazine")
    public MagazineDto getMagazine(@RequestParam Long magazineId) {
        return magazineMapper.mapToMagazineDto(magazineService.getMagazine(magazineId));
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteMagazine")
    public void deleteMagazine(@RequestParam Long magazineId){
        magazineService.deleteMagazine(magazineId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateMagazine")
    public MagazineDto updateMagazine(@RequestBody MagazineDto magazineDto){
        return magazineMapper.mapToMagazineDto(magazineService.saveMagazine(magazineMapper.mapToMagazine(magazineDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createMagazine", consumes = APPLICATION_JSON_VALUE)
    public void createMagazine(@RequestBody MagazineDto magazineDto){
        magazineService.saveMagazine(magazineMapper.mapToMagazine(magazineDto));
    }

    //--------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStartFor")
    public Double getPriceStartFor(@RequestParam Long magazineId, int stage){
        return magazineService.getPriceStartFor(magazineId, stage);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceActualFor")
    public Double getPriceActualFor(@RequestParam Long magazineId, int stage){
        return magazineService.getPriceActualFor(magazineId, stage);
    }

    //--------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET, value = "getTimeStartFor")
    public Double getTimeStartFor(@RequestParam Long magazineId, int stage){
        return magazineService.getTimeStartFor(magazineId, stage);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeActualFor")
    public Double getTimeActualFor(@RequestParam Long magazineId, int stage){
        return magazineService.getTimeActualFor(magazineId, stage);
    }

    //--------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllVolumes")
    public Integer getQuantityAllScannedVolumes(){
        return magazineService.getQuantityAllVolumes();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllVolumesToScanne")
    public Integer getQuantityAllVolumesToScanne(){
        return magazineService.getQuantityAllVolumesToScanne();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllReadyBigPdf")
    public Integer getQuantityAllReadyBigPdf(){
        return magazineService.getQuantityAllReadyBigPdf();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllBigPdfToDo")
    public Integer getQuantityAllBigPdfToDo(){
        return magazineService.getQuantityAllBigPdfToDo();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllReadySmallPdf")
    public Integer getQuantityAllReadySmallPdf(){
        return magazineService.getQuantityAllReadySmallPdf();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllSmallPdfToDo")
    public Integer getQuantityAllSmallPdfToDo(){
        return magazineService.getQuantityAllSmallPdfToDo();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceAllStagesStart")
    public Double getPriceAllStagesStart(){
        return magazineService.getPriceAllStagesStart();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceAllStagesActual")
    public Double getPriceAllStagesActual(){
        return magazineService.getPriceAllStagesActual();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeAllStart")
    public Double getTimeAllStart(){
        return magazineService.getTimeAllStart();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeAllActual")
    public Double getTimeAllActual(){
        return magazineService.getTimeAllActual();
    }


}
