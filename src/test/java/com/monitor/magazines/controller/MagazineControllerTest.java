package com.monitor.magazines.controller;

import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.mapper.MagazineMapper;
import com.monitor.magazines.service.FileGeneatorService;
import com.monitor.magazines.service.MagazineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(MagazineController.class)
public class MagazineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MagazineService magazineService;
    @MockBean
    private MagazineMapper magazineMapper;
    @MockBean
    private FileGeneatorService csvGeneatorService;

    @Test
    public void testGetMagazines() throws Exception {
        //Given
        List<Magazine> magazineList = new ArrayList<Magazine>();

        // When & Then
    }

    @Test
    public void getMagazine() throws Exception {
    }

    @Test
    public void deleteMagazine() throws Exception {
    }

    @Test
    public void updateMagazine() throws Exception {
    }

    @Test
    public void createMagazine() throws Exception {
    }

    @Test
    public void getPriceStartFor() throws Exception {
    }

    @Test
    public void getPriceActualFor() throws Exception {
    }

    @Test
    public void getTimeStartFor() throws Exception {
    }

    @Test
    public void getTimeActualFor() throws Exception {
    }

    @Test
    public void getQuantityAllScannedVolumes() throws Exception {
    }

    @Test
    public void getQuantityAllVolumesToScanne() throws Exception {
    }

    @Test
    public void getQuantityAllReadyBigPdf() throws Exception {
    }

    @Test
    public void getQuantityAllBigPdfToDo() throws Exception {
    }

    @Test
    public void getQuantityAllReadySmallPdf() throws Exception {
    }

    @Test
    public void getQuantityAllSmallPdfToDo() throws Exception {
    }

    @Test
    public void getPriceAllStagesStart() throws Exception {
    }

    @Test
    public void getPriceAllStagesActual() throws Exception {
    }

    @Test
    public void getTimeAllStart() throws Exception {
    }

    @Test
    public void getTimeAllActual() throws Exception {
    }

    @Test
    public void saveDataForSingelMagazine() throws Exception {
    }

    @Test
    public void saveDataForSingelMagazineToPdf() throws Exception {
    }

}