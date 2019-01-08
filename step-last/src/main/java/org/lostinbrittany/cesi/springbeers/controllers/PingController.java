package org.lostinbrittany.cesi.springbeers.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    
    @RequestMapping("/authorized")
    public String isAuthorized() {
    	return "{\"authorized\":true}";
    }
    
}
