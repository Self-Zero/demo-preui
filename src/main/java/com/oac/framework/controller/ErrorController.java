package com.oac.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errorPageController")
public class ErrorController {

    @RequestMapping("/error_{errorCode}")
    public String error(@PathVariable int errorCode){
        String responseMsg;
        switch (errorCode) {
            case 400: responseMsg = "/403.html"; break;
            case 401: responseMsg = "/403.html"; break;
            case 404: responseMsg = "/403.html"; break;
            case 500: responseMsg = "/403.html"; break;
            default: responseMsg = "/403.html"; break;
        }
        return responseMsg;
    }

}
