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

/**
 * ScoreList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "score_list", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ScoreList implements java.io.Serializable {

	// Fields

	private String id;
	private MemberInfo memberInfo;
	private int score;
	private String resourceFrom;
	private Date optTime;

	// Constructors

	/** default constructor */
	public ScoreList() {
	}

	/** minimal constructor */
	public ScoreList(String id) {
		this.id = id;
	}

	/** full constructor */
	public ScoreList(String id, MemberInfo memberInfo, int score,
			String resourceFrom, Date optTime) {
		this.id = id;
		this.memberInfo = memberInfo;
		this.score = score;
		this.resourceFrom = resourceFrom;
		this.optTime = optTime;
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
	@JoinColumn(name = "member_info_id")
	public MemberInfo getMemberInfo() {
		return this.memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	@Column(name = "score")
	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Column(name = "resource_from", length = 300)
	public String getResourceFrom() {
		return this.resourceFrom;
	}

	public void setResourceFrom(String resourceFrom) {
		this.resourceFrom = resourceFrom;
	}

	@Column(name = "opt_time", length = 19)
	public Date getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

}