package com.lzf.ez4webcast.image;

import com.lzf.ez4webcast.common.ServiceResponse;
import com.lzf.ez4webcast.image.service.BasicImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/9 16:14
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private BasicImageService basicImageService;

    @GetMapping("load")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.setStatus(400);
            return;
        }

        ServiceResponse<String> resp = basicImageService.readImage(id, response.getOutputStream());
        if(resp.success()) {
            response.setContentType(resp.data());
        } else if(resp.code() == 1 || resp.code() == 2) {
            response.setStatus(404);
        } else {
            response.setStatus(500);
        }
    }

    @RequestMapping(value = "upload", method = {RequestMethod.POST, RequestMethod.PUT})
    public void uploadImage(@RequestParam("file") MultipartFile file, HttpServletResponse response)
            throws IOException {
        ServiceResponse<Void> resp = basicImageService.uploadImage(file.getBytes(), file.getContentType());
        if(resp.success()) {
            return;
        }

        int code = resp.code();
        switch (code) {
            case 1: response.setStatus(403); break;
            case 2: response.setStatus(500); break;
        }
    }

}
