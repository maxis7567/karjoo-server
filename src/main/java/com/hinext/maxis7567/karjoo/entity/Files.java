package com.hinext.maxis7567.karjoo.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.*;

/**
 * 
 * @author MahdiSy
 * Create at 2019-05-28 19:30
 */
@Getter
@Setter

@Entity
@Table(name = "files")
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "fileUrl")
    private String fileUrl;


    @Column(name = "fileNeme")
    private String fileName;


    @Column(name = "\"desc\"")
    private String desc;


    @Column(name = "requestId")
    private int requestId;


    @Column(name = "offerId")
    private int offerId;
}