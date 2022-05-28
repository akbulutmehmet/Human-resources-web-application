package com.mehmet.akbulut.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class DenemeController {

    @GetMapping("/deneme")
    public String deneme () {
        return "deneme";
    }

    @PostMapping("/kaydet")
    public @ResponseBody String kaydet (@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws Exception{

        String path = request.getServletContext().getRealPath("/resources/diplomalar/");
        String fileName = file.getOriginalFilename();
        byte [] bytes = file.getBytes();
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new
                    FileOutputStream(
                    new File(path + File.separator + fileName)
            )
            );
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path + File.separator + fileName;
    }
}
