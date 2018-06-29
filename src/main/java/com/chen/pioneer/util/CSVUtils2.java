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
 * @类名称：CSVUtils
 * @类描述：csv文件读写
 * @创建时间：2017年1月10日 上午11:05:12
 * @version 1.0.0
 */
public class CSVUtils2 {

	private static final Logger log = Logger.getLogger(CSVUtils2.class);

	static String lineSprit = System.getProperty("line.separator");
	static Map<String, String> map = new HashMap<String, String>();
	static Map<String, String> mark = new HashMap<String, String>();

	// 写csv文件 传参数文件名 路径 csv文件表头 需要写入的数据
	public static File writeCsvFile(String fileName, String path, String[] fileHeaders, List<List<String>> list) {
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		CSVPrinter csvPrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(fileHeaders);
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdir();
			}
			csvFile = new File(path + fileName + ".csv");
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"),
					1024);
			// 初始化 CSVPrinter
			csvPrinter = new CSVPrinter(csvFileOutputStream, csvFileFormat);
			List<String> ls = null;
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ls = new ArrayList<String>();
					ls = list.get(i);
					for (int j = 0; j < ls.size(); j++) {
						csvPrinter.print(ls.get(j));
					}
					csvPrinter.println();// 换行
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("csv文件写入异常");
		} finally {
			try {
				csvFileOutputStream.flush();
				csvFileOutputStream.close();
				csvPrinter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvFile;
	}

	// 测试写入功能
	public static void main(String[] args) {

		readCsvFile(new File("E:/test/usr.csv"), 1);
		

		
		for (Map.Entry<String, String> entry : mark.entrySet()) {
			String sql = "update mer_usr_map set open_date='20170303' where cust_id = '6000060172898851'";
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			
		}
		
	}

	// 读取csv文件 传参数 文件 表头 从第几行开始
	public static void readCsvFile(File file, Integer num) {
		BufferedReader br = null;
		CSVParser csvFileParser = null;
		String[] fileHeaders = { "A","B","C","D","E","F" };
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
				map.put(record.get("CUST_ID"), record.get("BANK_ACCT_ID"));
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