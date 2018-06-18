package com.xjy.security.controller;

import com.xjy.security.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1812:03
 * @Description:
 * @Modified By:
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    //文件上传
    @PostMapping
    public void upload(MultipartFile file, User user) {
        log.info(file.getName());
        log.info(file.getOriginalFilename());
        log.info(file.getSize() + "");
    }

    //文件下载0
    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            log.info("========start================");
            long start_time = System.currentTimeMillis();
            inputStream = new FileInputStream(new File("C://Users//xiaojianyu//Desktop//picture//Chap4.zip"));
            outputStream = response.getOutputStream();

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=dChap4.zip");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            log.info("========end================{}",System.currentTimeMillis()-start_time);
        } finally {
            inputStream.close();
            outputStream.close();
        }
    }



}
