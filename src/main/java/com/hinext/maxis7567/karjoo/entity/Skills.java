package com.hinext.maxis7567.karjoo.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.*;

/**
 * 
 * @author MahdiSy
 * Create at 2019-05-28 19:41
 */
@Setter
@Getter
@Entity
@Table(name = "Skills")
public class Skills {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name")
    private String name;
}