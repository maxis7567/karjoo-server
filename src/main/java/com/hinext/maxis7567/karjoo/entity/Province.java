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
@Table(name = "provinces")
public class Province {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
