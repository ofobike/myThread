package com.nbcb.controller;

import com.nbcb.util.QrCodeUtil;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class QrController {
    public static void main(String[] args) throws Exception {
        StringBuffer buffer = new StringBuffer();
        String imgFile = "C:\\Users\\ysj\\Desktop\\0.jpg";
        ByteArrayOutputStream outputstream = null;
        int index = imgFile.lastIndexOf(".");
        String args1 = "jpg";
        if (index > -1 && index < (imgFile.length() - 1)) {
            args1 = imgFile.substring(index + 1);
        } else {
            throw new Exception("文件后缀有误url=" + imgFile);
        }
        File file = new File(imgFile);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (Exception e) {
            throw new Exception("读取图片url[" + imgFile + "]有误！");

        }
        // 开始对图片进行压缩
        outputstream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, args1, outputstream);
        Base64 base64 = new Base64();
        String encode = base64.encodeAsString(outputstream.toByteArray());
        buffer.append("data:image/png;base64,").append(encode);
        String string = buffer.toString();
        System.out.println(string);
        System.out.println("-------------------------------");
        /*QrCodeUtil.encode("https://www.cnblogs.com/dreamowneryong/p/4773201.html",
                imgFile,"C:\\Users\\ysj\\Desktop",true);*/
        System.out.println("-------------利用流生成-------------");
        InputStream inputStream = new FileInputStream(file);
//        QrCodeUtil.encodeByInputStream("https://www.cnblogs.com/dreamowneryong/p/4773201.html",
//                inputStream,"C:\\Users\\ysj\\Desktop",true);
        String substring = string.substring(22);
        /*BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(substring);*/
        byte[] decode = base64.decode(substring);
        InputStream byteArrayInputStream = new ByteArrayInputStream(decode);
        QrCodeUtil.encodeByInputStream("https://www.cnblogs.com/dreamowneryong/p/4773201.html",
                byteArrayInputStream,"C:\\Users\\ysj\\Desktop",true);



        String str="wqeqwewd\r\ndas\rds\nff "+"dsfsd";
        //str = str.replaceAll("\r\n|\r|\n", "");
        System.out.println(str);
    }
}