package com.milin.loveth.controller;

import com.milin.loveth.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Milin on 2016/11/8.
 */
@Controller
public class ImagesController extends BaseController {

    @Autowired
    private ImagesService imagesService;

    @RequestMapping("images/fall.html")
    public String toFall(Map<String, Object> paramMap) {
        paramMap.put("imgList", imagesService.loadRandomImageLists(-1));
        paramMap.put("mode", "manage");
        return "images/fall";
    }

    @RequestMapping("fall.html")
    public String toViewFall(Map<String, Object> paramMap) {
        paramMap.put("imgList", imagesService.loadRandomImageLists(-1));
        paramMap.put("mode", "view");
        return "images/fall";
    }

    @RequestMapping("love.html")
    public String toViewer(Map<String, Object> paramMap) {
        paramMap.put("imgList", imagesService.loadRandomImageLists(-1));
        return "images/viewer";
    }

    @RequestMapping("images/deleteImg.do")
    @ResponseBody
    public String deleteImg(String imgName) {
        imagesService.deleteImg(imgName);
        return "success";
    }

    @RequestMapping("images/uploadImg.do")
    @ResponseBody
    public String upload(MultipartFile file, Long fileLen) throws IOException {
        imagesService.saveImg(file, fileLen);
        return "success";
    }

    @RequestMapping("/")
    public String index(Map<String, Object> paramMap) {
        paramMap.put("imgList", imagesService.loadRandomImageLists(12));
        return "images/wall";
    }

}
