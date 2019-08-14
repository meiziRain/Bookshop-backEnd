package com.ccsu.bookshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.ccsu.bookshop.utils.ResponseUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/BookCommentPicture")
public class UploadHandler {
    @RequestMapping("/upload")
    public String upload(@RequestParam(value = "name", required = false) String name
            , @RequestParam("picture") MultipartFile picture, HttpServletResponse response, HttpServletRequest request) {

        JSONObject result = new JSONObject();

        //获取文件在服务器的储存位置
        String path = request.getSession().getServletContext().getRealPath("/upload");
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String nameStr = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + nameStr + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);


        String str= filePath + File.separator + fileName;
        str = str.replaceAll("\\\\", "/");
        System.out.println("转换为正斜杠后："+str);
        result.put("success", true);
        result.put("path",str);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回4
            try {
                ResponseUtil.write(response, result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/showImg")
    @ResponseBody
//
//    需要读取流.
//    操作如下，即在img的src路径内写入路径请求并指定能找到图片地址的参数，我传入的参数直接是图片的地址，请求到后台，
//    使用文件字节流按照图片地址读取图片，使用response输出，这样页面就可以展示本地的图片了。
    public void showImg(HttpServletRequest request, HttpServletResponse response, String pathName) throws IOException {
        System.out.println("pathName:"+pathName);
        File imgFile = new File(pathName);
        FileInputStream fin = null;
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            fin = new FileInputStream(imgFile);
            byte[] arr = new byte[1024 * 10];
            int n;
            while ((n = fin.read(arr)) != -1) {
                output.write(arr, 0, n);
            }
            output.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

