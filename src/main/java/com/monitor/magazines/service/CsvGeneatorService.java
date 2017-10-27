package com.monitor.magazines.service;

import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.repository.MagazineRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvGeneatorService {
    @Autowired
    private MagazineService magazineService;
//powinien być File nie void
    public void saveDataForSingelMagazine(Long magazineId, HttpServletResponse response){
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
            bufferedWriter.write("title" + "," + "issn" + "," + "sinceWhenDigitalization" + "," + "priceDigitalizationOnStart" + "," + "priceDigitalizationNow");
            bufferedWriter.newLine();
            bufferedWriter.write(magazine.getTitle() + "," + magazine.getIssn() + "," + magazine.getFirstScannedYear() + "," + priceDigitalizationOnStart + "," + priceDigitalizationNow);
            bufferedWriter.close();

            File file = new File(csvFile);
            FileInputStream fileInputStream = new FileInputStream(file);
            IOUtils.copy(fileInputStream, response.getOutputStream());

        }
        catch(IOException e){
            System.out.println("I can't create a file.");
        }
    }
}

    /*public File saveDataForSingelMagazine(Long magazineId){
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
            bufferedWriter.newLine();
            //czy to z kolejnej linii zapisze się w nowej linii?
            bufferedWriter.write(magazine.getTitle() + "," + magazine.getIssn() + "," + magazine.getFirstScannedYear() + "," + priceDigitalizationOnStart + "," + priceDigitalizationNow);
            bufferedWriter.close();

            File file = new File(csvFile);
            boolean fileExist = file.exists();
            try{
                if(!fileExist){
                    fileExist = file.createNewFile();
                }

            }
            catch(IOException e){

            }

        }
        catch(IOException e){
            System.out.println("I cant create a file.");
        }

        return new File("C:\\Users\\430\\Documents\\Development\\"+csvFile);
    }
}*/
