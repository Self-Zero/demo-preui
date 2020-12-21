package com.oac.project.common.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/file/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 文件上传
     */
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> doUpload(MultipartFile file) {
        System.out.println("上传的文件名字：" + file.getOriginalFilename());
        Map<String, Object> map = new HashMap<>();
        String filepath = uploadService.uploadImage(file);
        System.out.println("返回的路径是" + filepath);
        map.put("paths", filepath);
        return map;
    }

}
