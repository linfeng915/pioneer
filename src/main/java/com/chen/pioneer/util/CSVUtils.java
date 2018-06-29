package com.chen.pioneer.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.FileChooserUI;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * @version 1.0.0
 * @类名称：CSVUtils
 * @类描述：csv文件读写
 * @创建时间：2017年1月10日 上午11:05:12
 */
public class CSVUtils {

    private static final Logger log = Logger.getLogger(CSVUtils.class);

    static String lineSprit = System.getProperty("line.separator");
    static List<String> hf = new ArrayList<String>();

    // 测试写入功能
    public static void main(String[] args) {
        readCsvFile(new File("E:/test/20180520.csv"), 1);
        try {
            FileUtils.writeLines(new File("E:/test/20180520.txt"), hf);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 读取csv文件 传参数 文件 表头 从第几行开始
    public static void readCsvFile(File file, Integer num) {
        BufferedReader br = null;
        CSVParser csvFileParser = null;
        String[] fileHeaders = {"CUST_ID"};
        // 创建CSVFormat（header mapping）
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(fileHeaders);
        StringBuilder res = new StringBuilder();
        try {
            // 初始化FileReader object
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));// 解决乱码问题
            // 初始化 CSVParser object
            csvFileParser = new CSVParser(br, csvFileFormat);
            // CSV文件records
            List<CSVRecord> csvRecords = csvFileParser.getRecords();
            for (int i = num; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                String str = "update  mer_usr_map t set t.invest_flag='Y',t.borrow_flag='N' where t.mer_cust_id = '6000060023631929' and t.cust_id = '" + record.get("CUST_ID") + "' and t.guar_type != 'Y' and t.borrow_flag = 'Y';";
                res.append("'").append(record.get("CUST_ID")).append("',");
                hf.add(str);
            }
            System.out.println("select * from mer_usr_map t where t.mer_cust_id = '6000060000887839' and t.cust_id in (" + res + ");");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("csv文件读取异常");
        } finally {
            try {
                br.close();
                csvFileParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}