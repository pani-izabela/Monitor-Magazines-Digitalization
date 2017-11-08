package com.monitor.magazines.service;

import com.monitor.magazines.domain.Magazine;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class FileGeneratorService {
    @Autowired
    private MagazineService magazineService;

    public static Double makeTwoDecimalPlaces(double number){
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("0.00", decimalFormatSymbols);
        String doubleNumber = decimalFormat.format(number);
        return number = Double.valueOf(doubleNumber);
    }


    public void getDataForSingelMagazine(Long magazineId, HttpServletResponse response){
        String csvFile = "report.csv";
        double priceDigitalizationOnStart = (magazineService.getPriceStartFor(magazineId, 1))
                + (magazineService.getPriceStartFor(magazineId, 2))
                + (magazineService.getPriceStartFor(magazineId, 3))
                + (magazineService.getPriceStartFor(magazineId, 4));
        double startPriceMagazine = makeTwoDecimalPlaces(priceDigitalizationOnStart);

        double priceDigitalizationNow = (magazineService.getPriceActualFor(magazineId, 1))
                + (magazineService.getPriceActualFor(magazineId, 2))
                + (magazineService.getPriceActualFor(magazineId, 3))
                + (magazineService.getPriceActualFor(magazineId, 4));
        double actualPriceMagazine = makeTwoDecimalPlaces(priceDigitalizationNow);

        try{
            FileWriter writer = new FileWriter(csvFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            Magazine magazine = magazineService.getMagazine(magazineId);
            bufferedWriter.write("Title" + "; " + "ISSN" + "; " + "First digitalized year's issue" + "; " + "Price of digitalization on start" + "; " + "Price of digitalization at the indicated time");
            bufferedWriter.write("\n");
            bufferedWriter.write(magazine.getTitle() + "; " + magazine.getIssn() + "; " + magazine.getFirstScannedYear() + "; " + startPriceMagazine + " PLN" + "; " + actualPriceMagazine + " PLN");
            bufferedWriter.close();

            File file = new File(csvFile);
            FileInputStream fileInputStream = new FileInputStream(file);
            IOUtils.copy(fileInputStream, response.getOutputStream());
            fileInputStream.close();
        }
        catch(IOException e){
            System.out.println("I can't create file.");
        }
    }

    public void getDataForAllMagazine(HttpServletResponse response){
        String csvFile = "bigReport.csv";

        List<Magazine> magazines = magazineService.getMagazines();

        double priceDigitalizationAllOnStart = (magazineService.getPriceAllStagesStart());
        double priceForAllOnStart = makeTwoDecimalPlaces(priceDigitalizationAllOnStart);

        double priceDigitalizationAllNow = (magazineService.getPriceAllStagesActual());
        double priceForAllNow = makeTwoDecimalPlaces(priceDigitalizationAllNow);


        try{
            FileWriter writer = new FileWriter(csvFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("Title" + "; " + "ISSN" + "; " + "Price of digitalization on start" + "; " + "Price of digitalization at the indicated time");
            bufferedWriter.write("\n");


            magazines.forEach(magazine -> {
                try {
                    bufferedWriter.write(magazine.getTitle() + "; " + magazine.getIssn() + "; " + makeTwoDecimalPlaces(magazineService.getPriceStartFor(magazine.getId(), 0)) + "; " + makeTwoDecimalPlaces(magazineService.getPriceActualFor(magazine.getId(), 0)) + "\n");
                } catch (IOException e) {
                    System.out.println("I can't save magazine. " + magazine.getId());
                }
            });
            bufferedWriter.write("" + "; " + "Sum: " + "; " + priceForAllOnStart + "; " + priceForAllNow);
            bufferedWriter.close();

            File file = new File(csvFile);
            FileInputStream fileInputStream = new FileInputStream(file);
            IOUtils.copy(fileInputStream, response.getOutputStream());
            fileInputStream.close();
        }
        catch(IOException e){
            System.out.println("I can't create file.");
        }
    }

    public void getDataForSingelMagazineToPdf(Long magazineId, HttpServletResponse response) {
        String pdfFile = "report.pdf";
        double priceDigitalizationOnStart = (magazineService.getPriceStartFor(magazineId, 1))
                + (magazineService.getPriceStartFor(magazineId, 2))
                + (magazineService.getPriceStartFor(magazineId, 3))
                + (magazineService.getPriceStartFor(magazineId, 4));
        double startPriceMagazine = makeTwoDecimalPlaces(priceDigitalizationOnStart);

        double priceDigitalizationNow = (magazineService.getPriceActualFor(magazineId, 1))
                + (magazineService.getPriceActualFor(magazineId, 2))
                + (magazineService.getPriceActualFor(magazineId, 3))
                + (magazineService.getPriceActualFor(magazineId, 4));
        double actualPriceMagazine = makeTwoDecimalPlaces(priceDigitalizationNow);


        try {
            Document document = new Document();
            Magazine magazine = magazineService.getMagazine(magazineId);
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

            document.open();
            Font fontHead = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLUE);
            Font fontBody = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
            Font fontSpace = FontFactory.getFont(FontFactory.TIMES, 8);
            document.add(new Paragraph("Report costs of digitalization for single magazin: ", fontHead));
            document.add(new Paragraph("\n", fontSpace));
            document.add(new Paragraph("Title: " + magazine.getTitle(), fontBody));
            document.add(new Paragraph("\n", fontSpace));
            document.add(new Paragraph("ISSN: " + magazine.getIssn(), fontBody));
            document.add(new Paragraph("\n", fontSpace));
            document.add(new Paragraph("First digitalized year's issue: " + magazine.getFirstScannedYear(), fontBody));
            document.add(new Paragraph("\n", fontSpace));
            document.add(new Paragraph("Price of digitalization on start: " + startPriceMagazine + " PLN", fontBody));
            document.add(new Paragraph("\n", fontSpace));
            document.add(new Paragraph("Price of digitalization at the indicated time: " + actualPriceMagazine + " PLN", fontBody));
            document.close();

            File file = new File(pdfFile);
            FileInputStream fileInputStream = new FileInputStream(file);
            IOUtils.copy(fileInputStream, response.getOutputStream());
            fileInputStream.close();

        } catch (IOException e) {
            System.out.println("I can't create pdf.");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    }


