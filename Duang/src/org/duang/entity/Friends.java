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
 * Friends entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "friends", catalog = "duang")
@DynamicInsert(true)
public class Friends implements java.io.Serializable {

	// Fields

	private String id;
	private MemberInfo memberInfoByTarget;
	private MemberInfo memberInfoBySelf;
	private int together;
	private Date optTime;

	// Constructors

	/** default constructor */
	public Friends() {
	}

	/** minimal constructor */
	public Friends(String id) {
		this.id = id;
	}

	/** full constructor */
	public Friends(String id, MemberInfo memberInfoByTarget, MemberInfo memberInfoBySelf, int together, Date optTime) {
		this.id = id;
		this.memberInfoByTarget = memberInfoByTarget;
		this.memberInfoBySelf = memberInfoBySelf;
		this.together = together;
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
	@JoinColumn(name = "target")
	public MemberInfo getMemberInfoByTarget() {
		return this.memberInfoByTarget;
	}

	public void setMemberInfoByTarget(MemberInfo memberInfoByTarget) {
		this.memberInfoByTarget = memberInfoByTarget;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "self")
	public MemberInfo getMemberInfoBySelf() {
		return this.memberInfoBySelf;
	}

	public void setMemberInfoBySelf(MemberInfo memberInfoBySelf) {
		this.memberInfoBySelf = memberInfoBySelf;
	}

	@Column(name = "together")
	public int getTogether() {
		return this.together;
	}

	public void setTogether(int together) {
		this.together = together;
	}

	@Column(name = "opt_time", length = 19)
	public Date getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

}