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
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MagazineServiceTestSuite {

    @InjectMocks
    private MagazineService magazineService;

    @Mock
    private MagazineRepository magazineRepository;

    @Mock
    private StageService stageService;

    @Test
    public void testGetMagazines() {
        //Given
        Magazine magazine = new Magazine(1L, "Tytuł testowy", "0000-1234", 1999, 5, 1000L, 100L, 2, 0, 0);
        Magazine magazine1 = new Magazine(2L, "Tytuł testowy1", "4444-4321", 1956, 50, 5000L, 250L, 15, 10, 5);
        ArrayList<Magazine> magazinesList = new ArrayList<Magazine>();
        magazinesList.add(magazine);
        magazinesList.add(magazine1);
        when(magazineRepository.findAll()).thenReturn(magazinesList);
        //When
        int quatityMagazines = magazineService.getMagazines().size();
        //Then
        assertEquals(2, quatityMagazines);
    }

    @Test
    public void testSaveMagazine() {
        //Given
        Magazine magazine = new Magazine(1L, "Tytuł testowy", "0000-1234", 1999, 5, 1000L, 100L, 2, 0, 0);
        when(magazineRepository.save(magazine)).thenReturn(magazine);
        //When
        Magazine returnMagazine = magazineService.saveMagazine(magazine);
        //Then
        //assertEquals("1L", returnMagazine.getId());
        assertEquals("Tytuł testowy", returnMagazine.getTitle());

    }

    @Test
    public void testDeleteMagazine() {
        //Given
        Magazine magazine = new Magazine(1L, "Tytuł testowy", "0000-1234", 1999, 5, 1000L, 100L, 2, 0, 0);
        Long magazineId = magazine.getId();
        //When
        magazineService.deleteMagazine(magazineId);
        //Then
        verify(magazineRepository, times(1)).deleteById(magazineId);
    }

    @Test
    public void testGetMagazine() {
        //Given
        Magazine magazine = new Magazine(1L, "Tytuł testowy", "0000-1234", 1999, 5, 1000L, 100L, 2, 0, 0);
        Long magazineId = magazine.getId();
        when(magazineRepository.findById(magazineId)).thenReturn(Optional.of(magazine));
        //When
        Magazine returnMagazine = magazineService.getMagazine(magazineId);
        //Then
        assertEquals("Tytuł testowy", returnMagazine.getTitle());
        //assertEquals(1999, returnMagazine.getFirstScannedYear());

    }

    @Test
    public void testGetPriceStartFor() {
        //Given
        Magazine magazine = new Magazine(1L, "Tytuł testowy", "0000-1234", 1999, 5, 1000L, 100L, 2, 0, 0);
        Stage stageExample = new Stage(2, "Description", 0.5);
        Long id = magazine.getId();
        int stage = stageExample.getStage();
        double priceStage = stageExample.getPrice()*(magazine.getPagesToScann()/2);
        when(stageService.getPriceStage(stageExample.getStage())).thenReturn(priceStage);
        //When
        double price = magazineService.getPriceStartFor(id, stage);
        //Then
        assertEquals(250.00, price, 0.01);
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