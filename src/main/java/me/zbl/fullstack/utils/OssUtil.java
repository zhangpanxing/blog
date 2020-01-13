package me.zbl.fullstack.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.IOException;
import java.util.List;

@PropertySource(value = "classpath:*.yml")
public class OssUtil {

    @Value("AKIDA5mESRjr42pwoQrysa6QK2WlocbLQ55p")
    private String secretId = "AKIDA5mESRjr42pwoQrysa6QK2WlocbLQ55p";
    @Value("h8KL8gz3sRlpllX4MOAhy2sTpwEQN5f0")
    private String secretKey = "h8KL8gz3sRlpllX4MOAhy2sTpwEQN5f0";

    private static COSClient cosClient;

    private static OssUtil ossUtil;

    @Value("zpxblogs-1259581391")
    private static String bucket = "zpxblogs-1259581391";

    private static String PathKey = "/Blogs/image/";

    /**
     * 初始化
     */
    private OssUtil(){
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-chengdu");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
    }

    /**
     * 实例化
     * @return
     */
    public static OssUtil getInstance(){
        synchronized (OssUtil.class){
             ossUtil = new OssUtil();
        }
        return ossUtil;
    }

    /**
     * 上传普通文件路径
     * @param path
     * @return
     * @throws IOException
     */
    private  PutObjectResult putObject(String path){
        File localFile = new File(path);
        String a =localFile.getName();
        String PathKey = this.PathKey+a;
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, PathKey, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return putObjectResult;
        }catch (Throwable e){
            System.out.println("上传文件失败");
        }

        return null;

    }





    public static void main(String [] arr) throws IOException {
        PutObjectResult putObjectResult = OssUtil.getInstance().putObject("/Users/wen/Desktop/upload_075d96db-6424-4ef3-a8d9-137a97134fdd.png");
        System.out.println(putObjectResult);
    }



}
