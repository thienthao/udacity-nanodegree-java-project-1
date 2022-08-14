package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class NoteController {

  @Autowired
  HttpSession session;

  @Autowired private NoteService noteService;

  @GetMapping("/note")
  public List<Note> findAll() {
    return noteService.findAll();
  }

  @PostMapping("/note")
  public String add(Note note, Model model) {
    note.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
    noteService.add(note);
    model.addAttribute("result", "success");
    return "result";

  }

  @PostMapping("/note/update")
  public String update(Note note, Model model) {
    note.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
    noteService.update(note);
    model.addAttribute("result", "success");
    return "result";
  }

  @GetMapping("/note/{id}")
  public String delete(@PathVariable("id") long id, Model model) {
    noteService.delete(id);
    model.addAttribute("result", "success");
    return "result";
  }
}
