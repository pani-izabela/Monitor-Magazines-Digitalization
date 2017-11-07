package com.monitor.magazines.controller;

import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.domain.MagazineDto;
import com.monitor.magazines.domain.Stage;
import com.monitor.magazines.mapper.MagazineMapper;
import com.monitor.magazines.service.FileGeneatorService;
import com.monitor.magazines.service.MagazineService;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@WebMvcTest(MagazineController.class)
public class MagazineControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MagazineService magazineService;
    @MockBean
    private MagazineMapper magazineMapper;
    @MockBean
    private FileGeneatorService fileGeneatorService;

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
        mockMvc.perform(get("/monitor/digitalization/magazines/getMagazine")
                .param("magazineId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Tytul testowy1")));
    }

    @Test
    public void testDeleteMagazine() throws Exception {
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
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
        Stage stage = new Stage(1, "Description", 0.6);
        when(magazineService.getPriceStartFor(magazine.getId(), stage.getStage())).thenReturn(36.0);
        //Czy to jest dobrze, że ja wrzucam tu 30.0, a nie 36, wszak tyle pokazuje postman

        mockMvc.perform(get("/monitor/digitalization/magazines/getPriceStartFor")
                .param("magazineId", "1")
                .param("stage", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(36.0)));
    }

    @Test
    public void testGetPriceActualFor() throws Exception {
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Stage stage = new Stage(3, "Description", 18.75);
        when(magazineService.getPriceActualFor(magazine.getId(), stage.getStage())).thenReturn(18.75);

        mockMvc.perform(get("/monitor/digitalization/magazines/getPriceActualFor")
                .param("magazineId", "1")
                .param("stage", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(18.75)));
    }

    @Test
    public void testGetTimeStartFor() throws Exception {
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Stage stage = new Stage(3, "Description", 18.75);
        when(magazineService.getTimeStartFor(magazine.getId(), stage.getStage())).thenReturn(3.0);

        mockMvc.perform(get("/monitor/digitalization/magazines/getTimeStartFor")
                .param("magazineId", "1")
                .param("stage", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(3.0)));
    }

    @Test
    public void testGetTimeActualFor() throws Exception {
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Stage stage = new Stage(4, "Description", 6.25);
        when(magazineService.getTimeActualFor(magazine.getId(), stage.getStage())).thenReturn(0.33);

        mockMvc.perform(get("/monitor/digitalization/magazines/getTimeActualFor")
                .param("magazineId", "1")
                .param("stage", "4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(0.33)));
    }

    @Test
    public void testGetQuantityAllScannedVolumes() throws Exception {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);
        when(magazineService.getQuantityAllVolumes()).thenReturn(25);
        //When&Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllVolumes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(25)));
    }

    @Test
    public void testGetQuantityAllVolumesToScanne() throws Exception {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);
        when(magazineService.getQuantityAllVolumesToScanne()).thenReturn(15);
        //When&Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllVolumesToScanne")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(15)));
    }

    @Test
    public void testGetQuantityAllReadyBigPdf() throws Exception {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);
        when(magazineService.getQuantityAllReadyBigPdf()).thenReturn(9);
        //When&Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllReadyBigPdf")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(9)));
    }

    @Test
    public void testGetQuantityAllBigPdfToDo() throws Exception {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);
        when(magazineService.getQuantityAllBigPdfToDo()).thenReturn(16);
        //When&Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllBigPdfToDo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(16)));
    }

    @Test
    public void testGetQuantityAllReadySmallPdf() throws Exception {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);
        when(magazineService.getQuantityAllReadySmallPdf()).thenReturn(7);
        //When&Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllReadySmallPdf")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(7)));
    }

    @Test
    public void testGetQuantityAllSmallPdfToDo() throws Exception {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);
        when(magazineService.getQuantityAllSmallPdfToDo()).thenReturn(18);
        //When&Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllSmallPdfToDo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(18)));
    }

    @Test
    public void testGetPriceAllStagesStart() throws Exception {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);
        when(magazineService.getPriceAllStagesStart()).thenReturn(1756.0);
        //When&Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getPriceAllStagesStart")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1756.0)));
    }

    @Test
    public void getPriceAllStagesActual() throws Exception {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);
        when(magazineService.getPriceAllStagesActual()).thenReturn(1497.83);
        //When&Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getPriceAllStagesActual")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1497.83)));
    }

    @Test
    public void testGetTimeAllStart() throws Exception {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);
        when(magazineService.getTimeAllStart()).thenReturn(33.33);
        //When&Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getTimeAllStart")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(33.33)));
    }

    @Test
    public void testGetTimeAllActual() throws Exception {
        //Given
        Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Magazine magazine2 = new Magazine(2L, "Tytuł testowy2", "6543-9999", 2003, 12, 500L, 350L, 2, 2, 0);
        Magazine magazine3 = new Magazine(3L, "Tytuł testowy3", "j789-000", 2007, 10, 2500L, 100L, 5, 5, 5);
        when(magazineService.getTimeAllActual()).thenReturn(22.0);
        //When&Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getTimeAllActual")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(22.0)));
    }

    @Test
    public void testSsaveDataForSingelMagazine() throws Exception {
        /*Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        when(fileGeneatorService.saveDataForSingelMagazine(magazine1.getId())).thenReturn();*/
    }

    @Test
    public void testSaveDataForSingelMagazineToPdf() throws Exception {
        /*Magazine magazine1 = new Magazine(1L, "Tytuł testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        when(fileGeneatorService.saveDataForSingelMagazine()).thenReturn();*/
    }

}