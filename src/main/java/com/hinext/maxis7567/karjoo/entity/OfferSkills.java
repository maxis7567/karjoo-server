package com.hinext.maxis7567.karjoo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "offerskills")
public class OfferSkills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private int id;

    @Column(name = "offerId")
    private int offerId;

    @Column(name = "skillsId")
    private int skillsId;

    @Column(name = "desc")
    private String desc;
}
