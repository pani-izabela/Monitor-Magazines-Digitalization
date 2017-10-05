package com.monitor.magazines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MagazineDto {
    private final Long id;
    private final String title;
    private final String issn;
    private final int firstScannedYear;
    private final int volumesToScann;
    private final Long pagesToScann;
    private final Long articles;
    private int scannedVolumes = 0;
    private int volumesBigPdf = 0;
    private int volumesSmallPdf = 0;
    private double pagesPerVolumes = 0;
    private double articlesPerVolumes = 0;

}
