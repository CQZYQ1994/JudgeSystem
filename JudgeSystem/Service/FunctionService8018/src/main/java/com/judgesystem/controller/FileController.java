package com.judgesystem.controller;
import com.judgesystem.entity.FileEntity;
import com.judgesystem.entity.UploadFileResponse;
import com.judgesystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/file")
public class FileController {
   // private static final Logger logger= LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileService fileService;


  /*  @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile uploadFile,HttpServletRequest req){
        String realPath=req.getSession().getServletContext().getRealPath("/uploadFile/");
        String format=sdf.format(new Date());
        File folder=new File(realPath+format);
        if (!folder.isDirectory()){
            folder.mkdirs();
        }
        String oldName=uploadFile.getOriginalFilename();
        String newName= UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());
        try {
            uploadFile.transferTo(new File(folder,newName));
            String filePath=req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/uploadFile/"+format+newName;
            return filePath;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }*/

   // @ResponseBody
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        return fileService.uploadFile(file,request);
    }
    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("file") MultipartFile[] files,HttpServletRequest request){
        return fileService.uploadFiles(files,request);
    }
    @RequestMapping("/download/{fileName}")
    public String  downloadFile(@PathVariable("fileName") String fileName, final HttpServletResponse response, final HttpServletRequest request){
        //return fileService.downloadFile(fileName,response,request);
        return fileService.fileDownLoad(response,fileName);
    }
    @GetMapping("/findAllFile")
    public List<FileEntity> findAllFile(){
        return fileService.findAllFile();
    }
    @GetMapping("/findById/{id}")
    public FileEntity findFileById(@PathVariable("id") int id){
        return fileService.findFileById(id);
    }

/*    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file){
        String fileName=fileService.storeFile(file);
        String fileDownloadUri= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponse(fileName,fileDownloadUri,file.getContentType(),file.getSize());
    }
    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }*/



}
