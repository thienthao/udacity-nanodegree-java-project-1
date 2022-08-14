package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;


@Controller
public class CustomErrorController implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public String handleError(WebRequest webRequest) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());

        String status = attributes.get("status").toString();
        switch (status) {
        case "404":
            return "/error/404";
        case "403":
            return "/error/403";
        default:
            return "error";
        }
    }

}
