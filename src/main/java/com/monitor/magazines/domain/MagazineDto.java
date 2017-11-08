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
    private Integer firstScannedYear;
    private Integer volumesToScann;
    private Long pagesToScann;
    private Long articles;
    private Integer scannedVolumes;
    private Integer volumesBigPdf;
    private Integer volumesSmallPdf;
}
