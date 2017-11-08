package com.monitor.magazines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MagazineDto {
    private Long id;
    private String title;
    private String issn;
    private int firstScannedYear;
    private int volumesToScann;
    private Long pagesToScann;
    private Long articles;
    private int scannedVolumes;
    private int volumesBigPdf;
    private int volumesSmallPdf;
}
