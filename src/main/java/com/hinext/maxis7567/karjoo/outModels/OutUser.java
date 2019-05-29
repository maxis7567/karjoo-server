package com.hinext.maxis7567.karjoo.outModels;

import lombok.Setter;

@Setter
public class OutUser  {
    private final String bError;
    private final String bStatus;
    private final String bMessage;

    public OutUser(String name, String lName, String address, String province, String county, String city, int type, int status, String image, int gender, String bStatus, String bmessage, String bError) {
        this.name = name;
        this.lName = lName;
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

    private String name;
    private String lName;
    private String address;
    private String province;
    private String county;
    private String city;
    private int type;
    private int status;
    private String image;
    private int gender;
}
