package com.demo.qcby.file.controller;

import com.demo.qcby.anno.PreAuth;
import com.demo.qcby.common.constant.Constant;
import com.demo.qcby.common.web.ResultJson;
import com.demo.qcby.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname FileController
 * @Description 文件管理类
 * @Date 2021/9/13 9:52
 * @Created by thx
 */
@RestController
@RequestMapping("file")
public class FileController {
    /**
     *  上传文件
     * @param file
     * @return
     */
    @PreAuth("file:upload")
    @RequestMapping("upload")
    public ResultJson upload(@RequestParam("file") MultipartFile file){
        if(file == null){
            return ResultJson.failure(Constant.FAILURE_MESSAGE);
        }
        Map<String,String> r = new HashMap<>();
        // 调用上传方法返回可访问的路径
        String filePath = FileUtil.uploadFile(file);
        // 上传后的路径
        r.put("filePath",filePath);
        // 文件原名
        r.put("oldName",file.getOriginalFilename());
        // 文件大小
        r.put("size",file.getSize()+"");
        return ResultJson.success(r);
    }

    /**
     *  根据路径下载文件
     * @param filePath
     * @return
     * @throws IOException
     */
    @PreAuth("file:download")
    @RequestMapping("downloadByFilePath")
    public ResponseEntity<byte[]> download(String filePath) throws IOException {
        if(StringUtils.isEmpty(filePath)){
            throw new RuntimeException("路径不可为空！");
        }
        /**
         * 获取磁盘路径
         */
        System.out.println("sss");
        // 获取到文件相对路径
        // /filePrefix/20210626/20210626100834650_RFHASX.jpg => 20210626/20210626100834650_RFHASX.jpg
        String path = filePath.substring(filePath.indexOf(FileUtil.filePrefix)+FileUtil.filePrefix.length()-1);
        // 磁盘根路径 + 相对路径  获取绝对路径
        // D:/qcby/20210626/20210626100834650_RFHASX.jpg
        String localPath = FileUtil.uploadLocalPath+path;
        File file = new File(localPath);
        if(!file.exists()){
            throw new RuntimeException("文件不存在！");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",
                new String(file.getName().getBytes(StandardCharsets.UTF_8), "iso-8859-1"));
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 获取文件的字节数组 - 需要使用commons-io依赖包
        byte[] content = FileUtils.readFileToByteArray(file);
        // 返回下载的二进制内容
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
}
