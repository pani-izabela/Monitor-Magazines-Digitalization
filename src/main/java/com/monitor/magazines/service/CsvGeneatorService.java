package com.monitor.magazines.service;

import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvGeneatorService {
    @Autowired
    private MagazineService magazineService;

    public void saveDataForSingelMagazine(Long magazineId){
        String csvFile = "report.csv";
        double priceDigitalizationOnStart = (magazineService.getPriceStartFor(magazineId, 1))
                + (magazineService.getPriceStartFor(magazineId, 2))
                + (magazineService.getPriceStartFor(magazineId, 3))
                + (magazineService.getPriceStartFor(magazineId, 4));

        double priceDigitalizationNow = (magazineService.getPriceActualFor(magazineId, 1))
                + (magazineService.getPriceActualFor(magazineId, 2))
                + (magazineService.getPriceActualFor(magazineId, 3))
                + (magazineService.getPriceActualFor(magazineId, 4));
        try{
            FileWriter writer = new FileWriter(csvFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            Magazine magazine = magazineService.getMagazine(magazineId);
            bufferedWriter.write("tytuł" + "," + "issn" + "," + "sinceWhenDigitalization" + "," + "priceDigitalizationOnStart" + "," + "priceDigitalizationNow");
            //czy to z kolejnej linii zapisze się w nowej linii?
            bufferedWriter.write(magazine.getTitle() + "," + magazine.getIssn() + "," + magazine.getFirstScannedYear() + "," + priceDigitalizationOnStart + "," + priceDigitalizationNow);
            bufferedWriter.close();

        }
        catch(IOException e){
            System.out.println("I cant create a file.");
        }
    }
}
//Co potem z tą metodą, do kontrolera??
//Jak to przetestować?
//gdzie ten plik się utworzy?