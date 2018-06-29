package com.chen.pioneer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 白名单工具包
 * 
 * @类名称：CSVUtils
 * @类描述：csv文件读写
 * @创建时间：2017年1月10日 上午11:05:12
 * @version 1.0.0
 */
public class WhiteNameUtils {

	private static final Logger log = Logger.getLogger(WhiteNameUtils.class);

	static String lineSprit = System.getProperty("line.separator");
	static Map<String, String> map = new HashMap<String, String>();
	static Map<String, String> mark = new HashMap<String, String>();

	// 测试写入功能
	public static void main(String[] args) {

	
		
		
		
		
		
		
		
		mark.put("兴业银行","CIB");               
		mark.put("光大银行","CEB");               
		mark.put("工商银行","ICBC");                
		mark.put("民生银行","CMBC");                
		mark.put("江苏银行","JSCB");                
		mark.put("浦发银行","SPDB");               
		mark.put("浦东发展银行","SPDB");
		mark.put("中信银行","CITIC");              
		mark.put("招商银行","CMB");          
		mark.put("华夏银行","HXB");             
		mark.put("建设银行","CCB");
		mark.put("中国邮政储蓄银行","PSBC");
		mark.put("平安银行","PINGAN");          
		mark.put("浦东发展银行","SPDB");
		mark.put("广发银行","GDB");             
		mark.put("上海农村商业银行","SRCB");
		mark.put("交通银行","BOCOM");       
		mark.put("上海农商银行","SRCB");
		mark.put("农业银行","ABC");         
		mark.put("中国银行","BOC");    
		
		readCsvFile(new File("E:/test/st.csv"), 1);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}
		
		
	}

	// 读取csv文件 传参数 文件 表头 从第几行开始
	public static void readCsvFile(File file, Integer num) {
		BufferedReader br = null;
		CSVParser csvFileParser = null;
		String[] fileHeaders = { "name", "type", "id", "idkey", "card", "cardkey", "mobile", "cust", "bank", "shen",
				"scode", "dq", "dcode" };
		// 创建CSVFormat（header mapping）
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(fileHeaders);
		List<String> hf = new ArrayList<String>();
		try {
			// 初始化FileReader object
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));// 解决乱码问题
			// 初始化 CSVParser object
			csvFileParser = new CSVParser(br, csvFileFormat);
			// CSV文件records
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			for (int i = num; i < csvRecords.size(); i++) {
				CSVRecord record = csvRecords.get(i);
				//System.out.println(record.toString());
				StringBuilder sql = new StringBuilder("insert into special_usr_white_info  values ('20180206', ");
				sql.append("'").append(StringUtils.leftPad(i + "", 10, "0")).append("',");
				sql.append("'").append("6000060012153608").append("',");
				sql.append("'").append(record.get("cust")).append("',");
				sql.append("'").append("00").append("',");
				sql.append("'").append(record.get("id")).append("',");
				sql.append("'").append(record.get("name")).append("',");
				String str = record.get("bank").trim();
				if(StringUtils.isBlank(mark.get(str))){
					System.out.println(i+":"+str);
					continue;
				}
				sql.append("'").append(mark.get(str)).append("',");
				sql.append("'").append(record.get("card")).append("',");
				sql.append("'").append(record.get("mobile")).append("',");
				sql.append("'").append(record.get("dcode")).append("',");
				sql.append("'").append(record.get("scode")).append("',");
				sql.append("null").append(",");
				sql.append("'").append("20180206141300").append("',");
				sql.append("null").append(",");
				sql.append("null").append(",");
				sql.append("null").append(",");
				sql.append("null").append(",");
				sql.append("null").append(",");
				sql.append("null").append(",");
				sql.append("'").append(record.get("idkey")).append("',");
				sql.append("'").append(record.get("cardkey")).append("');");
				hf.add(sql.toString());
				//System.out.println(sql);
				//map.put(record.get("bank"), record.get("bank"));
			}
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

		try {
			FileUtils.writeLines(new File("E:/test/hu.txt"), hf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void readCsvFile2(File file, Integer num) {
		BufferedReader br = null;
		CSVParser csvFileParser = null;
		String[] fileHeaders = { "name", "num" };
		// 创建CSVFormat（header mapping）
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(fileHeaders);
		try {
			// 初始化FileReader object
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));// 解决乱码问题
			// 初始化 CSVParser object
			csvFileParser = new CSVParser(br, csvFileFormat);
			// CSV文件records
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			for (int i = num; i < csvRecords.size(); i++) {
				CSVRecord record = csvRecords.get(i);
				String key = record.get("num");
				if (map.containsKey(key)) {
					mark.put(key, map.get(key));
				}
			}
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