package com.huige.store.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.huige.store.controller.ex.FileUploadIOException;
import com.huige.store.enums.AppHttpCodeEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author huige
 * Create on 2022/4/12 23:30
 */
public class FileUpload {

    private static String port = "8080";

    private static final String ip = "localhost";

    public static String upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        // 文件夹的绝对路径
        // 定义文件的唯一标识(前缀)
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/store/src/main/resources/files/" + flag + "_" + originalFilename;
        String path = flag + "_" + originalFilename;
        // 把文件写入到上传的路径
        try {
            FileUtil.writeBytes(file.getBytes(), rootFilePath);
        } catch (IOException e) {
            throw new FileUploadIOException(AppHttpCodeEnum.FILE_UPLOAD_IO_MSG);
        }
        return "http://" + ip + ":" + port + "/files/" + flag;
    }

    public JSON editorUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        // 文件夹的绝对路径
        // 定义文件的唯一标识(前缀)
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/store/src/main/resources/files/" + flag + "_" + originalFilename;
        // 把文件写入到上传的路径
        FileUtil.writeBytes(file.getBytes(), rootFilePath);
        String url = ip + ":" + port + "/files/" + flag;
        JSONObject json = new JSONObject();
        json.set("errno", 0);
        JSONArray arr = new JSONArray();
        JSONObject data = new JSONObject();
        arr.add(data);
        data.set("url", url);
        json.set("data", arr);
        return json;
    }

    public void getFile(String flag, HttpServletResponse response) {
        OutputStream os;
        // 定义文件上传的根路径
        String basePath = System.getProperty("user.dir") + "/store/src/main/resources/files/";
        // 获取所有的文件名称
        List<String> fileNames = FileUtil.listFileNames(basePath);
        // 找到跟参数一致的文件
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                // 通过文件的路径 读取文件的字节流
                byte[] bytes = FileUtil.readBytes(basePath + fileName);
                // 通过输出流 返回文件
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }

}
