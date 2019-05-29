package com.hinext.maxis7567.karjoo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.Date;

/**
 * 
 * @author MahdiSy
 * Create at 2019-05-28 19:33
 */
@Setter
@Getter
@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "userId")
    private int userId;


    @Column(name = "title")
    private String title;


    @Column(name = "\"desc\"")
    private String desc;


    @Column(name = "phoneNumber")
    private String phoneNumber;


    @Column(name = "view")
    private int view;


    @Column(name = "dateModified", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date dateModified;
}