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
public class CSVUtils4 {

    private static final Logger log = Logger.getLogger(CSVUtils4.class);

    static String lineSprit = System.getProperty("line.separator");
    static List<String> hf = new ArrayList<String>();

    // 测试写入功能
    public static void main(String[] args) {
        readCsvFile(new File("E:/test/gl_mp.csv"), 1);
        try {
            FileUtils.writeLines(new File("E:/test/20180614.txt"), hf);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 读取csv文件 传参数 文件 表头 从第几行开始
    public static void readCsvFile(File file, Integer num) {
        BufferedReader br = null;
        CSVParser csvFileParser = null;
        String[] fileHeaders = {"name", "mp", "ls", "mps", "cust", "dz", "tj", "mark"};
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
                if (record.get("cust").length() > 16) {
                    StringBuilder str = new StringBuilder("update usr_oper_info t set  t.usr_mp='").append(record.get("mp").trim().substring(0, 7)).append("****',t.usr_mp_enc='").append(record.get("mps").trim())
                            .append("',t.usr_tel='").append(record.get("mp").trim()).append("'   where  t.oper_id='000001' and t.cust_id='").append(record.get("cust").trim()).append("';\n");
                    res.append("'").append(record.get("cust").trim()).append("',");
                    hf.add(str.toString());
                }
            }
            System.out.println("select t.usr_mp,t.usr_mp_enc,t.usr_tel,t.cust_id,t.oper_id from ubsdb.usr_oper_info t where t.oper_id='000001' and t.cust_id in ( " + res + ");");
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

    // 读取csv文件 传参数 文件 表头 从第几行开始
    public static void readCsvFile2(File file, Integer num) {
        BufferedReader br = null;
        CSVParser csvFileParser = null;
        String[] fileHeaders = {"name", "mp", "ls", "mps", "cust", "dz", "tj", "mark"};
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
                StringBuilder str = new StringBuilder("update usr_oper_info t set  t.usr_mp='").append(record.get("mp").trim())
                        .append("',t.usr_tel='").append(record.get("mp").trim()).append("',t.usr_mp_enc='").append(record.get("mp").trim()).append("'   where  t.oper_id='000001' and t.cust_id='").append(record.get("cust").trim()).append("' and t.USR_MP_ENC != USR_MP ;\n");
                res.append("'").append(record.get("cust").trim()).append("',");
                hf.add(str.toString());
            }
            System.out.println("select t.usr_mp,t.usr_mp_enc,t.usr_tel,t.cust_id,t.oper_id from usr_oper_info t where t.oper_id='000001' and t.cust_id in ( " + res + ");");
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

    // 读取csv文件 传参数 文件 表头 从第几行开始
    public static void readCsvFile3(File file, Integer num) {
        BufferedReader br = null;
        CSVParser csvFileParser = null;
        String[] fileHeaders = {"name", "mp", "ls", "mps", "cust", "dz", "tj", "mark"};
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
            int count = 0;
            for (int i = num; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                if (record.get("cust").length() > 16) {
                    System.out.println(record.get("cust"));
                    count++;
                }
                // hf.add(str.toString());
            }
            System.out.println(count);
            System.out.println("select t.usr_mp,t.usr_mp_enc,t.usr_tel,t.cust_id,t.oper_id from usr_oper_info t where t.oper_id='000001' and t.cust_id in ( " + res + ");");
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