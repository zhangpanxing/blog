package me.zbl.fullstack.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Base64;

/**
 * 文件相关操作辅助类。
 *
 * @author
 * @date 2019年06月24日
 */
public class FileUtil {
    private static final String FOLDER_SEPARATOR = "/";
    private static final char EXTENSION_SEPARATOR = '.';

    /**
     * 功能：复制文件或者文件夹。
     *
     * @author 张盼兴
     * @date 2019年06月24日
     * @param inputFile
     *            源文件
     * @param outputFile
     *            目的文件
     * @param isOverWrite
     *            是否覆盖(只针对文件)
     * @throws IOException
     */
    public static void copy(File inputFile, File outputFile, boolean isOverWrite)
            throws IOException {
        if (!inputFile.exists()) {
            throw new RuntimeException(inputFile.getPath() + "源目录不存在!");
        }
        copyPri(inputFile, outputFile, isOverWrite);
    }

    /**
     * 功能：为copy 做递归使用。
     *
     * @author 张
     * @date 2019年06月24日
     * @param inputFile
     * @param outputFile
     * @param isOverWrite
     * @throws IOException
     */
    private static void copyPri(File inputFile, File outputFile,
                                boolean isOverWrite) throws IOException {
// 是个文件。
        if (inputFile.isFile()) {
            copySimpleFile(inputFile, outputFile, isOverWrite);
        } else {
// 文件夹
            if (!outputFile.exists()) {
                outputFile.mkdir();
            }
// 循环子文件夹
            for (File child : inputFile.listFiles()) {
                copy(child,
                        new File(outputFile.getPath() + "/" + child.getName()),
                        isOverWrite);
            }
        }
    }

    /**
     * 功能：copy单个文件
     *
     * @author 张
     * @date 2019年06月24日
     * @param inputFile
     *            源文件
     * @param outputFile
     *            目标文件
     * @param isOverWrite
     *            是否允许覆盖
     * @throws IOException
     */
    private static void copySimpleFile(File inputFile, File outputFile,
                                       boolean isOverWrite) throws IOException {
// 目标文件已经存在
        if (outputFile.exists()) {
            if (isOverWrite) {
                if (!outputFile.delete()) {
                    throw new RuntimeException(outputFile.getPath() + "无法覆盖！");
                }
            } else {
// 不允许覆盖
                return;
            }
        }
        InputStream in = new FileInputStream(inputFile);
        OutputStream out = new FileOutputStream(outputFile);
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        out.close();
    }

    /**
     * 功能：删除文件
     *
     * @author 张
     * @date 2019年06月24日
     * @param file
     *            文件
     */
    public static void delete(File file) {
        deleteFile(file);
    }

    /**
     * 功能：删除文件，内部递归使用
     *
     * @author 张
     * @date 2019年06月24日
     * @param file
     *            文件
     * @return boolean true 删除成功，false 删除失败。
     */
    private static void deleteFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
// 单文件
        if (!file.isDirectory()) {
            boolean delFlag = file.delete();
            if (!delFlag) {
                throw new RuntimeException(file.getPath() + "删除失败！");
            } else {
                return;
            }
        }
// 删除子目录
        for (File child : file.listFiles()) {
            deleteFile(child);
        }
// 删除自己
        file.delete();
    }

    /**
     * 从文件路径中抽取文件的扩展名, 例如. "mypath/myfile.txt" -> "txt". * @author 张
     *
     * @date 2019年06月24日
     * @param
     * @return 如果path为null，直接返回null。
     */
    public static String getFilenameExtension(String path) {
        if (path == null) {
            return null;
        }
        int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        if (extIndex == -1) {
            return null;
        }
        int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        if (folderIndex > extIndex) {
            return null;
        }
        return path.substring(extIndex + 1);
    }

    /**
     * 从文件路径中抽取文件名, 例如： "mypath/myfile.txt" -> "myfile.txt"。 * @author 张
     *
     * @date 2019年06月24日
     * @param path
     *            文件路径。
     * @return 抽取出来的文件名, 如果path为null，直接返回null。
     */
    public static String getFilename(String path) {
        if (path == null) {
            return null;
        }
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1)
                : path);
    }

    /**
     * 功能：保存文件。
     *
     * @author 张
     * @date 2019年06月24日
     * @param content
     *            字节
     * @param file
     *            保存到的文件
     * @throws IOException
     */
    public static void save(byte[] content, File file) throws IOException {
        if (file == null) {
            throw new RuntimeException("保存文件不能为空");
        }
        if (content == null) {
            throw new RuntimeException("文件流不能为空");
        }
        InputStream is = new ByteArrayInputStream(content);
        save(is, file);
    }

    /**
     * 功能：保存文件
     *
     * @author 张
     * @date 2019年06月24日
     * @param streamIn
     *            文件流
     * @param file
     *            保存到的文件
     * @throws IOException
     */
    public static void save(InputStream streamIn, File file) throws IOException {
        if (file == null) {
            throw new RuntimeException("保存文件不能为空");
        }
        if (streamIn == null) {
            throw new RuntimeException("文件流不能为空");
        }
// 输出流
        OutputStream streamOut = null;
// 文件夹不存在就创建。
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        streamOut = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
            streamOut.write(buffer, 0, bytesRead);
        }
        streamOut.close();
        streamIn.close();
    }


    /**
     * 将图片文件转换成base64字符串，参数为该图片的路径
     *
     * @param imageFile
     * @return java.lang.String
     */
    public String ImageToBase64(String imageFile) {
        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imageFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        if (data != null) {
            return "data:image/jpeg;base64," + encoder.encode(data);// 返回Base64编码过的字节数组字符串
        }
        return null;
    }
    /**
     * 将base64解码成图片并保存在传入的路径下
     * 第一个参数为base64 ，第二个参数为路径
     *
     * @param base64, imgFilePath
     * @return boolean
     */
    public static boolean Base64ToImage(String base64, String imgFilePath) {

        /**
         * 去掉前缀
         */
        base64 = StringUtils.substringAfter(base64,",");

        // 对字节数组字符串进行Base64解码并生成图片
        if (base64 == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }






    public static void main(String [] arr) throws IOException {
        File file = new File("/Users/wen/Desktop/fileauth.txt");
        FileInputStream fin=new FileInputStream(file);
        byte[] bs=new byte[1024];
        int count=0;
        String s = "";
        while((count=fin.read(bs))>0)
        {
           String str=new String(bs,0,count);	//反复定义新变量：每一次都 重新定义新变量，接收新读取的数据
            s = s+str;
        }
        fin.close();




        String path = ResourceUtils.getURL("classpath:").getPath();
        String newPath = path+"static/img/1.jpg";
        Base64ToImage(s,newPath);


    }


}