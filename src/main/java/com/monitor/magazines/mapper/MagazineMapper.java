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
                magazineDto.getArticles());
    }

    public MagazineDto mapToMagazineDto(final Magazine magazine){
        return new MagazineDto(magazine.getId(),
                magazine.getTitle(),
                magazine.getIssn(),
                magazine.getFirstScannedYear(),
                magazine.getVolumesToScann(),
                magazine.getPagesToScann(),
                magazine.getArticles());
    }

    public List<MagazineDto> mapToMagazineDtoList(final List<Magazine> magazineList){
        return magazineList.stream()
                .map(m-> new MagazineDto(m.getId(), m.getTitle(), m.getIssn(), m.getFirstScannedYear(), m.getVolumesToScann(), m.getPagesToScann(), m.getArticles()))
                .collect(Collectors.toList());
    }
}
