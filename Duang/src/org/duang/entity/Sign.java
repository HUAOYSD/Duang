package org.duang.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;


@Entity
@Table(name = "sign", catalog = "duang")
@DynamicInsert(true)
public class Sign implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	private MemberInfo memberInfo;
	private Date signDate;
	private int signCount;
	private int score;
	// Constructors

	/** default constructor */
	public Sign() {
	}

	/** full constructor */
	public Sign(String id, MemberInfo memberInfo, Date signDate, int signCount,int score) {
		this.id = id;
		this.memberInfo = memberInfo;
		this.signDate = signDate;
		this.signCount = signCount;
		this.score = score;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	public MemberInfo getMemberInfo() {
		return this.memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	@Column(name = "signDate", nullable = false, length = 19)
	public Date getSignDate() {
		return this.signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	@Column(name = "signCount", nullable = false)
	public int getSignCount() {
		return this.signCount;
	}

	public void setSignCount(int signCount) {
		this.signCount = signCount;
	}
	@Column(name = "score", nullable = false)
	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}