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
        assertEquals(1, (long)returnMagazine.getId());
        assertEquals(5, (int)returnMagazine.getVolumesToScann());
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
        assertEquals(1999, (int)returnMagazine.getFirstScannedYear());

    }

    //---------------------------------------------------------------------

    @Test
    public void testGetPriceStartFor() {
        //Given
        Magazine magazine = new Magazine(3L, "Tytuł testowy", "0000-1234", 1999, 10, 2500L, 100L, 5, 5, 5);
        Stage stageExample = new Stage(1, "Description", 0.6);
        Long id = magazine.getId();
        int stage = stageExample.getStage();
        when(stageService.getPriceStage(stageExample.getStage())).thenReturn(stageExample.getPrice());
        when(magazineRepository.findById(magazine.getId())).thenReturn(Optional.of(magazine));
        //When
        double price = magazineService.getPriceStartFor(id, stage);
        //Then
        assertEquals(60, price, 0);
    }

    @Test
    public void testGetPriceActualFor() {
        //Given
        Magazine magazine = new Magazine(3L, "Tytuł testowy", "0000-1234", 1999, 10, 2500L, 100L, 5, 5, 5);
        Stage stageExample = new Stage(1, "Description", 0.6);
        Long id = magazine.getId();
        int stage = stageExample.getStage();
        when(stageService.getPriceStage(stageExample.getStage())).thenReturn(stageExample.getPrice());
        when(magazineRepository.findById(magazine.getId())).thenReturn(Optional.of(magazine));
        //When
        double price = magazineService.getPriceActualFor(id,stage);
        //Then
        assertEquals(30, price, 0);
    }

    //---------------------------------------------------------------------
    @Test
    public void testGetTimeStartFor() {
        //Given
        Magazine magazine = new Magazine(3L, "Tytuł testowy", "0000-1234", 1999, 10, 2500L, 100L, 5, 5, 5);
        Stage stageExample = new Stage(3, "Description", 18.75);
        Long id = magazine.getId();
        int stage = stageExample.getStage();
        when(magazineRepository.findById(magazine.getId())).thenReturn(Optional.of(magazine));
        //When
        double time = magazineService.getTimeStartFor(id, stage);
        //Then
        assertEquals(10, time, 0);
    }

    @Test
    public void testGetTimeActualFor() {
        //Given
        Magazine magazine = new Magazine(3L, "Tytuł testowy", "0000-1234", 1999, 10, 2500L, 100L, 5, 5, 5);
        Stage stageExample = new Stage(3, "Description", 18.75);
        Long id = magazine.getId();
        int stage = stageExample.getStage();
        when(magazineRepository.findById(magazine.getId())).thenReturn(Optional.of(magazine));
        //When
        double time = magazineService.getTimeActualFor(id, stage);
        //Then
        assertEquals(5, time, 0);
    }

    //---------------------------------------------------------------------
    @Test
    public void testGetQuantityAllVolumes() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);

        ArrayList<Magazine> magazinesList = new ArrayList<>();
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);
        magazinesList.add(magazine3);

        when(magazineRepository.findAll()).thenReturn(magazinesList);
        //When
        int quantity = magazineService.getQuantityAllVolumes();
        //Then
        assertEquals(25, quantity);
    }

    @Test
    public void testGetQuantityAllScanedVolumes() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);

        ArrayList<Magazine> magazinesList = new ArrayList<>();
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);
        magazinesList.add(magazine3);

        when(magazineRepository.findAll()).thenReturn(magazinesList);
        //When
        int quantity = magazineService.getQuantityAllScanedVolumes();
        //Then
        assertEquals(10, quantity);
    }

    @Test
    public void testGetQuantityAllVolumesToScanne() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);

        ArrayList<Magazine> magazinesList = new ArrayList<>();
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);
        magazinesList.add(magazine3);

        when(magazineRepository.findAll()).thenReturn(magazinesList);
        //When
        int quantity = magazineService.getQuantityAllVolumesToScanne();
        //Then
        assertEquals(15, quantity);
    }

    @Test
    public void testGetQuantityAllReadyBigPdf() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);

        ArrayList<Magazine> magazinesList = new ArrayList<>();
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);
        magazinesList.add(magazine3);

        when(magazineRepository.findAll()).thenReturn(magazinesList);
        //When
        int quantity = magazineService.getQuantityAllReadyBigPdf();
        //Then
        assertEquals(9, quantity);
    }

    @Test
    public void testGetQuantityAllBigPdfToDo() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);

        ArrayList<Magazine> magazinesList = new ArrayList<>();
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);
        magazinesList.add(magazine3);

        when(magazineRepository.findAll()).thenReturn(magazinesList);
        //When
        int quantity = magazineService.getQuantityAllBigPdfToDo();
        //Then
        assertEquals(16, quantity);
    }

    @Test
    public void testGetQuantityAllReadySmallPdf() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);

        ArrayList<Magazine> magazinesList = new ArrayList<>();
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);
        magazinesList.add(magazine3);

        when(magazineRepository.findAll()).thenReturn(magazinesList);
        //When
        int quantity = magazineService.getQuantityAllReadySmallPdf();
        //Then
        assertEquals(7, quantity);
    }

    @Test
    public void testGetQuantityAllSmallPdfToDo() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);

        ArrayList<Magazine> magazinesList = new ArrayList<>();
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);
        magazinesList.add(magazine3);

        when(magazineRepository.findAll()).thenReturn(magazinesList);
        //When
        int quantity = magazineService.getQuantityAllSmallPdfToDo();
        //Then
        assertEquals(18, quantity);
    }

    @Test
    public void testGetPriceAllStagesStart() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);

        ArrayList<Magazine> magazinesList = new ArrayList<>();
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);
        magazinesList.add(magazine3);
        Stage stageExample1 = new Stage(1, "Description1", 0.6);
        Stage stageExample2 = new Stage(2, "Description2", 0.5);
        Stage stageExample3 = new Stage(3, "Description3", 18.75);
        Stage stageExample4 = new Stage(4, "Description4", 6.25);
        when(magazineRepository.findAll()).thenReturn(magazinesList);
        when(stageService.getPriceStage(stageExample1.getStage())).thenReturn(stageExample1.getPrice());
        when(stageService.getPriceStage(stageExample2.getStage())).thenReturn(stageExample2.getPrice());
        when(stageService.getPriceStage(stageExample3.getStage())).thenReturn(stageExample3.getPrice());
        when(stageService.getPriceStage(stageExample4.getStage())).thenReturn(stageExample4.getPrice());

        //When
        double price = magazineService.getPriceAllStagesStart();
        //Then
        assertEquals(1756, price, 0.01);
    }

    @Test
    public void testGetPriceAllStagesActual() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);

        ArrayList<Magazine> magazinesList = new ArrayList<>();
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);
        magazinesList.add(magazine3);
        Stage stageExample1 = new Stage(1, "Description1", 0.6);
        Stage stageExample2 = new Stage(2, "Description2", 0.5);
        Stage stageExample3 = new Stage(3, "Description3", 18.75);
        Stage stageExample4 = new Stage(4, "Description4", 6.25);
        when(magazineRepository.findAll()).thenReturn(magazinesList);
        when(stageService.getPriceStage(stageExample1.getStage())).thenReturn(stageExample1.getPrice());
        when(stageService.getPriceStage(stageExample2.getStage())).thenReturn(stageExample2.getPrice());
        when(stageService.getPriceStage(stageExample3.getStage())).thenReturn(stageExample3.getPrice());
        when(stageService.getPriceStage(stageExample4.getStage())).thenReturn(stageExample4.getPrice());

        //When
        double price = magazineService.getPriceAllStagesActual();
        //Then
        assertEquals(1497.83, price, 0.01);
    }

    @Test
    public void testGetTimeAllStart() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);

        ArrayList<Magazine> magazinesList = new ArrayList<>();
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);
        magazinesList.add(magazine3);
        when(magazineRepository.findAll()).thenReturn(magazinesList);
        //When
        double time = magazineService.getTimeAllStart();
        //Then
        assertEquals(33.33, time, 0.01);
    }

    @Test
    public void testGetTimeAllActual() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);

        ArrayList<Magazine> magazinesList = new ArrayList<>();
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);
        magazinesList.add(magazine3);
        when(magazineRepository.findAll()).thenReturn(magazinesList);
        //When
        double time = magazineService.getTimeAllActual();
        //Then
        assertEquals(22, time, 0.01);
    }

}