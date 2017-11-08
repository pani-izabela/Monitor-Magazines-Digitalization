package com.monitor.magazines.controller;

import com.google.gson.Gson;
import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.domain.MagazineDto;
import com.monitor.magazines.domain.Stage;
import com.monitor.magazines.mapper.MagazineMapper;
import com.monitor.magazines.service.FileGeneratorService;
import com.monitor.magazines.service.MagazineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private FileGeneratorService fileGeneratorService;

    @Test
    public void testGetMagazines() throws Exception {
        //Given
        List<Magazine> magazineList = new ArrayList<Magazine>();
        magazineList.add(new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2));
        List<MagazineDto> magazineDtoList = new ArrayList<MagazineDto>();
        magazineDtoList.add(new MagazineDto(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2));

        when(magazineService.getMagazines()).thenReturn(magazineList);
        when(magazineMapper.mapToMagazineDtoList(magazineList)).thenReturn(magazineDtoList);

        //When & Then
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

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getMagazine")
                .param("magazineId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Tytul testowy1")));
    }

    @Test
    public void testDeleteMagazine() throws Exception {
        //Given
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);

        doNothing().when(magazineService).deleteMagazine(magazine.getId());

        //When & Then
        mockMvc.perform(delete("/monitor/digitalization/magazines/deleteMagazine")
                .param("magazineId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMagazine() throws Exception {
        //Given
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        MagazineDto magazineDto = new MagazineDto(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);

        when(magazineService.saveMagazine(magazine)).thenReturn(magazine);
        when(magazineMapper.mapToMagazine(magazineDto)).thenReturn(magazine);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(magazineDto);

        //When & Then
        mockMvc.perform(put("/monitor/digitalization/magazines/updateMagazine")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateMagazine() throws Exception {
        //Given
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        MagazineDto magazineDto = new MagazineDto(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);

        when(magazineService.saveMagazine(magazine)).thenReturn(magazine);
        when(magazineMapper.mapToMagazine(magazineDto)).thenReturn(magazine);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(magazineDto);

        //When & Then
        mockMvc.perform(post("/monitor/digitalization/magazines/createMagazine")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void getPriceStartFor() throws Exception {
        //Given
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Stage stage = new Stage(1, "Description", 0.6);

        when(magazineService.getPriceStartFor(magazine.getId(), stage.getStage())).thenReturn(36.0);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getPriceStartFor")
                .param("magazineId", "1")
                .param("stage", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(36.0)));
    }

    @Test
    public void testGetPriceActualFor() throws Exception {
        //Given
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Stage stage = new Stage(3, "Description", 18.75);

        when(magazineService.getPriceActualFor(magazine.getId(), stage.getStage())).thenReturn(18.75);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getPriceActualFor")
                .param("magazineId", "1")
                .param("stage", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(18.75)));
    }

    @Test
    public void testGetTimeStartFor() throws Exception {
        //Given
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Stage stage = new Stage(3, "Description", 18.75);

        when(magazineService.getTimeStartFor(magazine.getId(), stage.getStage())).thenReturn(3.0);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getTimeStartFor")
                .param("magazineId", "1")
                .param("stage", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("3(h) 0(m)")));
    }

    @Test
    public void testGetTimeActualFor() throws Exception {
        //Given
        Magazine magazine = new Magazine(1L, "Tytul testowy1", "4444-7890", 2009, 3, 300L, 60L, 3, 2, 2);
        Stage stage = new Stage(4, "Description", 6.25);

        when(magazineService.getTimeActualFor(magazine.getId(), stage.getStage())).thenReturn(0.34);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getTimeActualFor")
                .param("magazineId", "1")
                .param("stage", "4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("0(h) 20(m)")));
    }

    @Test
    public void testGetQuantityAllVolumes() throws Exception {
        //Given
        when(magazineService.getQuantityAllVolumes()).thenReturn(25);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllVolumes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(25)));
    }

    @Test
    public void testGetQuantityAllScannedVolumes() throws Exception {
        //Given
       when(magazineService.getQuantityAllScanedVolumes()).thenReturn(10);

       //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllScanedVolumes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(10)));
    }

    @Test
    public void testGetQuantityAllVolumesToScanne() throws Exception {
        //Given
        when(magazineService.getQuantityAllVolumesToScanne()).thenReturn(15);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllVolumesToScanne")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(15)));
    }

    @Test
    public void testGetQuantityAllReadyBigPdf() throws Exception {
        //Given
       when(magazineService.getQuantityAllReadyBigPdf()).thenReturn(9);

       //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllReadyBigPdf")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(9)));
    }

    @Test
    public void testGetQuantityAllBigPdfToDo() throws Exception {
        //Given
        when(magazineService.getQuantityAllBigPdfToDo()).thenReturn(16);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllBigPdfToDo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(16)));
    }

    @Test
    public void testGetQuantityAllReadySmallPdf() throws Exception {
        //Given
        when(magazineService.getQuantityAllReadySmallPdf()).thenReturn(7);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllReadySmallPdf")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(7)));
    }

    @Test
    public void testGetQuantityAllSmallPdfToDo() throws Exception {
        //Given
        when(magazineService.getQuantityAllSmallPdfToDo()).thenReturn(18);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getQuantityAllSmallPdfToDo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(18)));
    }

    @Test
    public void testGetPriceAllStagesStart() throws Exception {
        //Given
       when(magazineService.getPriceAllStagesStart()).thenReturn(1756.0);

       //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getPriceAllStagesStart")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1756.0)));
    }

    @Test
    public void getPriceAllStagesActual() throws Exception {
        //Given
        when(magazineService.getPriceAllStagesActual()).thenReturn(1497.83);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getPriceAllStagesActual")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1497.83)));
    }

    @Test
    public void testGetTimeAllStart() throws Exception {
        //Given
        when(magazineService.getTimeAllStart()).thenReturn(33.34);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getTimeAllStart")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("33(h) 20(m)")));
    }

    @Test
    public void testGetTimeAllActual() throws Exception {
        //Given
        when(magazineService.getTimeAllActual()).thenReturn(22.0);

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getTimeAllActual")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("22(h) 0(m)")));
    }

    @Test
    public void testGetDataForSingelMagazine() throws Exception {
        //Given
        doNothing().when(fileGeneratorService).getDataForSingelMagazine(anyLong(), any(HttpServletResponse.class));

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getDataForSingelMagazine")
                .param("magazineId", "5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDataForSingelMagazineToPdf() throws Exception {
        //Given
        doNothing().when(fileGeneratorService).getDataForSingelMagazineToPdf(anyLong(), any(HttpServletResponse.class));

        //When & Then
        mockMvc.perform(get("/monitor/digitalization/magazines/getDataForSingelMagazineToPdf")
                .param("magazineId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}