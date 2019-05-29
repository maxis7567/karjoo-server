package com.hinext.maxis7567.karjoo.controller;

import com.hinext.maxis7567.karjoo.repository.IRepVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/version")
public class VersionController {
    private IRepVersion iRepVersion;

    public VersionController(IRepVersion iRepVersion) {
        this.iRepVersion = iRepVersion;
    }

    @GetMapping("/android/{version}")
    public String androidVersion(@PathVariable String version){
        if (iRepVersion.getOne(1).getLastA()<=Integer.valueOf(version)){
            return "1";
        }else if (iRepVersion.getOne(1).getSupportedA()<=Integer.valueOf(version)){
            return "2";
        }else {
            return "3";
        }
    }
}
