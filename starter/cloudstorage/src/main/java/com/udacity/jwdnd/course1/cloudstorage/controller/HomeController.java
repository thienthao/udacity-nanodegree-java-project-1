package com.udacity.jwdnd.course1.cloudstorage.controller;

import javax.servlet.http.HttpSession;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @Autowired private HttpSession session;

  @Autowired private NoteService noteService;

  @Autowired private FileService fileService;

  @Autowired private UserService userService;

  @Autowired private CredentialService credentialService;

  @Autowired private EncryptionService encryptionService;

  @RequestMapping("/")
  public String home(Model model, Authentication authentication) {
    User user = userService.findByUsername(authentication.getName());
    int userId = Integer.parseInt(session.getAttribute("userId").toString());
    model.addAttribute("user", user);
    model.addAttribute("note", new Note());
    model.addAttribute("credential", new Credential());
    model.addAttribute("file", new File());
    model.addAttribute("notes", noteService.findAllByUserId(userId));
    model.addAttribute("files", fileService.findAllByUserId(userId));
    model.addAttribute("credentials", credentialService.findAllByUserId(userId));
    model.addAttribute("encryptionService", encryptionService);
    return "home";
  }
}
