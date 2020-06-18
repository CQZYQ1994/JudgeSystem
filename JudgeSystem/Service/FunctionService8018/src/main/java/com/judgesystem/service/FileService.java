package com.judgesystem.service;

import com.judgesystem.dao.FileEntityDao;
import com.judgesystem.entity.FileEntity;
import com.judgesystem.entity.FileException;
import com.judgesystem.entity.UploadFileResponse;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private FileEntityDao fileEntityDao;

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd/hh:mm");

    public UploadFileResponse uploadFile(MultipartFile file, HttpServletRequest request){
        if (file.isEmpty()) {
            String msg="上传失败，请选择文件";
            throw new FileException(file.getName()+msg);
        }
        String format=sdf.format(new Date());
        // 拿到文件名
        String oldName = file.getOriginalFilename();
        String newName= UUID.randomUUID().toString()+"-"+oldName;
        // 存放上传图片的文件夹
        File fileDir = fileUtil.getImgDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        //System.out.println(fileDir.getAbsolutePath());
        String fileURL=null;
        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + oldName);
            fileURL=newFile.getAbsolutePath();
           // System.out.println(newFile.getAbsolutePath());
            // 上传图片到 -》 “绝对路径”
            file.transferTo(newFile);
            FileEntity fileEntity=new FileEntity();
            fileEntity.setUploadtime(format);
            fileEntity.setFileUrl(fileURL);
            fileEntity.setFileName(oldName);
            fileEntityDao.saveUploadFile(fileEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new UploadFileResponse(oldName,fileURL,file.getContentType(),file.getSize());
    }
    public String uploadFiles(MultipartFile[] file, HttpServletRequest request){
        if (file.length==0) {
            String msg="上传失败，请选择文件";
            throw new FileException(msg);
        }
        try {
            for (int i=0;i<file.length;i++){
                if (file[i]!=null){
                    uploadFile(file[i],request);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            return "upload fail";
        }
        return "success";
    }
    public Object downloadFile(String fileName, final HttpServletResponse response, final HttpServletRequest request){
        OutputStream os = null;
        InputStream is= null;
        String rootPath="E://resources/static/upload/imgs/";
        try {
        // 取得输出流
            os = response.getOutputStream();
          // 清空输出流
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            //response.setContentLength((int) file.length());
            //response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"), "iso-8859-1"));
          //读取流
            File f = new File(rootPath+fileName);
            is = new FileInputStream(f);
            if (is == null) {
                String msg="下载附件失败，请检查文件“" + fileName + "”是否存在";
                return msg;
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            String msg="下载附件失败,error:"+e.getMessage();
            return msg;
        }
     //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                //logger.error(ExceptionUtils.getFullStackTrace(e));
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
             //  logger.error(ExceptionUtils.getFullStackTrace(e));
            }
        }
        return null;
    }
    public String fileDownLoad(HttpServletResponse response,String fileName){
        String rootPath="E://resources/static/upload/imgs/";
        String aliyunFilePath="/dev/vda/www/upAndDownFile";
        File file = new File(rootPath + fileName);
        System.out.println(file.getAbsolutePath());
        if(!file.exists()){
            return "下载文件不存在";
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);


        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buff = new byte[10240];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
           // log.error("{}",e);
            return "下载失败";
        }
        return "下载成功";
    }
    public List<FileEntity> findAllFile(){
        return fileEntityDao.findAll();
    }
    public FileEntity findFileById(int id){
        return fileEntityDao.findFileById(id);
    }
    /*   /private final Path fileStorageLocation; // 文件在本地存储的地址
    @Autowired
    public FileService(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }




    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }*/

}
