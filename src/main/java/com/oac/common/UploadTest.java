package com.oac.common;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
public class UploadTest {

    @RequestMapping(value = "/upload/Page", method = RequestMethod.POST)
    public Object getImg(@RequestParam(value = "file", required = false) MultipartFile attach, HttpServletRequest request, HttpServletResponse response) {
        String carPictuerUrl = null;
        System.out.println("111111111111" + attach);

        //判断文件是否为空
        if (!attach.isEmpty()) {
            String path1 = request.getSession().getServletContext().getRealPath("/");
            String path = request.getSession().getServletContext().getRealPath("statics/uploadfiles");
            System.out.println("path1:===============" + path1);
            System.out.println("path======" + path);
            String oldFileName = attach.getOriginalFilename();//原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            /**
             * RandomUtils.nextInt(1, 1000000)
             * 返回一个在指定区间内的整数
             * startInclusive 可以返回的最小值必须是非负的
             * endExclusive 上限（不包括）
             */
            //String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"."+prefix;
            String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1, 1000000) + "." + prefix;
            File targetFile = new File(path, fileName);  //创建文件
            if (!targetFile.exists()) {  //判断文件夹是否存在
                targetFile.mkdirs();
            }

            try {
                attach.transferTo(targetFile);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            carPictuerUrl = "statics/uploadfiles/" + fileName;
        }

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", map2);
        map2.put("src", carPictuerUrl);
        return map;
    }
}
