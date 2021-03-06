package com.hinext.maxis7567.karjoo.controller;

import com.hinext.maxis7567.karjoo.entity.*;
import com.hinext.maxis7567.karjoo.inModel.Skils;
import com.hinext.maxis7567.karjoo.outModels.File;
import com.hinext.maxis7567.karjoo.outModels.HomeData;
import com.hinext.maxis7567.karjoo.outModels.OutRequest;
import com.hinext.maxis7567.karjoo.outModels.Skills;
import com.hinext.maxis7567.karjoo.repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.hinext.maxis7567.karjoo.controller.UserController.LOCAL_STORAGE_DIRECTORY;

@RestController
@RequestMapping("/v1")
public class RequestController {
    private IRepRequest iRepRequest;
    private IRepOffer iRepOffer;
    private IRepSkills iRepSkills;
    private IRepJobs iRepJobs;
    private IRepOfferJob iRepOfferJob;
    private IRepRequestJob iRepRequestJob;
    private IRepOfferSkills iRepOfferSkills;
    private IRepRequestSkills iRepRequestSkills;
    private IRepUser iRepUser;
    private IRepProvince iRepProvince;
    private IRepCity iRepCity;
    private IRepFile iRepFile;

    public RequestController(IRepRequest iRepRequest, IRepOffer iRepOffer, IRepSkills iRepSkills, IRepJobs iRepJobs, IRepOfferJob iRepOfferJob, IRepRequestJob iRepRequestJob, IRepOfferSkills iRepOfferSkills, IRepRequestSkills iRepRequestSkills, IRepUser iRepUser, IRepProvince iRepProvince, IRepCity iRepCity, IRepFile iRepFile) {
        this.iRepRequest = iRepRequest;
        this.iRepOffer = iRepOffer;
        this.iRepSkills = iRepSkills;
        this.iRepJobs = iRepJobs;
        this.iRepOfferJob = iRepOfferJob;
        this.iRepRequestJob = iRepRequestJob;
        this.iRepOfferSkills = iRepOfferSkills;
        this.iRepRequestSkills = iRepRequestSkills;
        this.iRepUser = iRepUser;
        this.iRepProvince = iRepProvince;
        this.iRepCity = iRepCity;
        this.iRepFile = iRepFile;
    }

