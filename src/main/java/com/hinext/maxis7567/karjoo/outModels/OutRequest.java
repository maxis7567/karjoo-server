package com.hinext.maxis7567.karjoo.outModels;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OutRequest {
private String number;
private String address;
private List<Skills> skills;
private List<File> file;


}
