package com.hinext.maxis7567.karjoo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter

@Entity
@Table(name = "cities")
public class City {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "province_id")
    private int provinceId;

    @Column(name = "county_id")
    private int countyId;

    @Column(name = "name")
    private String name;
}
