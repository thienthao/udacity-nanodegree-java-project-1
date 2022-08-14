package com.udacity.jwdnd.course1.cloudstorage.exception;

import java.nio.file.FileAlreadyExistsException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


@ControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleFileSizeException(Model model) {
        model.addAttribute("result", "error");
        model.addAttribute("message", "File size is too big");
        return "result";
    }

    @ExceptionHandler(FileTypeException.class)
    public String handleFileTypeException(Model model) {
        model.addAttribute("result", "error");
        model.addAttribute("message", "This file type is not allow");
        return "result";
    }

    @ExceptionHandler(FileAlreadyExistsException.class)
    public String handleFileAlreadyExistsException(Model model) {
        model.addAttribute("result", "error");
        model.addAttribute("message", "File already exists");
        return "result";
    }
}
