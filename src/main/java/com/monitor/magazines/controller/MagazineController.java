package com.monitor.magazines.controller;

import com.monitor.magazines.domain.MagazineDto;
import com.monitor.magazines.mapper.MagazineMapper;
import com.monitor.magazines.service.MagazineService;
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

    /*@RequestMapping(method = RequestMethod.GET, value = "getMagazine")
    public MagazineDto getMagazine(@RequestParam Long magazineId){
        return magazineMapper.mapToMagazineDto(magazineService.getMagazine(magazineId));*/

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
    @RequestMapping(method = RequestMethod.GET, value = "getQuantityScannedVolumes")
    public Integer getQuantityScannedVolumes(Long magazineId){
        return 1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityVolumesToScann")
    public Integer getQuantityVolumesToScann(Long magazineId){
        return 1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityReadyBigPdf")
    public Integer getQuantityReadyBigPdf(Long magazineId){
        return 1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityBigPdfToDo")
    public Integer getQuantityBigPdfToDo(Long magazineId){
        return 1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityReadySmallPdf")
    public Integer getQuantityReadySmallPdf(Long magazineId){
        return 1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantitySmallPdfToDo")
    public Integer getQuantitySmallPdfToDo(Long magazineId){
        return 1;
    }
    //--------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIStart")
    public Double getPriceStageIStart(Long magazineId){
        return 1.1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIActual")
    public Double getPriceStageIActual(Long magazineId){
        return 1.1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIIStart")
    public Double getPriceStageIIStart(Long magazineId){
        return 1.1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIIActual")
    public Double getPriceStageIIActual(Long magazineId){
        return 1.1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIIIStart")
    public Double getPriceStageIIIStart(Long magazineId){
        return 1.1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIIIActual")
    public Double getPriceStageIIIActual(Long magazineId){
        return 1.1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIVStart")
    public Double getPriceStageIVStart(Long magazineId){
        return 1.1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStageIVActual")
    public Double getPriceStageIVActual(Long magazineId){
        return 1.1;
    }

    //--------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET, value = "getTimeStageIIIStart")
    public Double getTimeStageIIIStart(Long magazineId){
        return 1.1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeStageIIIActual")
    public Double getTimeStageIIIActual(Long magazineId){
        return 1.1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeStageIVStart")
    public Double getTimeStageIVStart(Long magazineId){
        return 1.1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeStageIVActual")
    public Double getTimeStageIVActual(Long magazineId){
        return 1.1;
    }

    //--------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllScannedVolumes")
    public Integer getQuantityAllScannedVolumes(){
        return 2;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllVolumesToScanne")
    public Integer getQuantityAllVolumesToScanne(){
        return 2;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllReadyBigPdf")
    public Integer getQuantityAllReadyBigPdf(){
        return 2;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllBigPdfToDo")
    public Integer getQuantityAllBigPdfToDo(){
        return 2;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllReadySmallPdf")
    public Integer getQuantityAllReadySmallPdf(){
        return 2;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllSmallPdfToDo")
    public Integer getQuantityAllSmallPdfToDo(){
        return 2;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceAllStagesStart")
    public Double getPriceAllStagesStart(){
        return 2.2;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceAllStagesActual")
    public Double getPriceAllStagesActual(){
        return 2.2;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeAllStart")
    public Double getTimeAllStart(){
        return 2.2;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeAllActual")
    public Double getTimeAllActual(){
        return 2.2;
    }


}
