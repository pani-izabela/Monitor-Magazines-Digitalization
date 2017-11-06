package com.monitor.magazines.controller;

import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.domain.MagazineDto;
import com.monitor.magazines.mapper.MagazineMapper;
import com.monitor.magazines.service.FileGeneatorService;
import com.monitor.magazines.service.MagazineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(MagazineController.class)
public class MagazineControllerTestSuite {
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
        magazineList.add(new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2));

        List<MagazineDto> magazineDtoList = new ArrayList<MagazineDto>();
        magazineDtoList.add(new MagazineDto(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2));

        when(magazineService.getMagazines()).thenReturn(magazineList);
        when(magazineMapper.mapToMagazineDtoList(magazineList)).thenReturn(magazineDtoList);
        // When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getMagazines").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Tytul testowy1")));
    }

    @Test
    public void testGetMagazine() throws Exception {
        //Given
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        MagazineDto magazineDto = new MagazineDto(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);

        when(magazineService.getMagazine(1L)).thenReturn(magazine);
        when(magazineMapper.mapToMagazineDto(magazine)).thenReturn(magazineDto);

        // When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getMagazine").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Tytul testowy1")));
    }

    @Test
    public void deleteMagazine() throws Exception {
    }

    @Test
    public void updateMagazine() throws Exception {
    }

    @Test
    public void testCreateMagazine() throws Exception {
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        MagazineDto magazineDto = new MagazineDto(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);

        when(magazineService.saveMagazine(magazine)).thenReturn(magazine);
        when(magazineMapper.mapToMagazine(magazineDto)).thenReturn(magazine);
    }

    @Test
    public void getPriceStartFor() throws Exception {
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);

        when(magazineService.getPriceStartFor(1L, 1)).thenReturn(36.0);

        mockMvc.perform(get("/monitor/digitalization/magazines/getPriceStartFor").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.title", is("Tytul testowy1")));
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