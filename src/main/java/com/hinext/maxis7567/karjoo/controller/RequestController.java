package com.hinext.maxis7567.karjoo.controller;

import com.hinext.maxis7567.karjoo.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public RequestController(IRepRequest iRepRequest, IRepOffer iRepOffer, IRepSkills iRepSkills, IRepJobs iRepJobs, IRepOfferJob iRepOfferJob, IRepRequestJob iRepRequestJob, IRepOfferSkills iRepOfferSkills, IRepRequestSkills iRepRequestSkills) {
        this.iRepRequest = iRepRequest;
        this.iRepOffer = iRepOffer;
        this.iRepSkills = iRepSkills;
        this.iRepJobs = iRepJobs;
        this.iRepOfferJob = iRepOfferJob;
        this.iRepRequestJob = iRepRequestJob;
        this.iRepOfferSkills = iRepOfferSkills;
        this.iRepRequestSkills = iRepRequestSkills;
    }
//    PageRequest.of(p.getPageNumber(), p.getPageSize(), Sort.by(Sort.Direction.DESC, "id"))
    @GetMapping("/get/home/{page}")
    public void getHomeData(){}
    @GetMapping("/get/request/{page}")
    public void getRequest(){}
    @GetMapping("/get/offer/{page}")
    public void getOffer(){}


}
