package com.hinext.maxis7567.karjoo.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.*;

/**
 * 
 * @author MahdiSy
 * Create at 2019-05-28 19:45
 */
@Setter
@Getter
@Entity
@Table(name = "version")

public class Version {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "lastA")
    private int lastA;


    @Column(name = "supportedA")
    private int supportedA;


    @Column(name = "lastI")
    private int lastI;


    @Column(name = "supportedI")
    private int supportedI;
}