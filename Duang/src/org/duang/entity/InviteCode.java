package org.duang.entity;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * InviteCode entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "invite_code", catalog = "duang")
@DynamicInsert(true)
public class InviteCode implements java.io.Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	private String id;
	private String memberId;
	private String inviteCode;
	private Date createTime;
	private int state;

	// Constructors

	/** default constructor */
	public InviteCode() {
	}

	/** minimal constructor */
	public InviteCode(String id, String memberId, String inviteCode, Date createTime) {
		this.id = id;
		this.memberId = memberId;
		this.inviteCode = inviteCode;
		this.createTime = createTime;
	}

	/** full constructor */
	public InviteCode(String id, String memberId, String inviteCode, Date createTime, int state) {
		this.id = id;
		this.memberId = memberId;
		this.inviteCode = inviteCode;
		this.createTime = createTime;
		this.state = state;
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

	@Column(name = "member_id", nullable = false, length = 36)
	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Column(name = "invite_code", nullable = false, length = 10)
	public String getInviteCode() {
		return this.inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "state")
	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

}