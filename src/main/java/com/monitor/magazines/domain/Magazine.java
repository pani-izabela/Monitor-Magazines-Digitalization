package com.monitor.magazines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

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
    @Length(min = 4, max = 4)
    private Integer firstScannedYear;
    @Column(name="volumesToScann")
    @NotEmpty
    @NotNull
    private Integer volumesToScann;
    @Column(name="pagesToScann")
    @NotEmpty
    @NotNull
    private Long pagesToScann;
    @Column(name="articles")
    @NotEmpty
    @NotNull
    private Long articles;
    @Column(name="scannedVolumes")
    @Min(value = 0, message = "Liczba nie może być mniejsza niż 0")//
    @NotEmpty(message = "Podana liczba nie może być większa niż liczba wszystkich numerów do skanowania, czyli niż wartość wpisana w pole: volumesToScann.")
    //@Max(value = volumesToScann) a może to trzeba załatwić dalej ifem/wyjątkiem np. że jeśli scannedVolumes>volumesToScann
    //to wyrzuć komunikat "podana wartość nie może być większa niż liczba nr do skanowania"
    //to powinno być na etapie wpisywania danych do bazy czyli przy saveMagazine lub updateMagazine||createMagazine
    private Integer scannedVolumes;
    @Column(name="volumesBigPdf")
    @Min(value = 0, message = "Liczba nie może być mniejsza niż 0") //
    @NotEmpty(message = "Podana liczba nie może być większa niż liczba wszystkich numerów do skanowania, czyli niż wartość wpisana w pole: volumesToScann.")
    private Integer volumesBigPdf;
    @Column(name="volumesSmallPdf")
    @Min(value = 0, message = "Liczba nie może być mniejsza niż 0") //
    @NotEmpty(message = "Podana liczba nie może być większa niż liczba wszystkich numerów do skanowania, czyli niż wartość wpisana w pole: volumesToScann.")
    private Integer volumesSmallPdf;

}
