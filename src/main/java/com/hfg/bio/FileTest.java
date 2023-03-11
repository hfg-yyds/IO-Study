package com.hfg.bio;

import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

/**
 * <p>
 * File类的常见使用
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-03-08
 */
public class FileTest {

    @Test
    public void test1() {
        File file = new File("E:\\testfile");
        List<File> list = new ArrayList<>();
        List<File> searchFile = searchFile(file, list, "AA");
        System.out.println(searchFile.size());
    }

    /**
     * 找某一个文件价是否有某个文件
     */
    public static Boolean havingAVI(File file) {
        for (File listFile : Objects.requireNonNull(file.listFiles())) {
            if (listFile.isFile() && listFile.getName().endsWith(".avi")) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 深度遍历
     * 找到当前目录下文件 名字有name的文件
     * 套路：
     * 1、进入文件夹,获取文件集合
     * 2、便利数组(需要非空判断)
     * 3.判断 (做业务逻辑处理)
     * 4.判断(递归)
     */
    public static List<File> searchFile(File file, List<File> list, String name) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isFile()) {
                    if (file1.getName().contains(name)) {
                        list.add(file);
                    }
                } else {
                    searchFile(file1, list, name);
                }
            }
        }
        return list;
    }

    /**
     * 深度遍历
     */
    public static List<File> searchBFSFile(File file, List<File> list, String name) {
        return list;
    }

    /**
     * 删除文件夹  由于file删除文件夹只能删除没有文件的目录
     */
    public static void removeFile(String dirName) {
        File file = new File(dirName);
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isFile()) {
                    file1.delete();
                } else {
                    removeFile(file1.getName());
                }
            }
        }
        //最后删除自己
        file.delete();
    }
    public static long countFileBytes(File file) {
        long length =0L;
        File[] files = file.listFiles();
        if (files!=null) {
            for (File file1 : files) {
                if (file1.isFile()) {
                    length = length + file1.length();
                }else {
                    length = length + countFileBytes(file);
                }
            }
        }
        return length;
    }

    /**
     * 统计文件的个数
     */
    public static Map<String,Integer> countFile(File file,Map<String,Integer> map) {
        File[] files = file.listFiles();
        if (files!=null) {
            for (File file1 : files) {
                if (file1.isFile()) {
                    String suffix = FileUtil.getSuffix(file1);
                    map.put(suffix,map.getOrDefault(suffix,0)+1);
                }else {
                    countFile(file1,map);
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = countFile(new File("E:\\testfile"), new HashMap<>());
        System.out.println(map);
    }

}
