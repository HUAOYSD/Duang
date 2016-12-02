package org.duang.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.duang.common.logger.LoggerUtils;
import org.duang.util.ReadProperties;

public class BackUpDB {

	// 判断作业是否执行的旗标
	private boolean isRunning = false;
    private Properties pr;
	// 定义任务执行体
	public void run() {
		if (!isRunning) {
			try {
				LoggerUtils.info("数据库备份开始", this.getClass());
				exportDataBase();
				deleteFile();
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
		pr = ReadProperties.initPrperties("backupdb.properties");
		String user = ReadProperties.getStringValue(pr, "jdbc.username").trim();
		String password = ReadProperties.getStringValue(pr, "jdbc.password").trim();
		String database = ReadProperties.getStringValue(pr, "jdbc.database").trim();
		String filepath = ReadProperties.getStringValue(pr, "jdbc.backupPath").trim() + dbName;

		// 创建执行的批处理
		FileOutputStream fout = null;
		OutputStreamWriter writer = null;
		String batFile = "D:\\backup_databae.bat";
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

	/**
	 * 删除超期备份数据
	 */
	private void deleteFile(){
        File file = new File(ReadProperties.getStringValue(pr, "jdbc.backupPath").trim());  
        if (!file.exists()) {
        	LoggerUtils.info("目录不存在", this.getClass());
            return;
        }
        //查找生成数据库文件的前天的备份日志
  		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, -2);    //得到前天
        int day = calendar.get(Calendar.DATE);
        File fa[] = file.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if(accept(fs,day)){
            	fs.delete();
            	LoggerUtils.info("删除文件"+fs.toString(), this.getClass());
            }
        }
	}
	
	/**
	 * 匹配文件名称
	 * @param file
	 * @param day
	 * @return
	 */
    public boolean accept(File file,int day) {
      try {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{1,2}-"+day+"_\\d{1,2}_\\d{1,2}_\\d{1,2}.sql$");
        Matcher match = pattern.matcher(file.getName());                
        return match.matches();
      } catch (Exception e) {
    	LoggerUtils.debug("删除备份数据库文件时发生错误，原因："+e.getMessage(), this.getClass());
        return true;
      }
    }
}
