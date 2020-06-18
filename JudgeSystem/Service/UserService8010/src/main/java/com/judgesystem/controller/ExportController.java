package com.judgesystem.controller;

import com.judgesystem.entity.RespBean;
import com.judgesystem.entity.User;
import com.judgesystem.service.ExportService;
import com.judgesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/excl")
public class ExportController {

    @Autowired
    UserService userService;
    @Autowired
    ExportService exportService;
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() {
        //List<User> list = (List<User>) UserService.getEmployeeByPage(null, null, new User(),null).getData();
        return exportService.user2Excel(userService.findAllUser());
    }

    @PostMapping("/import")
    public RespBean importData(@RequestParam("file")MultipartFile file) throws IOException {
        List<User> list = exportService.excel2User(file);
        //System.out.println(list);
        if (userService.addUser(list) == list.size()) {
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }
}
