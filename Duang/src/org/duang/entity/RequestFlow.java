package org.duang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * LoanList entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "request_flow", catalog = "duang")
@DynamicInsert(true)
public class RequestFlow implements java.io.Serializable {

	// Fields

	private String id;
	private String requestId;
	private String memberInfoId;
	private Date optTime;
	private String describe;
	private String result;
	
	public RequestFlow() {
		super();
	}
	public RequestFlow(String id) {
		super();
		this.id = id;
	}
	
	public RequestFlow(String id, String requestId, String memberInfoId,
			Date optTime, String describe, String result) {
		this.id = id;
		this.requestId = requestId;
		this.memberInfoId = memberInfoId;
		this.optTime = optTime;
		this.describe= describe;
		this.result = result;
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

	@Column(name = "optTime", length = 19)
	public Date getOptTime() {
		return this.optTime;
	}
	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
	
	@Column(name = "requestId")
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	@Column(name = "desInfo")
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	@Column(name = "result")
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@Column(name = "memberInfo_id")
	public String getMemberInfoId() {
		return memberInfoId;
	}
	public void setMemberInfoId(String memberInfoId) {
		this.memberInfoId = memberInfoId;
	}

	
	
}