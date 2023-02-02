package com.kuark.tool.advance.advance20200723.image;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author rock
 * @detail 把图片转成由各种字符组成的轮廓字符
 * @date 2020/8/10 10:16
 */
public class Image2ASCII {
//    static String base = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\\\"^`'.";
    static String base = "@#&$%*o!;.";//小帅丶使用这个字符
    /** 图片类型  */
    private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;



    //main方法调用
    public static void main(String[] args) throws Exception {
        load("E:\\materials\\renwu4.jpg", "E:\\materials\\renwu4.txt");//静态图片转字符保存为txt文件

//        String byteBase64 = image2ByteBase64(ImageIO.read(new File("E:\\materials\\renwu2.jpg")));
//        System.out.println("图片的base64串："+byteBase64);
    }
    /**
     * 图片转字符
     * @param imagePath 图片路径
     * @param txtPath 文本存放路径
     * @throws IOException
     */
    public static void load(String imagePath, String txtPath)
            throws IOException {
        //截取指定大小的图片
        BufferedImage image = ImageHelper.resize(ImageIO.read(new File(imagePath)),150,150);
        load(image, txtPath);
    }
    /**
     * 图片转字符
     * @param bi BufferedImage图片
     * @param txtPath 文本存放路径
     * @throws IOException
     */
    public static void load(BufferedImage bi, String txtPath){
        try {
            int width = bi.getWidth();
            int height = bi.getHeight();
            boolean flag = false;
            StringBuilder result = new StringBuilder("");
            for (int i = 0; i < height; i += 2) {
                for (int j = 0; j < width; j++) {
                    int pixel = bi.getRGB(j, i); // 下面三行代码将一个数字转换为RGB数字
                    int red = (pixel & 0xff0000) >> 16;
                    int green = (pixel & 0xff00) >> 8;
                    int blue = (pixel & 0xff);
                    float gray = 0.299f * red + 0.578f * green + 0.114f * blue;
                    int index = Math.round(gray * (base.length() + 1) / 255);
                    result.append(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
                }
                result.append("\r\n");
            }
            flag = writeTxtFile(result.toString(),txtPath);//保存字符到文本文件
            System.out.println(flag?"image 2 txt success":"image 2 txt fail");
        } catch (Exception e) {
            System.out.println("图片转字符异常"+e.getMessage());
        }
    }
    /**
     * 字符保存到txt文件中
     * @param imageStr 字符
     * @param txtPath  txt文件
     * @return boolean
     * @throws Exception
     */
    private static boolean writeTxtFile(String imageStr, String txtPath) throws Exception{
        // 先读取原有文件内容，然后进行写入操作
        String filein = imageStr;
        String temp = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
            File file = new File(txtPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();
            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
            }
            buf.append(filein);
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
        } catch (IOException e) {
            System.out.println("文件保存失败"+e.getMessage());
            return false;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return true;
    }


    /**
     * 图片转字符再保存为图片的base64
     * @param bi 原图
     * @return String
     */
    public static String image2ByteBase64(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        int speed = 7;
        BufferedImage bufferedImage = new BufferedImage(width,height,IMAGE_TYPE);
        // 获取图像上下文
        Graphics g = createGraphics(bufferedImage, width, height, speed);
        // 图片中文本行高
        final int Y_LINEHEIGHT = speed;
        int lineNum = 1;
        for (int i = miny; i < height; i += speed) {
            for (int j = minx; j < width; j += speed) {
                int pixel = bi.getRGB(j, i); // 下面三行代码将一个数字转换为RGB数字
                int red = (pixel & 0xff0000) >> 16;
                int green = (pixel & 0xff00) >> 8;
                int blue = (pixel & 0xff);
                float gray = 0.299f * red + 0.578f * green + 0.114f * blue;
                int index = Math.round(gray * (base.length() + 1) / 255);
                String c = index >= base.length() ? " " : String.valueOf(base.charAt(index));
                g.drawString(c, j, i);
            }
            lineNum++;
        }
        g.dispose();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage,"jpg",out);
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String base64 =base64Encoder.encode(out.toByteArray());
            return base64;
        } catch (IOException e) {
            System.out.println("ImageIO.write异常" + e.getMessage());
        }finally{
            if(null!=out){
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("out.close()异常" + e.getMessage());
                }
            }
        }
        return null;
    }
    /**
     * 画板默认一些参数设置
     * @param image 图片
     * @param width 图片宽
     * @param height 图片高
     * @param size 字体大小
     * @return
     */
    private static Graphics createGraphics(BufferedImage image, int width,
                                           int height, int size) {
        Graphics g = image.createGraphics();
        g.setColor(null); // 设置背景色
        g.fillRect(0, 0, width, height);// 绘制背景
        g.setColor(Color.BLACK); // 设置前景色
        g.setFont(new Font("微软雅黑", Font.PLAIN, size)); // 设置字体
        return g;
    }
}
