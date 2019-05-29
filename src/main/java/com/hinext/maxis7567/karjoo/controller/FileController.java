package com.hinext.maxis7567.karjoo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/file")
public class FileController {

    @GetMapping("/{name}")
    public File getFile(@PathVariable String name){
        File file = null;
        try {
            file=new File(new URI(UserController.LOCAL_STORAGE_DIRECTORY));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file;
    }
}
