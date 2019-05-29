package com.hinext.maxis7567.karjoo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.util.Date;

/**
 * 
 * @author MahdiSy
 * Create at 2019-05-28 19:42
 */
@Setter
@Getter
@Entity
@Table(name = "user")
public class User   {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "fName")
    private String fName;


    @Column(name = "lName")
    private String lName;

    @Column(name = "gender")
    private int gender;


    @Column(name = "imageUrl")
    private String imageUrl;


    @Column(name = "tokenId")
    private String tokenId;


    @Column(name = "fireBaseToken")
    private String fireBaseToken;


    @Column(name = "phoneNumber")
    private String phoneNumber;


    @Column(name = "cityId")
    private int cityId;


    @Column(name = "address")
    private String address;

    @Column(name = "type")
    private int type;

    @Column(name = "status")
    private int status;

    @Column(name = "dateModified", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date dateModified;
}