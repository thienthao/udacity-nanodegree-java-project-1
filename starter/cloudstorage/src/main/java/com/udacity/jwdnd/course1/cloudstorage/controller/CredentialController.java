package com.udacity.jwdnd.course1.cloudstorage.controller;

import javax.servlet.http.HttpSession;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CredentialController {

    @Autowired
    private HttpSession session;

    @Autowired
    private CredentialService credentialService;

    @PostMapping("/credential")
    public String add(Credential credential, Model model) {
        credential.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        credentialService.add(credential);
        model.addAttribute("result", "success");
        return "result";
    }

    @PostMapping("/credential/update")
    public String update(Credential credential, Model model) {
        credential.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        credentialService.update(credential);
        model.addAttribute("result", "success");
        return "result";
    }

    @GetMapping("/credential/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        credentialService.delete(id);
        model.addAttribute("result", "success");
        return "result";
    }
}
