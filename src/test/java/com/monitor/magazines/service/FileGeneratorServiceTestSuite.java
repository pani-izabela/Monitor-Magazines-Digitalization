package com.monitor.magazines.service;

import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.domain.Stage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileGeneratorServiceTestSuite {

    @InjectMocks
    private FileGeneratorService fileGeneratorService;

    @Mock
    private MagazineService magazineService;

    @Test
    public void testGetDataForSingelMagazine() throws UnsupportedEncodingException {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        when(magazineService.getMagazine(magazine1.getId())).thenReturn(magazine1);

        Stage stageExample1 = new Stage(1, "Description1", 0.6);
        Stage stageExample2 = new Stage(2, "Description2", 0.5);
        Stage stageExample3 = new Stage(3, "Description3", 18.75);
        Stage stageExample4 = new Stage(4, "Description4", 6.25);

        when(magazineService.getPriceStartFor(magazine1.getId(),stageExample1.getStage())).thenReturn(36.0);
        when(magazineService.getPriceStartFor(magazine1.getId(),stageExample2.getStage())).thenReturn(75.0);
        when(magazineService.getPriceStartFor(magazine1.getId(),stageExample3.getStage())).thenReturn(56.25);
        when(magazineService.getPriceStartFor(magazine1.getId(),stageExample4.getStage())).thenReturn(18.75);

        when(magazineService.getPriceActualFor(magazine1.getId(),stageExample1.getStage())).thenReturn(12.00);
        when(magazineService.getPriceActualFor(magazine1.getId(),stageExample2.getStage())).thenReturn(0.0);
        when(magazineService.getPriceActualFor(magazine1.getId(),stageExample3.getStage())).thenReturn(18.75);
        when(magazineService.getPriceActualFor(magazine1.getId(),stageExample4.getStage())).thenReturn(6.25);

        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        //When
        fileGeneratorService.getDataForSingelMagazine(magazine1.getId(), mockHttpServletResponse);

        //Then
        String contentString = mockHttpServletResponse.getContentAsString();
        assertEquals("Title; ISSN; First digitalized year's issue; Price of digitization on start(PLN); Price of digitization at the indicated time(PLN)\nTytul testowy1; 4444-7890; 2009; 186.0; 37.0", contentString);
    }

    @Test
    public void testGetDataForSingelMagazineToPdf() throws UnsupportedEncodingException {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        when(magazineService.getMagazine(magazine1.getId())).thenReturn(magazine1);

        Stage stageExample1 = new Stage(1, "Description1", 0.6);
        Stage stageExample2 = new Stage(2, "Description2", 0.5);
        Stage stageExample3 = new Stage(3, "Description3", 18.75);
        Stage stageExample4 = new Stage(4, "Description4", 6.25);

        when(magazineService.getPriceStartFor(magazine1.getId(), stageExample1.getStage())).thenReturn(36.0);
        when(magazineService.getPriceStartFor(magazine1.getId(), stageExample2.getStage())).thenReturn(75.0);
        when(magazineService.getPriceStartFor(magazine1.getId(), stageExample3.getStage())).thenReturn(56.25);
        when(magazineService.getPriceStartFor(magazine1.getId(), stageExample4.getStage())).thenReturn(18.75);

        when(magazineService.getPriceActualFor(magazine1.getId(), stageExample1.getStage())).thenReturn(12.00);
        when(magazineService.getPriceActualFor(magazine1.getId(), stageExample2.getStage())).thenReturn(0.0);
        when(magazineService.getPriceActualFor(magazine1.getId(), stageExample3.getStage())).thenReturn(18.75);
        when(magazineService.getPriceActualFor(magazine1.getId(), stageExample4.getStage())).thenReturn(6.25);

        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        //When
        fileGeneratorService.getDataForSingelMagazineToPdf(magazine1.getId(), mockHttpServletResponse);

        //Then
        int status = mockHttpServletResponse.getStatus();
        assertEquals(200, status);
    }
}