package com.huige.store.controller;

import com.huige.store.util.FileUpload;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author huige
 * Create on 2022/4/13 0:33
 */
@RestController
@RequestMapping("/files")
public class FileController {

    // TODO 调用fileUpload方法  实现文件的下载
    @GetMapping("/{flag}")
    public void getFile(@PathVariable("flag") String flag, HttpServletResponse response) {
        FileUpload fu = new FileUpload();
        fu.getFile(flag, response);
    }

}
