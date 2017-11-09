package com.monitor.magazines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity(name="magazines_list2")
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="title")
    @NotEmpty
    private String title;
    @Column(name = "issn")
    @NotEmpty
    private String issn;
    @Column(name = "firstScannedYear")
    private int firstScannedYear;
    @Column(name="volumesToScann")
    @NotNull
    private int volumesToScann;
    @Column(name="pagesToScann")
    @NotNull
    private Long pagesToScann;
    @Column(name="articles")
    @NotNull
    private Long articles;
    @Column(name="scannedVolumes")
    @Min(value = 0, message = "The number have to be higher than 0")
    private int scannedVolumes;
    @Column(name="volumesBigPdf")
    @Min(value = 0, message = "The number have to be higher than 0")
    private int volumesBigPdf;
    @Column(name="volumesSmallPdf")
    @Min(value = 0, message = "The number have to be higher than 0")
    private int volumesSmallPdf;

}