    //    PageRequest.of(p.getPageNumber(), p.getPageSize(), Sort.by(Sort.Direction.DESC, "id"))
    @GetMapping("/get/home/{page}")
    public List<HomeData> getHomeData(@PathVariable int page) {
        List<HomeData> homeDataList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by(Sort.Direction.DESC, "dateModified"));
        List<Request> requestList = iRepRequest.findAll(pageable).getContent();
        for (int i = 0; i < requestList.size(); i++) {
            User user = iRepUser.getOne(requestList.get(i).getUserId());
            HomeData newHomeData = new HomeData();
            newHomeData.setName(user.getFName() + " " + user.getLName());
            newHomeData.setCity(iRepCity.getOne(user.getCityId()).getName());
            newHomeData.setProvince(iRepProvince.getOne(iRepCity.getOne(user.getCityId()).getProvinceId()).getName());
            newHomeData.setType(1);
            newHomeData.setDescribe(requestList.get(i).getDesc());
            newHomeData.setDate(requestList.get(i).getDateModified().getTime());
            newHomeData.setImage(user.getImageUrl());
            newHomeData.setId(requestList.get(i).getId());
            newHomeData.setTitle(requestList.get(i).getTitle());
            homeDataList.add(newHomeData);
        }
        List<Offer> offerList = iRepOffer.findAll(pageable).getContent();
        for (int i = 0; i < offerList.size(); i++) {
            User user = iRepUser.getOne(offerList.get(i).getUserId());
            HomeData newHomeData = new HomeData();
            newHomeData.setName(user.getFName() + " " + user.getLName());
            newHomeData.setCity(iRepCity.getOne(user.getCityId()).getName());
            newHomeData.setProvince(iRepProvince.getOne(iRepCity.getOne(user.getCityId()).getProvinceId()).getName());
            newHomeData.setType(2);
            newHomeData.setDescribe(offerList.get(i).getDesc());
            newHomeData.setDate(offerList.get(i).getDateModified().getTime());
            newHomeData.setImage(user.getImageUrl());
            newHomeData.setId(offerList.get(i).getId());
            newHomeData.setTitle(offerList.get(i).getTitle());
            homeDataList.add(newHomeData);
        }
        homeDataList.sort(Comparator.comparing(HomeData::getDate).reversed());
        return homeDataList;
    }

    @GetMapping("/get/request/{page}")
    public List<HomeData> getRequest(@PathVariable int page) {
        List<HomeData> homeDataList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "dateModified"));
        List<Request> requestList = iRepRequest.findAll(pageable).getContent();
        for (int i = 0; i < requestList.size(); i++) {
            User user = iRepUser.getOne(requestList.get(i).getUserId());
            HomeData newHomeData = new HomeData();
            newHomeData.setName(user.getFName() + " " + user.getLName());
            newHomeData.setCity(iRepCity.getOne(user.getCityId()).getName());
            newHomeData.setProvince(iRepProvince.getOne(iRepCity.getOne(user.getCityId()).getProvinceId()).getName());
            newHomeData.setType(1);
            newHomeData.setDescribe(requestList.get(i).getDesc());
            newHomeData.setDate(requestList.get(i).getDateModified().getTime());
            newHomeData.setImage(user.getImageUrl());
            newHomeData.setId(requestList.get(i).getId());
            newHomeData.setTitle(requestList.get(i).getTitle());
            homeDataList.add(newHomeData);
        }
        return homeDataList;
    }

    @GetMapping("/get/offer/{page}")
    public List<HomeData> getOffer(@PathVariable int page) {
        List<HomeData> homeDataList = new ArrayList<>();
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "dateModified"));
        List<Offer> offerList = iRepOffer.findAll(pageable).getContent();
        for (int i = 0; i < offerList.size(); i++) {
            User user = iRepUser.getOne(offerList.get(i).getUserId());
            HomeData newHomeData = new HomeData();
            newHomeData.setName(user.getFName() + " " + user.getLName());
            newHomeData.setCity(iRepCity.getOne(user.getCityId()).getName());
            newHomeData.setProvince(iRepProvince.getOne(iRepCity.getOne(user.getCityId()).getProvinceId()).getName());
            newHomeData.setType(2);
            newHomeData.setDescribe(offerList.get(i).getDesc());
            newHomeData.setDate(offerList.get(i).getDateModified().getTime());
            newHomeData.setImage(user.getImageUrl());
            newHomeData.setId(offerList.get(i).getId());
            newHomeData.setTitle(offerList.get(i).getTitle());
            homeDataList.add(newHomeData);
        }
        return homeDataList;
    }

    @GetMapping("/get/request/search/{q}")
    public List<com.hinext.maxis7567.karjoo.entity.Skills> getRequestSearch(@PathVariable String q) {
        return iRepSkills.findAllByNameContains(q);

    }

    @GetMapping("/get/request/searchId/{id}/{page}")
    public List<HomeData> getRequestSearchId(@PathVariable int id, @PathVariable int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        List<Request> requestList = new ArrayList<>();
        List<RequestSkills> requestSkills = iRepRequestSkills.findAllBySkillsId(id, pageable);
        for (int i = 0; i < requestSkills.size(); i++) {
            requestList.add(iRepRequest.getOne(requestSkills.get(i).getRequestId()));
        }
        List<HomeData> homeDataList = new ArrayList<>();
        for (int i = 0; i < requestList.size(); i++) {
            User user = iRepUser.getOne(requestList.get(i).getUserId());
            HomeData newHomeData = new HomeData();
            newHomeData.setName(user.getFName() + " " + user.getLName());
            newHomeData.setCity(iRepCity.getOne(user.getCityId()).getName());
            newHomeData.setProvince(iRepProvince.getOne(iRepCity.getOne(user.getCityId()).getProvinceId()).getName());
            newHomeData.setType(1);
            newHomeData.setDescribe(requestList.get(i).getDesc());
            newHomeData.setDate(requestList.get(i).getDateModified().getTime());
            newHomeData.setImage(user.getImageUrl());
            newHomeData.setId(requestList.get(i).getId());
            newHomeData.setTitle(requestList.get(i).getTitle());
            homeDataList.add(newHomeData);
        }
        return homeDataList;
    }

    @GetMapping("/get/offer/search/{q}")
    public List<Jobs> getOfferSearch(@PathVariable String q) {
        return iRepJobs.findAllByNameContains(q);

    }

    @GetMapping("/get/offer/searchId/{id}/{page}")
    public List<HomeData> getofferSearchId(@PathVariable int id, @PathVariable int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        List<Offer> offerList = new ArrayList<>();
        List<OfferJob> offerJobList = iRepOfferJob.findAllByJobsId(id, pageable);
        for (int i = 0; i < offerJobList.size(); i++) {
            offerList.add(iRepOffer.getOne(offerJobList.get(i).getOfferId()));
        }
        List<HomeData> homeDataList = new ArrayList<>();
        for (int i = 0; i < offerList.size(); i++) {
            User user = iRepUser.getOne(offerList.get(i).getUserId());
            HomeData newHomeData = new HomeData();
            newHomeData.setName(user.getFName() + " " + user.getLName());
            newHomeData.setCity(iRepCity.getOne(user.getCityId()).getName());
            newHomeData.setProvince(iRepProvince.getOne(iRepCity.getOne(user.getCityId()).getProvinceId()).getName());
            newHomeData.setType(2);
            newHomeData.setDescribe(offerList.get(i).getDesc());
            newHomeData.setDate(offerList.get(i).getDateModified().getTime());
            newHomeData.setImage(user.getImageUrl());
            newHomeData.setId(offerList.get(i).getId());
            newHomeData.setTitle(offerList.get(i).getTitle());
            homeDataList.add(newHomeData);
        }
        return homeDataList;
    }

    @GetMapping("get/request/detail/{id}")
    public OutRequest getDetailRequest(@PathVariable int id) {
        OutRequest outRequest = new OutRequest();
        Request request = iRepRequest.getOne(id);
        User user = iRepUser.getOne(request.getUserId());
        outRequest.setAddress(user.getAddress());
        outRequest.setNumber(request.getPhoneNumber());
        List<Skils> skillsList = new ArrayList<>();
        List<RequestSkills> requestSkillsList = iRepRequestSkills.findAllByRequestId(request.getId());
        for (int i = 0; i < requestSkillsList.size(); i++) {
            com.hinext.maxis7567.karjoo.entity.Skills skills = (iRepSkills.getOne(requestSkillsList.get(i).getSkillsId()));
            Skils skills1 = new Skils();
            skills1.setName(skills.getName());
            skills1.setDescribe(requestSkillsList.get(i).getDesc());
            skillsList.add(skills1);
        }
        List<File> fileList=new ArrayList<>();
        List<Files> files=iRepFile.findAllByRequestId(request.getId());
        for (int i = 0; i < files.size(); i++) {
            File file=new File();
            file.setName(files.get(i).getFileName());
            file.setDesc(files.get(i).getDesc());
            file.setUrl(files.get(i).getFileUrl());
            fileList.add(file);
        }
        outRequest.setFile(fileList);
        outRequest.setSkills(skillsList);
        request.setView(request.getView()+1);
        iRepRequest.save(request);
        return outRequest;
    }
    @GetMapping("get/offer/detail/{id}")
    public OutRequest getDetailOffer(@PathVariable int id) {
        OutRequest outRequest = new OutRequest();
        Offer offer = iRepOffer.getOne(id);
        User user = iRepUser.getOne(offer.getUserId());
        outRequest.setAddress(user.getAddress());
        outRequest.setNumber(offer.getPhoneNumber());
        List<Skils> skillsList = new ArrayList<>();
        List<OfferJob> requestSkillsList = iRepOfferJob.findAllByOfferId(offer.getId());
        for (int i = 0; i < requestSkillsList.size(); i++) {
            Jobs skills = (iRepJobs.getOne(requestSkillsList.get(i).getJobsId()));
            Skils skills1 = new Skils();
            skills1.setName(skills.getName());
            skillsList.add(skills1);
        }
        List<File> fileList=new ArrayList<>();
        List<Files> files=iRepFile.findAllByOfferId(offer.getId());
        for (int i = 0; i < files.size(); i++) {
            File file=new File();
            file.setName(files.get(i).getFileName());
            file.setDesc(files.get(i).getDesc());
            file.setUrl(files.get(i).getFileUrl());
            fileList.add(file);
        }
        outRequest.setFile(fileList);
        outRequest.setSkills(skillsList);
        offer.setView(offer.getView()+1);
        iRepOffer.save(offer);
        return outRequest;
    }
    @GetMapping("/tags/add/{name}/{type}")
    public String addSkills(@PathVariable String name,@PathVariable int type){
        if (type==1){
            com.hinext.maxis7567.karjoo.entity.Skills skills=new com.hinext.maxis7567.karjoo.entity.Skills();
            skills.setName(name);
            return String.valueOf(iRepSkills.save(skills).getId());

        }else {
            Jobs jobs=new Jobs();
            jobs.setName(name);
            return String.valueOf(iRepJobs.save(jobs).getId());
        }
    }
    @PostMapping("/upload")
    public String upload(@RequestHeader HashMap<String, String> header,
                           @RequestParam("file") MultipartFile file){
        User user = iRepUser.findByTokenId(header.get("token"));
        String newFileName = String.valueOf(ThreadLocalRandom.current().nextInt(1000, 9999 + 1)) + file.getOriginalFilename();
        java.io.File convFile = new java.io.File(LOCAL_STORAGE_DIRECTORY + newFileName);
        try {
            file.transferTo(convFile);
        } catch (IOException ignored) {

        }
        Files files=new Files();
        files.setFileName(newFileName);
        files.setFileUrl("/uploads/" + newFileName);
        files.setDesc(header.get("desc"));
        if (user.getType()==1){
            files.setRequestId(Integer.valueOf(header.get("id")));
        }else {
            files.setOfferId(Integer.valueOf(header.get("id")));

        }
        iRepFile.save(files);
        return "{\"status\":\"ok\"}";
    }
}
