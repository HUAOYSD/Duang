package org.duang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Award entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member_award", catalog = "duang")
public class MemberAward implements java.io.Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	private String id;
	private String memberinfoId;
	private String description;
	private String awardId;
	private int  usescore;
	private Date opttime;
	private String state;
	private String lotsNum;
	private int  experMoney;
	// Constructors

	/** default constructor */
	public MemberAward() {
	}

	/** minimal constructor */
	public MemberAward(String id) {
		this.id = id;
	}

	/** full constructor */
	public MemberAward(String id, String memberinfoId, String description, String awardId, int usescore, Date opttime, String state,String lotsNum,int experMoney) {
		this.id = id;
		this.memberinfoId = memberinfoId;
		this.description = description;
		this.awardId = awardId;
		this.usescore = usescore;
		this.opttime = opttime;
		this.state = state;
		this.lotsNum = lotsNum;
		this.experMoney = experMoney;
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

	@Column(name = "memberinfoId", nullable = false)
	public String getmemberinfoId() {
		return this.memberinfoId;
	}

	public void setmemberinfoId(String memberinfoId) {
		this.memberinfoId = memberinfoId;
	}

	@Column(name = "description", length = 500)
	public String getdescription() {
		return this.description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	@Column(name = "awardId")
	public String getawardId() {
		return this.awardId;
	}

	public void setawardId(String awardId) {
		this.awardId = awardId;
	}

	@Column(name = "usescore")
	public int getUsescore() {
		return this.usescore;
	}

	public void setUsescore(int usescore) {
		this.usescore = usescore;
	}

	@Column(name = "opttime")
	public Date getOpttime() {
		return this.opttime;
	}

	public void setOpttime(Date opttime) {
		this.opttime = opttime;
	}
	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "lotsNum")
	public String getLotsNum() {
		return this.lotsNum;
	}
	public void setLotsNum(String lotsNum) {
		this.lotsNum = lotsNum;
	}
	
	@Column(name = "experMoney")
	public int getExperMoney() {
		return this.experMoney;
	}
	public void setExperMoney(int experMoney) {
		this.experMoney = experMoney;
	}
}