package com.hinext.maxis7567.karjoo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "requestjob")
public class RequestJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "requestId")
    private int requestId;


    @Column(name = "jobsId")
    private int jobsId;
}
