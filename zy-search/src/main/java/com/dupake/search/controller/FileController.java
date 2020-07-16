package com.dupake.search.controller;

import com.dupake.search.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/export")
    public void export( HttpServletResponse response) {
        fileService.export(response);

    }

    @PostMapping("/importExcel")
    public void importExcel(MultipartFile file) {
        fileService.importExcel(file);

    }

}
