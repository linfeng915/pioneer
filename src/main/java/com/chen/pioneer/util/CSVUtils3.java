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
public class CSVUtils3 {

    private static final Logger log = Logger.getLogger(CSVUtils3.class);

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
                String str = "     update mer_usr_map t\n" +
                        "            set t.invest_flag = 'Y', t.borrow_flag = 'N'\n" +
                        "          where t.mer_cust_id = '9000080000000120'\n" +
                        "            and t.guar_type != 'Y'\n" +
                        "            and t.borrow_flag = 'Y'\n" +
                        "            and t.cust_id in\n" +
                        "                (select t.cust_id\n" +
                        "                   from usr_bank_acct_info t\n" +
                        "                  where t.mer_cust_id = '9000080000000120'\n" +
                        "                    and t.bank_acct_id = '"+record.get("CUST_ID")+"');\n";
                res.append("'").append(record.get("CUST_ID")).append("',");
                hf.add(str);
            }
            System.out.println("select *\n" +
                    "  from mer_usr_map t\n" +
                    " where t.mer_cust_id = '9000080000000120'\n" +
                    "   and t.cust_id in\n" +
                    "       (select t.cust_id\n" +
                    "          from usr_bank_acct_info t\n" +
                    "         where t.mer_cust_id = '9000080000000120'\n" +
                    "           and t.bank_acct_id in ( "+res+"));");
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