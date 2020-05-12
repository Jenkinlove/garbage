package com.xiong.admin.controller;

import com.xiong.admin.oss.AliOssProperty;
import com.xiong.common.utils.AliOssUtils;
import com.xiong.common.utils.Response;
import com.xiong.common.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private AliOssProperty aliOssProperty;

    @RequestMapping("/uploadFile")
    public Response<String> uploadFile(MultipartFile file) {
        try {
            //上传文件到阿里云OSS中
            String url = AliOssUtils.putObject(aliOssProperty.getBucketName(), file,
                    aliOssProperty.getEndpoint(), aliOssProperty.getAccessKeyId(),
                    aliOssProperty.getAccessKeySecret());
            if (StringUtils.isEmpty(url)) {
                throw new ServiceException("upload.file.url.is.null");
            }
            return Response.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("upload.file.fail");
        }
    }

    @RequestMapping("/uploadImage")
    public Map uploadImage(MultipartFile upfile) throws Exception {
        try {
            //上传至阿里云oss中
            String url = AliOssUtils.putObject(aliOssProperty.getBucketName(), upfile,
                    aliOssProperty.getEndpoint(), aliOssProperty.getAccessKeyId(),
                    aliOssProperty.getAccessKeySecret());
            Map<String, Object> result = new HashMap<>();
            result.put("state", "SUCCESS");
            result.put("url", url);
            result.put("title", upfile.getOriginalFilename());
            result.put("original", upfile.getOriginalFilename());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/delete")
    public Response<Boolean> delete(@RequestParam(value = "url") String url) {
        try {
            if (StringUtils.isEmpty(url)) {
                throw new ServiceException("delete.url.is.null");
            }
            String sub = "http://" + aliOssProperty.getBucketName() + "." + aliOssProperty.getEndpoint() +
                    "/";
            String objectName = url.substring(sub.length());
            AliOssUtils.deleteObject(aliOssProperty.getBucketName(), objectName,
                    aliOssProperty.getEndpoint(), aliOssProperty.getAccessKeyId(),
                    aliOssProperty.getAccessKeySecret());
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("delete.image.fail");
        }
    }

}
