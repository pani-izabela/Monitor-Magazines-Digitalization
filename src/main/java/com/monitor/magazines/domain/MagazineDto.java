package com.monitor.magazines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
    private Integer scannedVolumes = 0;
    private Integer volumesBigPdf = 0;
    private Integer volumesSmallPdf = 0;
    private Double pagesPerVolumes = 0.0;
    private Double articlesPerVolumes = 0.0;

    public MagazineDto(Long id, String title, String issn, int firstScannedYear, int volumesToScann, Long pagesToScann, Long articles) {
        this.id = id;
        this.title = title;
        this.issn = issn;
        this.firstScannedYear = firstScannedYear;
        this.volumesToScann = volumesToScann;
        this.pagesToScann = pagesToScann;
        this.articles = articles;
    }
}
