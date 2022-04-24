package com.huige.store.util;

import com.huige.store.controller.ex.*;
import com.huige.store.enums.AppHttpCodeEnum;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author huige
 * Create on 2022/4/12 21:35
 */
public class FileUploadUtil {

    /**
     * 限制文件上传文件的类型
     */
    private static final Integer AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    private static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image/jpg");
    }

    public static String upload(HttpSession session, MultipartFile file) {
        String path = System.getProperty("user.dir") + "/src/main/resources/files/";
        System.out.println(path);

        if (file.isEmpty()) {
            throw new FileEmptyException(AppHttpCodeEnum.FILE_EMPTY_MSG);
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException(AppHttpCodeEnum.FILE_SIZE_MSG);
        }
        // 判断文件的类型是否是我们规定的后缀类型
        String contentType = file.getContentType();
        // 如果集合包含某个元素  则返回true
        if (!AVATAR_TYPE.contains(contentType)) {
            throw new FileTypeException(AppHttpCodeEnum.FILE_TYPE_MSG);
        }
        // 上传的文件        ../upload/文件.png
        // File对象指向这个路径 File是否存在

        // 获取到这个文件名称，UUID工具类生成一个行的字符串作为文件名
        String originalFilename = file.getOriginalFilename();
        // 例如:avatar01.png
        // System.out.println(originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String fileName = UUID.randomUUID().toString().toUpperCase() + suffix;
        // 创建dest文件  空文件

        File dest = new File(fileName);
        // 将参数file中的数据写入到空文件中
        try {
            // 将file文件中的数据写入到dest中
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadIOException(AppHttpCodeEnum.FILE_UPLOAD_IO_MSG);
        } catch (FileStateException e) {
            throw new FileStateException(AppHttpCodeEnum.FILE_STATE_MSG);
        }
        // String replace = path.replace('', '');
        System.out.println(path + "\\upload\\" + fileName);
        return "http://localhost:8080" + path + "\\upload\\" + fileName;
    }
}
