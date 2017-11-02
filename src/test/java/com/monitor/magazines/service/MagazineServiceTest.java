package com.monitor.magazines.service;

import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.domain.Stage;
import com.monitor.magazines.repository.MagazineRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MagazineServiceTest {

    @Mock
    private MagazineService magazineService;

    /*@Mock
    private MagazineRepository magazineRepository;

    @Mock
    private StageService stageService;*/

    @Test
    public void testGetMagazines() {
        //Given
        Magazine magazine = new Magazine(1L, "Tytuł testowy", "0000-1234", 1999, 5, 1000L, 100L, 2, 0, 0);
        Magazine magazine1 = new Magazine(2L, "Tytuł testowy1", "4444-4321", 1956, 50, 5000L, 250L, 15, 10, 5);
        ArrayList magazinesList = new ArrayList<Magazine>();
        magazinesList.add(magazine);
        magazinesList.add(magazine1);
        //When
        when(magazineService.getMagazines()).thenReturn(magazinesList);
        //Then
        assertEquals(2, magazinesList.size());
    }

    @Test
    public void testSaveMagazine() {
        //Given
        Magazine magazine = new Magazine(1L, "Tytuł testowy", "0000-1234", 1999, 5, 1000L, 100L, 2, 0, 0);
        //When
        when(magazineService.saveMagazine(magazine)).thenReturn(magazine);
        //Then
        //assertEquals(1L, magazine.getId());
        assertEquals("Tytuł testowy", magazine.getTitle());

    }

    @Test
    public void testDeleteMagazine() {
        //Given
        Magazine magazine = new Magazine(1L, "Tytuł testowy", "0000-1234", 1999, 5, 1000L, 100L, 2, 0, 0);
        Long magazineId = magazine.getId();
        //When
        magazineService.deleteMagazine(magazineId);
        //Then
        verify(magazineService, times(1)).deleteMagazine(magazineId);
    }

    @Test
    public void testGetMagazine() {
        //Given
        Magazine magazine = new Magazine(1L, "Tytuł testowy", "0000-1234", 1999, 5, 1000L, 100L, 2, 0, 0);
        Long magazineId = magazine.getId();
        //When
        when(magazineService.getMagazine(magazineId)).thenReturn(magazine);
        //Then
        assertEquals("Tytuł testowy", magazine.getTitle());
        //assertEquals(1999, magazine.getFirstScannedYear());

    }

    @Test
    public void testGetPriceStartFor() {
        //Given
        Magazine magazine = new Magazine(1L, "Tytuł testowy", "0000-1234", 1999, 5, 1000L, 100L, 2, 0, 0);
        Stage stageExample = new Stage(2, "Description", 0.5);
        Long magazineId = magazine.getId();
        int stage = stageExample.getStage();
        double price = magazineService.getPriceStartFor(magazineId, stage);
        when(magazineService.getPriceStartFor(magazineId, stage)).thenReturn(price);
        //When

        //Then
    }

    @Test
    public void testGetPriceActualFor() {
        //Given
        //When
        //Then
    }

    @Test
    public void getTimeStartFor() throws Exception {
        //Given
        //When
        //Then
    }

    @Test
    public void getTimeActualFor() throws Exception {
        //Given
        //When
        //Then
    }

    @Test
    public void getQuantityAllVolumes() throws Exception {
        //Given
        //When
        //Then
    }

    @Test
    public void getQuantityAllVolumesToScanne() throws Exception {
        //Given
        //When
        //Then
    }

    @Test
    public void getQuantityAllReadyBigPdf() throws Exception {
        //Given
        //When
        //Then
    }

    @Test
    public void getQuantityAllBigPdfToDo() throws Exception {
        //Given
        //When
        //Then
    }

    @Test
    public void getQuantityAllReadySmallPdf() throws Exception {
        //Given
        //When
        //Then
    }

    @Test
    public void getQuantityAllSmallPdfToDo() throws Exception {
        //Given
        //When
        //Then
    }

    @Test
    public void getPriceAllStagesStart() throws Exception {
        //Given
        //When
        //Then
    }

    @Test
    public void getPriceAllStagesActual() throws Exception {
        //Given
        //When
        //Then
    }

    @Test
    public void getTimeAllStart() throws Exception {
        //Given
        //When
        //Then
    }

    @Test
    public void getTimeAllActual() throws Exception {
        //Given
        //When
        //Then
    }

}