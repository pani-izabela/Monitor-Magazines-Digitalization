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

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIStart")
    public Double getPriceStageIStart(@RequestParam Long magazineId){
        return magazineService.getPriceStageIStart(magazineId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getEstimatedPriceStageIActual")
    public Double getEstimatedPriceStageIActual(@RequestParam Long magazineId){
        return magazineService.getEstimatedPriceStageIActual(magazineId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIIStart")
    public Double getPriceStageIIStart(@RequestParam Long magazineId){
        return magazineService.getPriceStageIIStart(magazineId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getEstimatedPriceStageIIActual")
    public Double getEstimatedPriceStageIIActual(@RequestParam Long magazineId){
        return magazineService.getEstimatedPriceStageIIActual(magazineId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIIIStart")
    public Double getPriceStageIIIStart(@RequestParam Long magazineId){
        return magazineService.getPriceStageIIIStart(magazineId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIIIActual")
    public Double getPriceStageIIIActual(@RequestParam Long magazineId){
        return magazineService.getPriceStageIIIActual(magazineId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIVStart")
    public Double getPriceStageIVStart(@RequestParam Long magazineId){
        return magazineService.getPriceStageIVStart(magazineId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIVActual")
    public Double getPriceStageIVActual(@RequestParam Long magazineId){
        return magazineService.getPriceStageIVActual(magazineId);
    }

    //--------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET, value = "getTimeStageIIIStart")
    public Double getTimeStageIIIStart(@RequestParam Long magazineId){
        return magazineService.getTimeStageIIIStart(magazineId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeStageIIIActual")
    public Double getTimeStageIIIActual(@RequestParam Long magazineId){
        return magazineService.getTimeStageIIIActual(magazineId);
    }
    //zamienić double na Time
    @RequestMapping(method = RequestMethod.GET, value = "getTimeStageIVStart")
    public Double getTimeStageIVStart(@RequestParam Long magazineId){
        return magazineService.getTimeStageIVStart(magazineId);
    }
    //zamienić double na Time
    @RequestMapping(method = RequestMethod.GET, value = "getTimeStageIVActual")
    public Double getTimeStageIVActual(@RequestParam Long magazineId){
        return magazineService.getTimeStageIVActual(magazineId);
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
