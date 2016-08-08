package org.duang.entity.old;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysLog entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_log", catalog = "duang")
public class SysLog implements java.io.Serializable {

	// Fields

	private String id;
	private String userName;
	private String modelName;
	private String logContent;
	private String logAction;
	private String logType;
	private String logIp;
	private Timestamp optionTime;
	private String logStatus;

	// Constructors

	/** default constructor */
	public SysLog() {
	}

	/** minimal constructor */
	public SysLog(String id, String userName) {
		this.id = id;
		this.userName = userName;
	}

	/** full constructor */
	public SysLog(String id, String userName, String modelName,
			String logContent, String logAction, String logType, String logIp,
			Timestamp optionTime, String logStatus) {
		this.id = id;
		this.userName = userName;
		this.modelName = modelName;
		this.logContent = logContent;
		this.logAction = logAction;
		this.logType = logType;
		this.logIp = logIp;
		this.optionTime = optionTime;
		this.logStatus = logStatus;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "user_name", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "model_name", length = 50)
	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Column(name = "log_content", length = 2046)
	public String getLogContent() {
		return this.logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	@Column(name = "log_action", length = 200)
	public String getLogAction() {
		return this.logAction;
	}

	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}

	@Column(name = "log_type", length = 10)
	public String getLogType() {
		return this.logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	@Column(name = "log_ip", length = 32)
	public String getLogIp() {
		return this.logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	@Column(name = "option_time", length = 19)
	public Timestamp getOptionTime() {
		return this.optionTime;
	}

	public void setOptionTime(Timestamp optionTime) {
		this.optionTime = optionTime;
	}

	@Column(name = "log_status", length = 2)
	public String getLogStatus() {
		return this.logStatus;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

}