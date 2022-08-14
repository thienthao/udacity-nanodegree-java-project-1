package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.udacity.jwdnd.course1.cloudstorage.exception.FileTypeException;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileController {

    @Autowired
    private HttpSession session;

    @Autowired
    private FileService fileService;

    @PostMapping("/files")
    public String upload(@RequestParam("fileUpload") MultipartFile file, Model model)
            throws IOException, SQLException, FileTypeException {
        int result = fileService.store(file, Integer.parseInt(session.getAttribute("userId").toString()));
        model.addAttribute("result", result == 1 ? "success" : "not saved");
        return "result";
    }

    @GetMapping("/file/download/{id}")
    public void download(@PathVariable("id") long id, HttpServletResponse response)
            throws IOException, SQLException {
        File file = fileService.find(id);
        response.setContentType(file.getContentType());
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getFileName());
        Blob blob = file.getData();
        InputStream inputStream = blob.getBinaryStream();
        int read;
        while ((read = inputStream.read()) != -1) {
            response.getWriter().write(read);
        }
    }

    @GetMapping("/file/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        fileService.delete(id);
        model.addAttribute("result", "success");
        return "result";
    }
}
