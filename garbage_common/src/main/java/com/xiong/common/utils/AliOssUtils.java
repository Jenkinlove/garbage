package com.xiong.common.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AliOssUtils {
    /**
     * 上传某个Object
     */
    public static String putObject(String bucketName, MultipartFile file,
                                   String endpoint, String accessKeyId, String accessKeySecret) throws IOException {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            if (file.isEmpty()) {
                throw new ServiceException("upload.file.is.null");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String objectName = sdf.format(new Date()) + file.getOriginalFilename();

            File uploadFile = new File(file.getOriginalFilename());
            FileUtils.copyInputStreamToFile(file.getInputStream(), uploadFile);

            // 上传Object.
            client.putObject(bucketName, objectName, uploadFile);
            String url = "http://" + bucketName + "." + endpoint + "/" + objectName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            client.shutdown();
        }
    }

    /**
     * 删除某个Object
     */
    public static boolean deleteObject(String bucketName, String objectName, String endpoint,
                                       String accessKeyId, String accessKeySecret) {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 删除Object.
            client.deleteObject(bucketName, objectName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            client.shutdown();
        }
        return true;
    }

    /**
     * 删除多个Object
     */
    public static boolean deleteObjects(String bucketName, List<String> objectNames,
                                        String endpoint,
                                        String accessKeyId, String accessKeySecret) {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 删除Object.
            DeleteObjectsResult deleteObjectsResult = client.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(objectNames));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            client.shutdown();
        }
        return true;
    }

}
