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

    private static String ossUrl = "https://zpxblogs-1259581391.cos.ap-chengdu.myqcloud.com/";

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
     * @return  返回线上地址
     * @throws IOException
     */
    public Message putObject(String path){
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
    public   Message deleteObject(String fileName){
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
        getInstance();
//        putObject("/Users/wen/Desktop/upload_075d96db-6424-4ef-a8d9-137a97134fcc.png");
//         deleteObject("upload_075d96db-6424-4ef3-a8d9-137a97134fdd.png");

    }



}
