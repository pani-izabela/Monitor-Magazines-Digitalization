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

    private static void createFile(String nameFile, HttpServletResponse response) throws IOException {
        File file = new File(nameFile);
        FileInputStream fileInputStream = new FileInputStream(file);
        IOUtils.copy(fileInputStream, response.getOutputStream());
        fileInputStream.close();
    }

    public void getDataForSingelMagazine(Long magazineId, HttpServletResponse response){
        String csvFile = "report.csv";
        double pricedigitizationOnStart = (magazineService.getPriceStartFor(magazineId, 1))
                + (magazineService.getPriceStartFor(magazineId, 2))
                + (magazineService.getPriceStartFor(magazineId, 3))
                + (magazineService.getPriceStartFor(magazineId, 4));
        double startPriceMagazine = makeTwoDecimalPlaces(pricedigitizationOnStart);

        double pricedigitizationNow = (magazineService.getPriceActualFor(magazineId, 1))
                + (magazineService.getPriceActualFor(magazineId, 2))
                + (magazineService.getPriceActualFor(magazineId, 3))
                + (magazineService.getPriceActualFor(magazineId, 4));
        double actualPriceMagazine = makeTwoDecimalPlaces(pricedigitizationNow);

        try{
            FileWriter writer = new FileWriter(csvFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            Magazine magazine = magazineService.getMagazine(magazineId);
            bufferedWriter.write("Title" + "; " + "ISSN" + "; " + "First digitalized year's issue" + "; " + "Price of digitization on start(PLN)" + "; " + "Price of digitization at the indicated time(PLN)");
            bufferedWriter.write("\n");
            bufferedWriter.write(magazine.getTitle() + "; " + magazine.getIssn() + "; " + magazine.getFirstScannedYear() + "; " + startPriceMagazine + "; " + actualPriceMagazine);
            bufferedWriter.close();

            createFile(csvFile, response);
        }
        catch(IOException e){
            System.out.println("I can't create file.");
        }
    }

    public void getDataForAllMagazine(HttpServletResponse response){
        String csvFile = "bigReport.csv";

        List<Magazine> magazines = magazineService.getMagazines();

        double pricedigitizationAllOnStart = (magazineService.getPriceAllStagesStart());
        double priceForAllOnStart = makeTwoDecimalPlaces(pricedigitizationAllOnStart);

        double pricedigitizationAllNow = (magazineService.getPriceAllStagesActual());
        double priceForAllNow = makeTwoDecimalPlaces(pricedigitizationAllNow);

        try{
            FileWriter writer = new FileWriter(csvFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("Title" + "; " + "ISSN" + "; " + "Price of digitization on start" + "; " + "Price of digitization at the indicated time");
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

            createFile(csvFile, response);
        }
        catch(IOException e){
            System.out.println("I can't create file.");
        }
    }

    public void getDataForSingelMagazineToPdf(Long magazineId, HttpServletResponse response) {
        String pdfFile = "report.pdf";
        double pricedigitizationOnStart = (magazineService.getPriceStartFor(magazineId, 1))
                + (magazineService.getPriceStartFor(magazineId, 2))
                + (magazineService.getPriceStartFor(magazineId, 3))
                + (magazineService.getPriceStartFor(magazineId, 4));
        double startPriceMagazine = makeTwoDecimalPlaces(pricedigitizationOnStart);

        double pricedigitizationNow = (magazineService.getPriceActualFor(magazineId, 1))
                + (magazineService.getPriceActualFor(magazineId, 2))
                + (magazineService.getPriceActualFor(magazineId, 3))
                + (magazineService.getPriceActualFor(magazineId, 4));
        double actualPriceMagazine = makeTwoDecimalPlaces(pricedigitizationNow);

        try {
            Document document = new Document();
            Magazine magazine = magazineService.getMagazine(magazineId);
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

            document.open();
            Font fontHead = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLUE);
            Font fontBody = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
            Font fontSpace = FontFactory.getFont(FontFactory.TIMES, 8);
            document.add(new Paragraph("Report costs of digitization for single magazin: ", fontHead));
            document.add(new Paragraph("\n", fontSpace));
            document.add(new Paragraph("Title: " + magazine.getTitle(), fontBody));
            document.add(new Paragraph("\n", fontSpace));
            document.add(new Paragraph("ISSN: " + magazine.getIssn(), fontBody));
            document.add(new Paragraph("\n", fontSpace));
            document.add(new Paragraph("First digitalized year's issue: " + magazine.getFirstScannedYear(), fontBody));
            document.add(new Paragraph("\n", fontSpace));
            document.add(new Paragraph("Price of digitization on start: " + startPriceMagazine + " PLN", fontBody));
            document.add(new Paragraph("\n", fontSpace));
            document.add(new Paragraph("Price of digitization at the indicated time: " + actualPriceMagazine + " PLN", fontBody));
            document.close();

            createFile(pdfFile, response);

        } catch (IOException e) {
            System.out.println("I can't create pdf.");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}


