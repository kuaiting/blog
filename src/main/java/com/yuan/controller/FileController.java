package com.yuan.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.bean.User;
import com.yuan.services.UserSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    UserSevices userService;
    @Autowired
    HttpSession session;

    @PostMapping("/uploaduserimg")
    public String fileUpload(@RequestParam("uploadFile") MultipartFile file, Model model) throws IOException {
        if (file.isEmpty()) {
            model.addAttribute("msg", "未获取文件!");
            return "myimg";
        }

        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "--->" + size);
        String path = "F:/Workspaces/blog/src/main/resources/static/admin/img/";
        File savepath = new File(path + fileName);
        //判断文件父目录是否存在
        if (!savepath.getParentFile().exists()) {
            savepath.getParentFile().mkdirs();

        }
        if (!savepath.getParentFile().exists()) {
            savepath.getParentFile().createNewFile();

        }
        try {
            //保存文件
            file.transferTo(savepath);

            Object myinfo = session.getAttribute("user");
            User user1= (User) myinfo;
            Integer id = user1.getId();
            User user = new User();
            user.setImg(fileName);
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            //更新条件
            wrapper.eq("id", id);

            Integer res = userService.updateUserWrapper(user, wrapper);
            if (res != null) {
                model.addAttribute("msg", "上传成功!");
                model.addAttribute("fileName", fileName);
                return "myimg";
            } else {
                model.addAttribute("msg", "上传失败!");
                return "myimg";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "上传失败!");
            return "myimg";
        }
    }
}
