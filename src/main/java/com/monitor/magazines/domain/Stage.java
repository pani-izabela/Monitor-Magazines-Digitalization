package com.monitor.magazines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name="stages_digitization")
public class Stage {
    @Id
    @Column(name="stage")
    private int stage;
    @Column(name="description")
    private String description;
    @Column(name="price")
    private double price;

}
