package com.monitor.magazines.service;

import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class MagazineService {

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private StageService stageService;

    public List<Magazine> getMagazines(){
        return magazineRepository.findAll();
    }

    public Magazine saveMagazine(final Magazine magazine){
        return magazineRepository.save(magazine);
    }

    public void deleteMagazine(final Long magazineId){
        magazineRepository.deleteById(magazineId);
    }

    public Magazine getMagazine(final Long magazineId){
        return magazineRepository.findById(magazineId).get();
    }

    //------------------------------------------------------------------

    public Double getPriceStageIStart(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        stageService.getPriceStage(1);
        return magazine.getArticles() * stageService.getPriceStage(1);
    }

    public Double getEstimatedPriceStageIActual(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        return (((double)magazine.getArticles() / (double)magazine.getVolumesToScann()) * (magazine.getVolumesToScann() - magazine.getVolumesSmallPdf())) * 0.6;
    }

    public Double getPriceStageIIStart(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        return (magazine.getPagesToScann()/2) * 0.5;
    }

    public Double getEstimatedPriceStageIIActual(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        return (((double)magazine.getPagesToScann() / (double)magazine.getVolumesToScann()) * (magazine.getVolumesToScann() - magazine.getScannedVolumes()) * 0.5);
    }

    public Double getPriceStageIIIStart(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        return (magazine.getVolumesToScann()) * 18.75;
    }

    public Double getPriceStageIIIActual(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        return (magazine.getVolumesToScann() - magazine.getVolumesBigPdf()) * 18.75;
    }

    public Double getPriceStageIVStart(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        return (magazine.getVolumesToScann()) * 6.25;
    }

    public Double getPriceStageIVActual(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        return (magazine.getVolumesToScann() - magazine.getVolumesSmallPdf()) * 6.25;
    }

    //------------------------------------------------------------------

    public Double getTimeStageIIIStart(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        return (double)magazine.getVolumesToScann() * 1;
    }

    public Double getTimeStageIIIActual(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        return ((double)magazine.getVolumesToScann() - (double)magazine.getVolumesBigPdf()) * 1;
    }

    public Double getTimeStageIVStart(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        return ((double)magazine.getVolumesToScann() * 20) / 60;
    }

    public Double getTimeStageIVActual(Long magazineId){
        Magazine magazine = getMagazine(magazineId);
        return ((((double)magazine.getVolumesToScann() - (double)magazine.getVolumesSmallPdf()) * 20) / 60);
    }

    //---------------------------------------------------------------------

    public Integer getQuantityAllVolumes(){
        List<Magazine> magazines = getMagazines();
        return (magazines.stream().mapToInt(Magazine::getVolumesToScann).sum());
    }

    public Integer getQuantityAllVolumesToScanne(){
        List<Magazine> magazines = getMagazines();
        Integer sum = 0;
        for(Magazine magazine : magazines){
            int volume = magazine.getVolumesToScann() - magazine.getScannedVolumes();
            sum += volume;
        }
        return sum;
    }

    public Integer getQuantityAllReadyBigPdf(){
        List<Magazine> magazines = getMagazines();
        return (magazines.stream().mapToInt(Magazine::getVolumesBigPdf).sum());
    }

    public Integer getQuantityAllBigPdfToDo(){
        List<Magazine> magazines = getMagazines();
        Integer sum = 0;
        for(Magazine magazine : magazines){
            int volume = magazine.getVolumesToScann() - magazine.getVolumesBigPdf();
            sum += volume;
        }
        return sum;
    }

    public Integer getQuantityAllReadySmallPdf(){
        List<Magazine> magazines = getMagazines();
        return (magazines.stream().mapToInt(Magazine::getVolumesSmallPdf).sum());
    }

    public Integer getQuantityAllSmallPdfToDo(){
        List<Magazine> magazines = getMagazines();
        Integer sum = 0;
        for(Magazine magazine : magazines){
            int volume = magazine.getVolumesToScann() - magazine.getVolumesSmallPdf();
            sum += volume;
        }
        return sum;
    }

    public Double getPriceAllStagesStart(){
        List<Magazine> magazines = getMagazines();
        double sumPriceStart = 0;
        for(Magazine magazine : magazines){
            double volume = magazine.getArticles() * 0.6
                    + (magazine.getPagesToScann()/2) * 0.5
                    + (magazine.getVolumesToScann()) * 18.75
                    + (magazine.getVolumesToScann()) * 6.25;
            sumPriceStart += volume;
        }

        return sumPriceStart;
    }

    public Double getPriceAllStagesActual(){
        List<Magazine> magazines = getMagazines();
        double sumPriceActual = 0;
        for(Magazine magazine : magazines){
            double volume = (((double)magazine.getArticles() / (double)magazine.getVolumesToScann()) * (magazine.getVolumesToScann() - magazine.getVolumesSmallPdf())) * 0.6 +
                    ((double)magazine.getPagesToScann() / (double)magazine.getVolumesToScann()) * (magazine.getVolumesToScann() - magazine.getScannedVolumes()) * 0.5 +
                    (magazine.getVolumesToScann() - magazine.getVolumesBigPdf()) * 18.75 +
                    (magazine.getVolumesToScann() - magazine.getVolumesSmallPdf()) * 6.25;
            sumPriceActual += volume;
        }
        return sumPriceActual;
    }

    public Double getTimeAllStart(){
        List<Magazine> magazines = getMagazines();
        double sumTimeStart = 0;
        for(Magazine magazine : magazines){
            double time = (double)magazine.getVolumesToScann() * 1
                    + ((double)magazine.getVolumesToScann() * 20) / 60;
            sumTimeStart += time;
        }
        return sumTimeStart;
    }

    public Double getTimeAllActual(){
        List<Magazine> magazines = getMagazines();
        double sumTimeActual = 0;
        for(Magazine magazine : magazines){
            double time = ((double)magazine.getVolumesToScann() - (double)magazine.getVolumesBigPdf()) * 1
                    + ((((double)magazine.getVolumesToScann() - (double)magazine.getVolumesSmallPdf()) * 20) / 60);
            sumTimeActual += time;
        }
        return sumTimeActual;
    }
}
