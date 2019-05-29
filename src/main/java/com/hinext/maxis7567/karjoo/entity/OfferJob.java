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
@Table(name = "offerjob")
public class OfferJob {
    @Id
    @Column(name = "offerId")
    private int offerId;


    @Column(name = "jobsId")
    private int jobsId;
}
