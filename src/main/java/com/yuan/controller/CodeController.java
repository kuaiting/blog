package com.yuan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
public class CodeController {
    // 常用字符集
    private char[] data = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    //随机验证码胡Session中的名称
    private String code = "code";

    public static final int WIDTH = 120;
    public static final int HEIGHT = 30;



    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);// 创建缓存

        Graphics graphics = image.getGraphics();// 获得画布
        setBackGround(graphics);// 设置背影色
        setBorder(graphics);// 设置边框
        drawRandomLine(graphics);// 画干扰线
        String random = drawRandomNum((Graphics2D) graphics);// 写随机数
        request.getSession().setAttribute(code, random);//核心代码：将随机字符存在session中
        response.setContentType("image/jpeg");// 将图形写给浏览器
        // 发头控制浏览器不要缓存
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

        ImageIO.write(image, "jpg", response.getOutputStream());// 将图片写给浏览器
    }

    // 设置背景色
    private void setBackGround(Graphics g) {
        g.setColor(Color.WHITE);// 设置颜色
        g.fillRect(0, 0, WIDTH, HEIGHT);// 填充区域

    }


    //设置边框
    private void setBorder(Graphics g) {
        g.setColor(Color.BLUE);// 设置边框颜色
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);// 边框区域
    }


    // 画随机线条
    private void drawRandomLine(Graphics g) {
        g.setColor(Color.GREEN);// 设置颜色
        for (int i = 0; i < 5; i++) {// 设置线条个数并画线
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }


    // 画随机汉字
    private String drawRandomNum(Graphics2D g) {
        StringBuffer sb = new StringBuffer();

        g.setColor(Color.RED);// 设置颜色

        g.setFont(new Font("宋体", Font.BOLD, 24));// 设置字体
        int x = 5;// 控制字数

        for (int i = 0; i < 4; i++) {
            int degree = new Random().nextInt() % 30;// 设置字体旋转角度

            String ch = data[new Random().nextInt(data.length)] + "";// 截取汉字
            sb.append(ch);

            g.rotate(degree * Math.PI / 180, x, 20);// 正向角度
            g.drawString(ch, x, 20);
            // 反向角度
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 30;
        }
        return sb.toString();
    }
    @RequestMapping("/checkCode")
    @ResponseBody
    public String checkCode(HttpServletRequest request){
        String code0 = request.getParameter("code0");
        String code = request.getSession().getAttribute("code").toString();
        String msg="";
        if(code0.equals(code)){
            msg="yes";
        }else {
            msg="no";
        }
        return msg;
    }
}
