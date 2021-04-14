package me.zbl.fullstack.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class OssUtil {
    private static String secretId;
    private static String secretKey;
    private static String bucket;
    private static String PathKey;

    @Value("${tengxunyun.oss.secretId}")
    public  void setSecretId(String secretId){
        OssUtil.secretId = secretId;
    }

    @Value("${tengxunyun.oss.secretKey}")
    public  void setSecretKey(String secretKey){
        OssUtil.secretKey = secretKey;
    }
    @Value("${tengxunyun.oss.bucket}")
    public  void setBucket(String bucket){
        OssUtil.bucket = bucket;
    }
    @Value("${tengxunyun.oss.PathKey}")
    public  void setPathKey(String PathKey){
        OssUtil.PathKey = PathKey;
    }

    private static COSClient cosClient;

    private static OssUtil ossUtil;
    /**
     * 图片链接地址
     */
    private static String ossUrl = "https://zpxblogs-1259581391.cos.ap-chengdu.myqcloud.com/";

    /**
     * 初始化
     */
    public static void init(){
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-chengdu");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
    }



    /**
     * 上传普通文件路径
     * @param path
     * @return  返回线上地址
     * @throws IOException
     */
    public static Message putObject(String path){
        File localFile = new File(path);
        String a =localFile.getName();
        String key = PathKey+a;
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            if(putObjectResult != null){
                String imageUrl = ossUrl+key;
                return Message.success(imageUrl);
            }
        }catch (Throwable e){
            System.out.println("上传文件失败");

        }

        return Message.error("上传文件失败");

    }

    /**
     * 删除单个文件
     * @param fileName
     * @return
     */
    public static  Message deleteObject(String fileName){
        String key = PathKey+fileName;
        try{
        cosClient.deleteObject(bucket, key);
        return Message.success();
        }catch (Throwable e){
            System.out.println(e.toString()+"删除文件失败");
        }
        return Message.error("删除文件失败");
    }





    public static void main(String [] arr) throws IOException {
//        getInstance();
//        putObject("/Users/wen/Desktop/upload_075d96db-6424-4ef-a8d9-137a97134fcc.png");
//         deleteObject("upload_075d96db-6424-4ef3-a8d9-137a97134fdd.png");

    }



}
