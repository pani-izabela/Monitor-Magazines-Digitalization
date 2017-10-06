package com.monitor.magazines.service;

import com.monitor.magazines.domain.Magazine;
import com.monitor.magazines.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagazineService {

    @Autowired
    private MagazineRepository magazineRepository;

    public List<Magazine> getMagazines(){
        return magazineRepository.findAll();
    }

    public Magazine saveMagazine(final Magazine magazine){
        return magazineRepository.save(magazine);
    }

    public void deleteMagazine(final Long magazineId){
        magazineRepository.deleteById(magazineId);
    }

    public Optional<Magazine> getMagazine(final Long magazineId){
        return magazineRepository.findById(magazineId);
    }
}
