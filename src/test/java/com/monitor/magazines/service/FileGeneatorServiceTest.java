package com.monitor.magazines.service;

import com.monitor.magazines.domain.Magazine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileGeneatorServiceTest {
    @InjectMocks
    private FileGeneatorService fileGeneatorService;

    @Mock
    private MagazineService magazineService;

    @Test
    public void testSaveDataForSingelMagazine() {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        when(magazineService.getMagazine(magazine1.getId())).thenReturn(magazine1);

        //When
        fileGeneatorService.saveDataForSingelMagazine(magazine1.getId(), );

        //Then
        verify(fileGeneatorService, times(1)).saveDataForSingelMagazine(magazine1.getId(), );
    }

    @Test
    public void testSaveDataForSingelMagazineToPdf() {
    }

}