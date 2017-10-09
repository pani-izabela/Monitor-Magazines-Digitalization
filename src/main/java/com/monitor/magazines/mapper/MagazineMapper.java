package com.monitor.magazines.mapper;

import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.domain.MagazineDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MagazineMapper {
    public Magazine mapToMagazine(final MagazineDto magazineDto){
        return new Magazine(magazineDto.getId(),
                magazineDto.getTitle(),
                magazineDto.getIssn(),
                magazineDto.getFirstScannedYear(),
                magazineDto.getVolumesToScann(),
                magazineDto.getPagesToScann(),
                magazineDto.getArticles(),
                magazineDto.getScannedVolumes(),
                magazineDto.getVolumesBigPdf(),
                magazineDto.getVolumesSmallPdf());
    }

    public MagazineDto mapToMagazineDto(final Magazine magazine){
        return new MagazineDto(magazine.getId(),
                magazine.getTitle(),
                magazine.getIssn(),
                magazine.getFirstScannedYear(),
                magazine.getVolumesToScann(),
                magazine.getPagesToScann(),
                magazine.getArticles(),
                magazine.getScannedVolumes(),
                magazine.getVolumesBigPdf(),
                magazine.getVolumesSmallPdf());
                //(double) magazine.getPagesToScann()/magazine.getScannedVolumes(),
                //(double) magazine.getArticles()/magazine.getScannedVolumes());
    }

    public List<MagazineDto> mapToMagazineDtoList(final List<Magazine> magazineList){
        return magazineList.stream()
                .map(m-> mapToMagazineDto(m))
                .collect(Collectors.toList());
    }
}
