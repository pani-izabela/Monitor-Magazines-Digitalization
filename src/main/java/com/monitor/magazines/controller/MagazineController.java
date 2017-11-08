package com.monitor.magazines.controller;

import com.monitor.magazines.domain.MagazineDto;
import com.monitor.magazines.mapper.MagazineMapper;
import com.monitor.magazines.service.FileGeneratorService;
import com.monitor.magazines.service.MagazineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("monitor/digitization/magazines")
public class MagazineController {
    @Autowired
    private MagazineService magazineService;

    @Autowired
    private MagazineMapper magazineMapper;

    @Autowired
    private FileGeneratorService fileGeneratorService;

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

    @RequestMapping(method = RequestMethod.GET, value = "getPriceStartFor")
    public Double getPriceStartFor(@RequestParam Long magazineId, int stage){
        double price = magazineService.getPriceStartFor(magazineId, stage);
        return fileGeneratorService.makeTwoDecimalPlaces(price);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceActualFor")
    public Double getPriceActualFor(@RequestParam Long magazineId, int stage){
        double price = magazineService.getPriceActualFor(magazineId, stage);
        return fileGeneratorService.makeTwoDecimalPlaces(price);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeStartFor")
    public String getTimeStartFor(@RequestParam Long magazineId, int stage){
        double time = magazineService.getTimeStartFor(magazineId, stage);
        return convertDoubleToTime(time);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeActualFor")
    public String getTimeActualFor(@RequestParam Long magazineId, int stage){
        double time = magazineService.getTimeActualFor(magazineId, stage);
        return convertDoubleToTime(time);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllVolumes")
    public int getQuantityAllScannedVolumes(){
        return magazineService.getQuantityAllVolumes();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllScanedVolumes")
    public int getQuantityAllScanedVolumes(){
        return magazineService.getQuantityAllScanedVolumes();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllVolumesToScanne")
    public int getQuantityAllVolumesToScanne(){
        return magazineService.getQuantityAllVolumesToScanne();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllReadyBigPdf")
    public int getQuantityAllReadyBigPdf(){
        return magazineService.getQuantityAllReadyBigPdf();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllBigPdfToDo")
    public int getQuantityAllBigPdfToDo(){
        return magazineService.getQuantityAllBigPdfToDo();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllReadySmallPdf")
    public int getQuantityAllReadySmallPdf(){
        return magazineService.getQuantityAllReadySmallPdf();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityAllSmallPdfToDo")
    public int getQuantityAllSmallPdfToDo(){
        return magazineService.getQuantityAllSmallPdfToDo();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceAllStagesStart")
    public Double getPriceAllStagesStart(){
        double price = magazineService.getPriceAllStagesStart();
        return fileGeneratorService.makeTwoDecimalPlaces(price);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPriceAllStagesActual")
    public Double getPriceAllStagesActual(){
        double price = magazineService.getPriceAllStagesActual();
        return fileGeneratorService.makeTwoDecimalPlaces(price);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeAllStart")
    public String getTimeAllStart() {
        double time = magazineService.getTimeAllStart();
        return convertDoubleToTime(time);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTimeAllActual")
    public String getTimeAllActual(){
        double time = magazineService.getTimeAllActual();
        return convertDoubleToTime(time);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDataForSingelMagazine")
    public void getDataForSingelMagazine(@RequestParam Long magazineId, HttpServletResponse response){
        response.addHeader("content-type", "application/csv");
        response.addHeader("content-disposition", "attachment;filename=report.csv");
        fileGeneratorService.getDataForSingelMagazine(magazineId, response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDataForAllMagazine")
    public void getDataForAllMagazine(HttpServletResponse response){
        response.addHeader("content-type", "application/csv");
        response.addHeader("content-disposition", "attachment;filename=bigReport.csv");
        fileGeneratorService.getDataForAllMagazine(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDataForSingelMagazineToPdf")
    public void getDataForSingelMagazineToPdf(@RequestParam Long magazineId, HttpServletResponse response) throws IOException {
        response.addHeader("content-type", "application/pdf");
        response.addHeader("content-disposition", "attachment;filename=report.pdf");
        fileGeneratorService.getDataForSingelMagazineToPdf(magazineId, response);
    }

    private static String convertDoubleToTime(double time){
        int hours = (int)time;
        int minutes = (int)(time*60)%60;
        String hoursPlusMinutes = String.format("%s(h) %s(m)", hours, minutes);
        return hoursPlusMinutes;
    }
}
