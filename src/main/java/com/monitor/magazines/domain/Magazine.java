package com.monitor.magazines.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Entity(name="magazines_list")
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    @Column
    private final String title;
    @Column
    private final String issn;
    @Column
    private final int firstScannedYear;
    @Column
    private final int volumesToScann;
    @Column
    private final Long pagesToScann;
    @Column
    private final Long articles;
    @Column
    private int scannedVolumes = 0;
    @Column
    private int volumesBigPdf = 0;
    @Column
    private int volumesSmallPdf = 0;
    @Column
    private double pagesPerVolumes = 0;
    @Column
    private double articlesPerVolumes = 0;
}
