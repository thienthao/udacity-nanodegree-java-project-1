package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.repository.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

  @Autowired private NoteMapper noteMapper;

  public List<Note> findAll() {
    return noteMapper.findAll();
  }

  public List<Note> findAllByUserId(int userId) {
    return noteMapper.findAllByUserId(userId);
  }

  public int add(Note note) {
    return noteMapper.insert(note);
  }

  public int update(Note note) {
    return noteMapper.update(note);
  }

  public int delete(long id) {
    return noteMapper.delete(id);
  }
}
