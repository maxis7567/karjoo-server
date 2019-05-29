package com.hinext.maxis7567.karjoo.entity;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter

@Entity
@Table(name = "counties")
public class County {

    @Id
    @Column(name = "id",nullable = false,unique = true)
    private int id;

    @Column(name = "province_id",unique = true)
    private int provinceId;

    @Column(name = "name")
    private String name;

}
