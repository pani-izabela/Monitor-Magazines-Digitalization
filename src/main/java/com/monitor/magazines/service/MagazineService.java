package com.monitor.magazines.service;

import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.domain.Stage;
import com.monitor.magazines.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.PrintStream;
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

    public Double getPriceStartFor(Long magazineId, int stage){
        Magazine magazine = getMagazine(magazineId);
        double price=0.0;
        if(stage==1){
            price = magazine.getArticles() * stageService.getPriceStage(stage);
        }
        else if (stage==2){
            price =(magazine.getPagesToScann()/2) * stageService.getPriceStage(2);
        }
        else if(stage==3){
            price = (magazine.getVolumesToScann()) * stageService.getPriceStage(3);
        }
        else if(stage==4){
            price = magazine.getVolumesToScann() * stageService.getPriceStage(4);
        }
        return price;
    }

    public Double getPriceActualFor(Long magazineId, int stage){
        Magazine magazine = getMagazine(magazineId);
        double price = 0.0;
        if(stage==1){
            price = (((double)magazine.getArticles() / (double)magazine.getVolumesToScann()) * (magazine.getVolumesToScann() - magazine.getVolumesSmallPdf())) * stageService.getPriceStage(1);
        }
        else if (stage==2){
            price = (((double)magazine.getPagesToScann() / (double)magazine.getVolumesToScann()) * (magazine.getVolumesToScann() - magazine.getScannedVolumes()) * stageService.getPriceStage(2));
        }
        else if(stage==3){
            price = (magazine.getVolumesToScann() - magazine.getVolumesBigPdf()) * stageService.getPriceStage(3);
        }
        else if(stage==4){
            price = (magazine.getVolumesToScann() - magazine.getVolumesSmallPdf()) * stageService.getPriceStage(4);
        }

        return price;
    }

   //------------------------------------------------------------------

    public Double getTimeStartFor(Long magazineId, int stage){
        Magazine magazine = getMagazine(magazineId);
        double time = 0.0;
        if(stage==3){
            time = (double)magazine.getVolumesToScann() * 1;
        }
        else if(stage==4){
            time = ((double)magazine.getVolumesToScann() * 20) / 60;
        }
        return time;
    }

    public Double getTimeActualFor(Long magazineId, int stage){
        Magazine magazine = getMagazine(magazineId);
        double time = 0.0;
        if(stage==3){
            time = ((double)magazine.getVolumesToScann() - (double)magazine.getVolumesBigPdf()) * 1;
        }
        else if(stage==4){
            time = ((((double)magazine.getVolumesToScann() - (double)magazine.getVolumesSmallPdf()) * 20) / 60);
        }
        return time;
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
            double volume = magazine.getArticles() * stageService.getPriceStage(1)
                    + (magazine.getPagesToScann()/2) * stageService.getPriceStage(2)
                    + (magazine.getVolumesToScann()) * stageService.getPriceStage(3)
                    + (magazine.getVolumesToScann()) * stageService.getPriceStage(4);
            sumPriceStart += volume;
        }

        return sumPriceStart;
    }

    public Double getPriceAllStagesActual(){
        List<Magazine> magazines = getMagazines();
        double sumPriceActual = 0;
        for(Magazine magazine : magazines){
            double volume = (((double)magazine.getArticles() / (double)magazine.getVolumesToScann()) * (magazine.getVolumesToScann() - magazine.getVolumesSmallPdf())) * stageService.getPriceStage(1) +
                    ((double)magazine.getPagesToScann() / (double)magazine.getVolumesToScann()) * (magazine.getVolumesToScann() - magazine.getScannedVolumes()) * stageService.getPriceStage(2) +
                    (magazine.getVolumesToScann() - magazine.getVolumesBigPdf()) * stageService.getPriceStage(3) +
                    (magazine.getVolumesToScann() - magazine.getVolumesSmallPdf()) * stageService.getPriceStage(4);
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
