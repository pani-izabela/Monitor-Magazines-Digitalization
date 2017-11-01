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

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class CsvGeneatorService {
    @Autowired
    private MagazineService magazineService;

    public void saveDataForSingelMagazine(Long magazineId, HttpServletResponse response){
        String csvFile = "report.csv";
        double priceDigitalizationOnStart = (magazineService.getPriceStartFor(magazineId, 1))
                + (magazineService.getPriceStartFor(magazineId, 2))
                + (magazineService.getPriceStartFor(magazineId, 3))
                + (magazineService.getPriceStartFor(magazineId, 4));
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("0.00", decimalFormatSymbols);
        String doublePrice = decimalFormat.format(priceDigitalizationOnStart);
        priceDigitalizationOnStart = Double.valueOf(doublePrice);

        double priceDigitalizationNow = (magazineService.getPriceActualFor(magazineId, 1))
                + (magazineService.getPriceActualFor(magazineId, 2))
                + (magazineService.getPriceActualFor(magazineId, 3))
                + (magazineService.getPriceActualFor(magazineId, 4));
        DecimalFormatSymbols decimalFormatSymbols1 = new DecimalFormatSymbols(Locale.getDefault());
        decimalFormatSymbols1.setDecimalSeparator('.');
        DecimalFormat decimalFormat1 = new DecimalFormat("0.00", decimalFormatSymbols);
        String doublePrice1 = decimalFormat1.format(priceDigitalizationNow);
        priceDigitalizationNow = Double.valueOf(doublePrice1);

        try{
            FileWriter writer = new FileWriter(csvFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            Magazine magazine = magazineService.getMagazine(magazineId);
            bufferedWriter.write("Title" + "; " + "ISSN" + "; " + "First digitalized year's issue" + "; " + "Price of digitalization on start" + "; " + "Price of digitalization at the indicated time");
            bufferedWriter.newLine();
            bufferedWriter.write(magazine.getTitle() + "; " + magazine.getIssn() + "; " + magazine.getFirstScannedYear() + "; " + priceDigitalizationOnStart + " zł" + "; " + priceDigitalizationNow + " zł");
            bufferedWriter.close();

            File file = new File(csvFile);
            FileInputStream fileInputStream = new FileInputStream(file);
            IOUtils.copy(fileInputStream, response.getOutputStream());

        }
        catch(IOException e){
            System.out.println("I can't create file.");
        }
    }

    public void saveDataForSingelMagazineToPdf(Long magazineId, HttpServletResponse response) {
        String pdfFile = "report.pdf";
        double priceDigitalizationOnStart = (magazineService.getPriceStartFor(magazineId, 1))
                + (magazineService.getPriceStartFor(magazineId, 2))
                + (magazineService.getPriceStartFor(magazineId, 3))
                + (magazineService.getPriceStartFor(magazineId, 4));
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("0.00", decimalFormatSymbols);
        String doublePrice = decimalFormat.format(priceDigitalizationOnStart);
        priceDigitalizationOnStart = Double.valueOf(doublePrice);

        double priceDigitalizationNow = (magazineService.getPriceActualFor(magazineId, 1))
                + (magazineService.getPriceActualFor(magazineId, 2))
                + (magazineService.getPriceActualFor(magazineId, 3))
                + (magazineService.getPriceActualFor(magazineId, 4));
        DecimalFormatSymbols decimalFormatSymbols1 = new DecimalFormatSymbols(Locale.getDefault());
        decimalFormatSymbols1.setDecimalSeparator('.');
        DecimalFormat decimalFormat1 = new DecimalFormat("0.00", decimalFormatSymbols);
        String doublePrice1 = decimalFormat1.format(priceDigitalizationNow);
        priceDigitalizationNow = Double.valueOf(doublePrice1);

        Document document = new Document();


        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            Magazine magazine = magazineService.getMagazine(magazineId);

            document.open();
            document.add(new Paragraph("Title" + "; " + "ISSN" + "; " + "First digitalized year's issue" + "; " + "Price of digitalization on start" + "; " + "Price of digitalization at the indicated time"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(magazine.getTitle() + "; " + magazine.getIssn() + "; " + magazine.getFirstScannedYear() + "; " + priceDigitalizationOnStart + " zł" + "; " + priceDigitalizationNow + " zł"));
            document.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("I can't create pdf.");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    }


