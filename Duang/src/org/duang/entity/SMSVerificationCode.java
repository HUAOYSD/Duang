package org.duang.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;


@Entity
@Table(name = "sms_verification_code", catalog = "duang")
@DynamicInsert(true)
public class SMSVerificationCode implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	private String phone;
	private String vCode;
	private Date startTime;
	private Date endTime;
	// Constructors

	/** default constructor */
	public SMSVerificationCode() {
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
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "v_code")
	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

	@Column(name = "startTime", nullable = false, length = 19)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime", nullable = false, length = 19)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	


}