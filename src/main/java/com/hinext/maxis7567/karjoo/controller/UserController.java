package com.hinext.maxis7567.karjoo.controller;


import com.hinext.maxis7567.karjoo.entity.*;
import com.hinext.maxis7567.karjoo.inModel.Create;
import com.hinext.maxis7567.karjoo.inModel.SaveSkills;
import com.hinext.maxis7567.karjoo.model.Address;
import com.hinext.maxis7567.karjoo.outModels.BaseModel;
import com.hinext.maxis7567.karjoo.outModels.ListData;
import com.hinext.maxis7567.karjoo.outModels.OutUser;
import com.hinext.maxis7567.karjoo.repository.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@RestController
@RequestMapping("/v1/user")
public class UserController {
    public static final String LOCAL_STORAGE_DIRECTORY = "C:\\xampp\\htdocs\\uploads\\";

    private IRepUser iRepUser;
    private IRepProvince iRepProvince;
    private IRepCity iRepCity;
    private IRepCounty iRepCounty;
    private IRepRequest iRepRequest;
    private IRepOffer iRepOffer;
    private IRepSkills iRepSkills;
    private IRepJobs iRepJobs;
    private IRepOfferJob iRepOfferJob;
    private IRepRequestJob iRepRequestJob;
    private IRepOfferSkills iRepOfferSkills;
    private IRepRequestSkills iRepRequestSkills;
    private IRepFile iRepFile;

    public UserController(IRepUser iRepUser, IRepProvince iRepProvince, IRepCity iRepCity, IRepCounty iRepCounty, IRepRequest iRepRequest, IRepOffer iRepOffer, IRepSkills iRepSkills, IRepJobs iRepJobs, IRepOfferJob iRepOfferJob, IRepRequestJob iRepRequestJob, IRepOfferSkills iRepOfferSkills, IRepRequestSkills iRepRequestSkills, IRepFile iRepFile) {
        this.iRepUser = iRepUser;
        this.iRepProvince = iRepProvince;
        this.iRepCity = iRepCity;
        this.iRepCounty = iRepCounty;
        this.iRepRequest = iRepRequest;
        this.iRepOffer = iRepOffer;
        this.iRepSkills = iRepSkills;
        this.iRepJobs = iRepJobs;
        this.iRepOfferJob = iRepOfferJob;
        this.iRepRequestJob = iRepRequestJob;
        this.iRepOfferSkills = iRepOfferSkills;
        this.iRepRequestSkills = iRepRequestSkills;
        this.iRepFile = iRepFile;
    }

    @PostMapping("/active")
    public BaseModel activeUser(@RequestHeader HashMap<String, String> header,
                                @RequestParam("image") MultipartFile file,
                                @RequestParam("name") String name,
                                @RequestParam("lName") String lastName,
                                @RequestParam("address") String address,
                                @RequestParam("cityId") int cityId,
                                @RequestParam("gender") int gender,
                                @RequestParam("type") int type) {

        BaseModel result = new BaseModel();
        if (iRepUser.existsByTokenId(header.get("token"))) {
            String newFileName = String.valueOf(ThreadLocalRandom.current().nextInt(1000, 9999 + 1)) + file.getOriginalFilename();
            File convFile = new File(LOCAL_STORAGE_DIRECTORY + newFileName);
            try {
                file.transferTo(convFile);
            } catch (IOException e) {
                result.setStatus("Failed");
                result.setError("upload failed");
                result.setMessage("مشکل در ارسال عکس");
                return result;
            }
            User user = iRepUser.findByTokenId(header.get("token"));
            user.setFName(name);
            user.setLName(lastName);
            user.setAddress(address);
            user.setCityId(cityId);
            user.setGender(gender);
            user.setType(type);
            user.setStatus(1);
            user.setImageUrl("/uploads/" + newFileName);
            iRepUser.save(user);
            result.setStatus("Ok");
            return result;
        } else {
            result.setStatus("Failed");
            result.setError("User Not exist");
            result.setMessage("توکن وجود ندارو");
            return result;
        }


    }

    @GetMapping("/get")
    public OutUser getUser(@RequestHeader HashMap<String, String> header) {
        if (iRepUser.existsByTokenId(header.get("token"))) {
            User user = iRepUser.findByTokenId(header.get("token"));
            Address address = new Address();
            address.setCity(iRepCity.getOne(user.getCityId()).getName());
            address.setCounty(iRepCounty.getOne(iRepCity.getOne(user.getCityId()).getCountyId()).getName());
            address.setProvince(iRepProvince.getOne(iRepCounty.getOne(iRepCity.getOne(user.getCityId()).getCountyId()).getProvinceId()).getName());
            return new OutUser(user.getFName() + " " +
                    user.getLName(), user.getAddress(), address.getProvince()
                    , address.getCounty(), address.getCity(), user.getType(), user.getStatus(), user.getImageUrl(), user.getGender(), "OK", null, null);
        } else {
            return new OutUser(null, null, null, null, null, 0, 0,
                    null, 0, "Failed", "دسترسی نا معتبر", "Token Not Exist");
        }
    }

