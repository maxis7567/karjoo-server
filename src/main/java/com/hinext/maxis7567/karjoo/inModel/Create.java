package com.hinext.maxis7567.karjoo.inModel;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public class Create implements Serializable {
    private String title, desc, phoneNumber;
    private List<Skils> skillsList;

}