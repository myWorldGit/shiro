package com.lanpangzi.conf.shiro;

import com.alibaba.druid.util.StringUtils;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@ConfigurationProperties(prefix = "ali.oss")
public class OssService {
    private String endpoint;
    private String keyid;
    private String keysecret;
    private String bucketname;
    private String filedir;


    private OSSClient ossClient;
    /**
     * 根据图片参数进行上传
     */
    public String uploadImgToOss(MultipartFile file) throws IOException{
        if(file.getSize() > 10*1024*1024) {
            throw new IOException("上传图片大于10M");
        }
        String orginalFilename = file.getOriginalFilename();
        //取后缀
        String suffix =orginalFilename.substring
                (orginalFilename.lastIndexOf(".")).toLowerCase();
        String prefix = UUID.randomUUID().toString().replace("-","");
        String filename = prefix+suffix;
        try {
            InputStream inputStream =file.getInputStream();
            this.uploadImgToOss(inputStream,filename);
            return filename;
        }catch (IOException e) {
            throw new IOException("图片上传失败");
        }
    }

    /**
     * 需要流  和一个唯一文件名
     * @param inputStream   文件流
     * @param fileName      文件名  （最好唯一
     * @return   md5  验证
     */
    public String uploadImgToOss(InputStream inputStream ,String fileName){
        String result="";
        try {
            ObjectMetadata objectMetadata= new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType
                    (fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            PutObjectResult putResult =
                    ossClient.putObject(bucketname, filedir + fileName, inputStream, objectMetadata);
            result = putResult.getETag();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream!=null)
                try {
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
        }
        return result;
    }

    /**
     * 取后缀判断文件类型  设置请求类型
     * @param filenameExtension  文件后缀  扩展名
     * @return
     */
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase("jpeg") ||
                filenameExtension.equalsIgnoreCase("jpg") ||
                filenameExtension.equalsIgnoreCase("png"))
        {
            return "image/jpeg";
        } if (filenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase("pptx") ||
                filenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase("docx") ||
                filenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /** *
     * 获得图片路径
     * @param fileUrl
     * @return
     * */
    public String getImgUrl(String fileUrl) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return this.getUrl(this.filedir + split[split.length - 1]);
        }
        return "" ;
    }



    public String getUrl(String key) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        URL url = ossClient.generatePresignedUrl(bucketname, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return "";
    }





    public void init() {
        ossClient = new OSSClient(endpoint, keyid, keysecret);
    }
    public void destory(){
        ossClient.shutdown();
    }


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getKeysecret() {
        return keysecret;
    }

    public void setKeysecret(String keysecret) {
        this.keysecret = keysecret;
    }

    public String getBucketname() {
        return bucketname;
    }

    public void setBucketname(String bucketname) {
        this.bucketname = bucketname;
    }

    public String getFiledir() {
        return filedir;
    }

    public void setFiledir(String filedir) {
        this.filedir = filedir;
    }

    public OSSClient getOssClient() {
        return ossClient;
    }

    public void setOssClient(OSSClient ossClient) {
        this.ossClient = ossClient;
    }
}
