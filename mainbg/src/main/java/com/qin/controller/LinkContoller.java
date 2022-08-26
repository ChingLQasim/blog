package com.qin.controller;

import com.qin.domain.ResponseResult;
import com.qin.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkContoller {
    @Autowired
    private LinkService linkService;

    @GetMapping("getAllLink ")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }
}
