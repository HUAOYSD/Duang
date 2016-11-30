package org.duang.task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

import org.duang.common.logger.LoggerUtils;
import org.duang.util.ReadProperties;

public class BackUpDB {

	// 判断作业是否执行的旗标
	private boolean isRunning = false;

	// 定义任务执行体
	public void run() {
		if (!isRunning) {
			try {
				LoggerUtils.info("数据库备份开始", this.getClass());
				exportDataBase();
			} catch (Exception e) {
				LoggerUtils.error("BackUpDB-----数据库备份错误：" + e.getMessage(), this.getClass());
				e.printStackTrace();
			}
		}
	}

	/**
	 * 备份数据库
	 * @throws Exception 
	 */
	public  void exportDataBase() throws Exception {
		Date now = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();
		String dbName = df.format(now) + ".sql";
		dbName = dbName.replaceAll(":", "_");
		dbName = dbName.replaceAll(" ", "_");
		Properties  pr = ReadProperties.initPrperties("backupdb.properties");
		String user = ReadProperties.getStringValue(pr, "jdbc.username").trim();
		String password = ReadProperties.getStringValue(pr, "jdbc.password").trim();
		String database = ReadProperties.getStringValue(pr, "jdbc.database").trim();
		String filepath = ReadProperties.getStringValue(pr, "jdbc.backupPath").trim() + dbName;

		// 创建执行的批处理
		FileOutputStream fout = null;
		OutputStreamWriter writer = null;
		String batFile = "E:\\backup_databae.bat";
		fout = new FileOutputStream(batFile);
		writer = new OutputStreamWriter(fout, "utf8");
		StringBuffer sb = new StringBuffer("");
		sb.append(ReadProperties.getStringValue(pr,"mysql.lumu").trim() + " \r\n");
		sb.append("cd " + ReadProperties.getStringValue(pr,"mysql.binPath").trim() + " \r\n");
		sb.append("mysqldump -u " + user + " -p" + password + " " + database + " >" + filepath + "\r\n");
		sb.append("exit");
		String outStr = sb.toString();
		writer.write(outStr);
		writer.flush();
		writer.close();
		fout.close();
		Runtime.getRuntime().exec(" cmd /c start " + batFile);
		LoggerUtils.info("数据库备份成功", this.getClass() );
		writer = null;
		fout = null;
	}

}
