package utils;

import java.util.UUID;

public class StringUtils {
    public static String getRandomImgName(String fileName) {

        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index);

        //检验文件
        if(".jpg".equals(suffix) || ".jpeg".equals(suffix) || ".png".equals(suffix)){

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            return uuid + suffix;
        }else{
            throw new IllegalArgumentException();
        }
    }
}
