package org.duang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 中间人放款记录
 */
@Entity
@Table(name = "member_middle_records", catalog = "duang")
public class MemberMiddleRecords implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	private String id;
	private String scaleId;
	private MemberMiddle memberMiddle;
	private Double money;
	private Date createTime;
	// Constructors
	/** default constructor */
	public MemberMiddleRecords() {
	}

	/** minimal constructor */
	public MemberMiddleRecords(String id) {
		this.id = id;
	}

	/** full constructor */
	public MemberMiddleRecords(String id, String scaleId, MemberMiddle memberMiddle, Double money, Date createTime) {
		this.id = id;
		this.scaleId = scaleId;
		this.memberMiddle = memberMiddle;
		this.money = money;
		this.createTime = createTime;
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
	
	@Column(name = "scaleId", length = 36)
	public String getScaleId() {
		return this.scaleId;
	}

	public void setScaleId(String scaleId) {
		this.scaleId = scaleId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "middle_id")
	public MemberMiddle getMemberMiddle() {
		return this.memberMiddle;
	}

	public void setMemberMiddle(MemberMiddle memberMiddle) {
		this.memberMiddle = memberMiddle;
	}

	@Column(name = "money", precision = 255, scale = 0)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}