package com.judgesystem.service;

import org.springframework.stereotype.Service;

import java.io.File;
@Service
public class FileUtil {
    public final static String IMG_PATH_PREFIX = "static/upload/imgs";
    public final static String aliyunFilePath="/dev/vda/www/upAndDownFile";

    public static File getImgDirFile(){

        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("E://resources/" + IMG_PATH_PREFIX);

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

}
