package kr.co.within.dubra.controller;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/agency")
public class AgencyController {
    @GetMapping
    public String getAgency(){return "Agency";}
}
