package com.hfg.bio;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>
 * 测试字节流
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-03-08
 */
public class TestOutStream {

    /**
     * 文件流写出
     *
     * @throws IOException io
     */
    @Test
    public void testFileOutputStream() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\testfile\\1.txt", true);
        for (int i = 0; i < 10; i++) {
            String string = RandomUtil.randomString(10) + "含" + "\r\n";
            fileOutputStream.write(string.getBytes("GBK"));
        }
        fileOutputStream.close();
    }

    /**
     * 文件流写出
     *
     * @throws IOException io
     */
    @Test
    public void testFileInputStream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\testfile\\1.txt");
        int read;
        while ((read = fileInputStream.read()) != -1) {
            System.out.println(read);
        }
        fileInputStream.close();
    }

    @Test
    public void testCopy() throws IOException {
        //效率极其缓慢
        FileInputStream fileInputStream = new FileInputStream("E:\\testfile\\1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\testfile\\222.txt");
        int read;
        while ((read = fileInputStream.read()) != -1) {
            fileOutputStream.write(read);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    @Test
    public void testCopy2() {
        try (FileInputStream fileInputStream = new FileInputStream("E:\\testfile\\1.txt");
             FileOutputStream fileOutputStream = new FileOutputStream("E:\\testfile\\222.txt")) {
            int len;
            byte[] bytes = new byte[1024 * 1024 * 5];
            while ((len = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
