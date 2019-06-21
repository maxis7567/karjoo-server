package com.hinext.maxis7567.karjoo.controller;


import com.hinext.maxis7567.karjoo.entity.User;
import com.hinext.maxis7567.karjoo.model.Address;
import com.hinext.maxis7567.karjoo.outModels.BaseModel;
import com.hinext.maxis7567.karjoo.outModels.OutUser;
import com.hinext.maxis7567.karjoo.repository.IRepCity;
import com.hinext.maxis7567.karjoo.repository.IRepCounty;
import com.hinext.maxis7567.karjoo.repository.IRepProvince;
import com.hinext.maxis7567.karjoo.repository.IRepUser;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;


@RestController
@RequestMapping("/v1/user")
public class UserController {
    public static final String LOCAL_STORAGE_DIRECTORY="C:\\xampp\\htdocs\\uploads\\";

    private IRepUser iRepUser;
    private IRepProvince iRepProvince;
    private IRepCity iRepCity;
    private IRepCounty iRepCounty;
    public UserController(IRepUser iRepUser, IRepProvince iRepProvince, IRepCity iRepCity, IRepCounty iRepCounty) {
        this.iRepUser = iRepUser;
        this.iRepProvince = iRepProvince;
        this.iRepCity = iRepCity;
        this.iRepCounty = iRepCounty;
    }

    @PostMapping("/active")
    public BaseModel activeUser(@RequestHeader HashMap<String,String> header,
                                @RequestParam("image") MultipartFile file,
                                @RequestParam("name") String name,
                                @RequestParam("lName")String lastName,
                                @RequestParam("address")String address,
                                @RequestParam("cityId")int cityId,
                                @RequestParam("gender")int gender,
                                @RequestParam("type") int type){

        BaseModel result=new BaseModel();
        if (iRepUser.existsByTokenId(header.get("token"))){
            String newFileName=String.valueOf(ThreadLocalRandom.current().nextInt(1000, 9999 + 1))+file.getOriginalFilename();
            File convFile = new File(LOCAL_STORAGE_DIRECTORY+newFileName);
            try {
                file.transferTo(convFile);
            } catch (IOException e) {
                result.setStatus("Failed");
                result.setError("upload failed");
                result.setMessage("مشکل در ارسال عکس");
                return result;
            }
            User user=iRepUser.findByTokenId(header.get("token"));
            user.setFName(name);
            user.setLName(lastName);
            user.setAddress(address);
            user.setCityId(cityId);
            user.setGender(gender);
            user.setType(type);
            user.setStatus(1);
            user.setImageUrl("/uploads/"+newFileName);
            iRepUser.save(user);
            result.setStatus("Ok");
            return result;
        }else {
            result.setStatus("Failed");
            result.setError("User Not exist");
            result.setMessage("توکن وجود ندارو");
            return result;
        }


    }
    @GetMapping("/get")
    public OutUser getUser(@RequestHeader HashMap<String,String> header){
        if (iRepUser.existsByTokenId(header.get("token"))){
            User user=iRepUser.findByTokenId(header.get("token"));
            Address address=new Address();
            address.setCity(iRepCity.getOne(user.getCityId()).getName());
            address.setCounty(iRepCounty.getOne(iRepCity.getOne(user.getCityId()).getCountyId()).getName());
            address.setProvince(iRepProvince.getOne(iRepCounty.getOne(iRepCity.getOne(user.getCityId()).getCountyId()).getProvinceId()).getName());
            return new OutUser(user.getFName()+" "+
                    user.getLName(),user.getAddress(),address.getProvince()
            ,address.getCounty(),address.getCity(),user.getType(),user.getStatus(),user.getImageUrl(),user.getGender(),"OK",null,null);
        }else {
            return new OutUser(null,null,null,null,null,0,0,
                    null,0,"Failed","دسترسی نا معتبر","Token Not Exist");
        }
    }
//    @GetMapping("get/list")
//    public void
}
