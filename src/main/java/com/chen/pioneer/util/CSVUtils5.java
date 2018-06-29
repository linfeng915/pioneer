package com.chen.pioneer.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.0
 * @类名称：CSVUtils
 * @类描述：csv文件读写
 * @创建时间：2017年1月10日 上午11:05:12
 */
public class CSVUtils5 {

    private static final Logger log = Logger.getLogger(CSVUtils5.class);

    static String lineSprit = System.getProperty("line.separator");
    static List<String> hf = new ArrayList<String>();
    // 测试写入功能
    public static void main(String[] args) {
        readCsvFile(new File("E:/test/dc.txt"), 1);
        try {
            FileUtils.writeLines(new File("E:/test/dc.sql"), hf);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 读取csv文件 传参数 文件 表头 从第几行开始
    public static void readCsvFile(File file, Integer num) {

        StringBuilder res = new StringBuilder();
        try {
            // 初始化FileReader object

           List<String> list  = FileUtils.readLines(file, "utf-8");// 解决乱码问题

            for (int i = num; i < list.size(); i++) {
                String str = "update dispatcher_send_msg t set  t.sendstat='U' where t.senddate = '20180610'  and t.sysid = '14' and t.merid = '6000060004348157' and t.ordid  = '"+list.get(i)+"';"+lineSprit+"commit;";
                hf.add(str);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("csv文件读取异常");
        } finally {

        }
    }


}