package com.hinext.maxis7567.karjoo.outModels;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutUser  {
    private  String bError;
    private  String bStatus;
    private  String bMessage;
    private String name;
    private String lastName;
    private String address;
    private String province;
    private String county;
    private String city;
    private int type;
    private int status;
    private String image;
    private int gender;
    public OutUser(String name, String lastName, String address, String province, String county, String city, int type, int status, String image, int gender, String bStatus, String bmessage, String bError) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.province = province;
        this.county = county;
        this.city = city;
        this.type = type;
        this.status = status;
        this.image = image;
        this.gender = gender;
        this.bError=bError;
        this.bStatus=bStatus;
        this.bMessage=bmessage;
    }
}
