package com.ccsu.bookshop.controller;

import com.ccsu.bookshop.bean.HostHolder;
import com.ccsu.bookshop.bean.User;
import com.ccsu.bookshop.service.LoginTicketService;
import com.ccsu.bookshop.service.UserService;
import com.ccsu.bookshop.utils.Md5;
import com.ccsu.bookshop.utils.SavePic;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginTicketService loginTicketService;

    @Autowired
    private HostHolder hostHolder;
    User tmp=null;

    @RequestMapping("/queryUserById")
    @ResponseBody
    public User queryUserById(@RequestParam("userId") Integer userId) {
        return userService.selectByUserId(userId);
    }

    @RequestMapping("/queryUserByUsername")
    @ResponseBody
    public User queryUserByUsername() {
        if (hostHolder.getUser()==null){
            return null;
        }
        //保存用户到本地线程    随时可以调用
        tmp=hostHolder.getUser();
        return hostHolder.getUser() ;
    }


    @RequestMapping("/queryUserByUname")
    @ResponseBody
    public boolean queryUserByUname(@RequestParam String username) {
        if (userService.selectByUsername(username)==null)
        {
            return true;
        }
        else {
            return false;
        }
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public int insertUser(User user) {
        user.setPassword(Md5.generate(user.getPassword()));
        return userService.insertUser(user);
    }

    @RequestMapping("/getImages")
    public void getUserLogo(HttpServletRequest request,
                            HttpServletResponse response, String path) throws IOException {
        String imgUrl = path;
        System.out.println("getImages");
        System.out.println("path:" + path);
        URL url = new URL(imgUrl);
        URLConnection conn = url.openConnection();
        InputStream inStream = conn.getInputStream();
        response.setContentType("image/jpg"); //设置返回的文件类型
        response.setHeader("Access-Control-Allow-Origin", "*");//设置该图片允许跨域访问
        IOUtils.copy(inStream, response.getOutputStream());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletResponse response) {

//        System.out.println(rememberMe);  //true  $$ false
        Map<String, Object> map = new HashMap<>();
        User user = userService.selectByUsername(username);
        if (user == null) {
            return "no";//用户不存在
        }
        System.out.println(password);
        System.out.println(user.getPassword());
        if (Md5.verify(password, user.getPassword()) == false) {
            return "erorr";//密码错误
        }


        System.out.println("------------------------------------------------------");
        System.out.println(user.getId());
        String ticket = loginTicketService.addLoginTicket(user.getId());

        map.put("ticket", ticket);
        Cookie cookie = new Cookie("ticket", (String) map.get("ticket"));
        cookie.setPath("/"); //全站有效；
        cookie.setMaxAge(3600 * 24 * 5); //五天有效期！
        response.addCookie(cookie);

        return "ok";//登陆成功

    }
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(@CookieValue("ticket") String ticket){
        loginTicketService.updateStatus(ticket,1);  //过期
        return "ok";
    }


    @RequestMapping("updae/up_image_url")
    public String add_image_url(
            @RequestParam("file") MultipartFile file,
            HttpServletResponse response, HttpServletRequest request) throws Exception {
        //JSON格式
        if(!file.isEmpty()){
//            String webPath=request.getServletContext().getRealPath("");
//            String filePath= "/static/images";
//            //把文件名子换成（时间搓.png）
//            // String imageName="houtai_logo."+file.getOriginalFilename().split("\\.")[1];
//
//            String imageName= DateUtil.formatDate(new Date(), "yyyy-MM-dd-hh-mm-ss-SSS")+".jpg";
//            file.transferTo(new File(webPath+filePath+imageName));
            String URL= SavePic.savePic(file.getInputStream(), file.getOriginalFilename());
            String url = URL.replaceAll("\\\\","/");
            String urlImage="../../static/userIcon/"+ file.getOriginalFilename();
            System.out.println("========");
            System.out.println(tmp);

            userService.updateImage(urlImage,tmp.getId());
        }
        return null;
    }

}
