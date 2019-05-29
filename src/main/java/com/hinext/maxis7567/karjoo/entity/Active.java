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
@Table(name = "active")
public class Active {
    @Id
    @Column(name = "number")
    private String number;

    @Column(name = "code")
    private int code;

    @Column(name = "status")
    private int status;
}
