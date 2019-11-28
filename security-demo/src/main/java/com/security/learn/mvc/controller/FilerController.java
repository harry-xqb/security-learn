package com.security.learn.mvc.controller;

import com.security.learn.mvc.dto.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * create by： harry
 * date:  2019/11/24 0024 下午 4:13
 **/
@RestController
@RequestMapping("/file")
public class FilerController {

    private final static String folder = "F:/idea-projects/harrysecurity/harry-security-demo/src/main/resources/files";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println("name:" + file.getName());
        System.out.println("origin name:" + file.getOriginalFilename());
        System.out.println("size:" + file.getSize());
        int sub = file.getOriginalFilename().lastIndexOf(".");
        String type = file.getOriginalFilename().substring(sub);
        Long id = new Date().getTime();
        File localFile = new File(folder, id + type);
        file.transferTo(localFile);
        return new FileInfo(id, localFile.getAbsolutePath(), file.getOriginalFilename());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        try( ServletOutputStream outputStream = response.getOutputStream();
             FileInputStream inputStream = new FileInputStream(new File(folder, id + ".docx"));){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=hello.docx");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }catch (Exception e){
            System.out.println("下载异常---");
        }
    }
}
