package com.monitor.magazines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity(name="magazines_list2")
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name = "issn")
    private String issn;
    @Column(name = "firstScannedYear")
    private Integer firstScannedYear;
    @Column(name="volumesToScann")
    private Integer volumesToScann;
    @Column(name="pagesToScann")
    private Long pagesToScann;
    @Column(name="articles")
    private Long articles;
    @Column(name="scannedVolumes")
    private Integer scannedVolumes;
    @Column(name="volumesBigPdf")
    private Integer volumesBigPdf;
    @Column(name="volumesSmallPdf")
    private Integer volumesSmallPdf;
    //@Column(name="pagesPerVolumes")
    //private Double pagesPerVolumes = 0.0;
    //@Column(name="articlesPerVolumes")
    //private Double articlesPerVolumes = 0.0;

}
