package com.skrtu.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Optional;

public class FileCopy {

    /***
     * @Author dcx
     * @Description //NIO方式复制文件操作
     * @Date 12:30 2020/6/3
     * @Param [from, to, name]
     * @return void
     **/
    public static void fileCopy(String from, String to, String name) throws IOException {
        FileChannel fis = null;
        FileChannel fos = null;
        try {
            File file = new File(to);
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
                boolean newFile = file.createNewFile();
                System.out.println("文件创建" + mkdirs + "-" + newFile);
            }
            String newFile = to + '/' + name;
            fis = new FileInputStream(from).getChannel();
            fos = new FileOutputStream(newFile).getChannel();
            fis.transferTo(0, fis.size(), fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Optional.ofNullable(fis).isPresent()) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (Optional.ofNullable(fos).isPresent()) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String from = "D:\\timg.jpg";
        String to = "D:\\var\\aa";
        String name = "22.jpg";
        fileCopy(from, to, name);
    }
}