    @PostMapping("/edit")
    public String userEdit(@RequestHeader HashMap<String, String> header,
                           @RequestParam("image") MultipartFile file,
                           @RequestParam("name") String name,
                           @RequestParam("lName") String lastName,
                           @RequestParam("address") String address,
                           @RequestParam("cityId") int cityId,
                           @RequestParam("gender") int gender) {
        String newFileName = String.valueOf(ThreadLocalRandom.current().nextInt(1000, 9999 + 1)) + file.getOriginalFilename();
        File convFile = new File(LOCAL_STORAGE_DIRECTORY + newFileName);
        try {
            file.transferTo(convFile);
        } catch (IOException ignored) {

        }
        User user = iRepUser.findByTokenId(header.get("token"));
        user.setFName(name);
        user.setLName(lastName);
        user.setAddress(address);
        user.setCityId(cityId);
        user.setGender(gender);
        user.setImageUrl("/uploads/" + newFileName);
        iRepUser.save(user);
        return "{\"status\":\"ok\"}";
    }

    @PostMapping("/create/list")
    public String CreateList(@RequestHeader HashMap<String, String> header, @RequestBody Create create) {
        String id = null;
        User user = iRepUser.findByTokenId(header.get("token"));
        if (user.getType() == 1) {
            Request request = new Request();
            request.setUserId(user.getId());
            request.setTitle(create.getTitle());
            request.setDesc(create.getDesc());
            request.setPhoneNumber(create.getPhoneNumber());
            request.setId(iRepRequest.save(request).getId());
            id=String.valueOf(request.getId());
            for (int i = 0; i < create.getSkillsList().size(); i++) {
                RequestSkills requestSkills = new RequestSkills();
                requestSkills.setDesc(create.getSkillsList().get(i).getDescribe());
                requestSkills.setRequestId(request.getId());
                requestSkills.setSkillsId(create.getSkillsList().get(i).getId());
                iRepRequestSkills.save(requestSkills);
            }
        } else {
            Offer offer = new Offer();
            offer.setUserId(user.getId());
            offer.setTitle(create.getTitle());
            offer.setDesc(create.getDesc());
            offer.setPhoneNumber(create.getPhoneNumber());
            offer.setId(iRepOffer.save(offer).getId());
            id=String.valueOf(offer.getId());
            for (int i = 0; i < create.getSkillsList().size(); i++) {
                OfferJob offerJob = new OfferJob();
                offerJob.setJobsId(create.getSkillsList().get(i).getId());
                offerJob.setOfferId(offer.getId());
                iRepOfferJob.save(offerJob);
            }
        }
        return id;
    }

    @GetMapping("/get/list/")
    public List<ListData> getList(@RequestHeader HashMap<String, String> header) {
        List<ListData> listData = new ArrayList<>();
        User user = iRepUser.findByTokenId(header.get("token"));
        if (user.getType() == 1) {
            List<Request> requestList = iRepRequest.findAllByUserId(user.getId());
            for (int i = 0; i < requestList.size(); i++) {
                ListData listData1 = new ListData();
                listData1.setCity(iRepCity.getOne(user.getCityId()).getName());
                listData1.setProvince(iRepProvince.getOne(iRepCounty.getOne(iRepCity.getOne(user.getCityId()).getCountyId()).getProvinceId()).getName());
                listData1.setId(requestList.get(i).getId());
                listData1.setDate(requestList.get(i).getDateModified().getTime());
                listData1.setDescribe(requestList.get(i).getDesc());
                listData1.setView(requestList.get(i).getView());
                listData1.setTitle(requestList.get(i).getTitle());
                listData.add(listData1);
            }
            return listData;
        } else {
            List<Offer> offerList = iRepOffer.findAllByUserId(user.getId());
            for (int i = 0; i < offerList.size(); i++) {
                ListData listData1 = new ListData();
                listData1.setCity(iRepCity.getOne(user.getCityId()).getName());
                listData1.setProvince(iRepProvince.getOne(iRepCounty.getOne(iRepCity.getOne(user.getCityId()).getCountyId()).getProvinceId()).getName());
                listData1.setId(offerList.get(i).getId());
                listData1.setDate(offerList.get(i).getDateModified().getTime());
                listData1.setDescribe(offerList.get(i).getDesc());
                listData1.setView(offerList.get(i).getView());
                listData1.setTitle(offerList.get(i).getTitle());
                listData.add(listData1);
            }
            return listData;
        }
    }
}
