package com.hinext.maxis7567.karjoo.outModels;

import com.hinext.maxis7567.karjoo.inModel.Skils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OutRequest {
private String number;
private String address;
private List<Skils> skills;
private List<File> file;


}
