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
 * Message entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "message", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class Message implements java.io.Serializable {

	// Fields

	private String id;
	private MemberInfo memberInfoByReceiver;
	private MemberInfo memberInfoBySender;
	private Date time;
	private String title;
	private String content;
	private int readed;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(String id) {
		this.id = id;
	}

	/** full constructor */
	public Message(String id, MemberInfo memberInfoByReceiver,
			MemberInfo memberInfoBySender, Date time, String title,
			String content, int readed) {
		this.id = id;
		this.memberInfoByReceiver = memberInfoByReceiver;
		this.memberInfoBySender = memberInfoBySender;
		this.time = time;
		this.title = title;
		this.content = content;
		this.readed = readed;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receiver")
	public MemberInfo getMemberInfoByReceiver() {
		return this.memberInfoByReceiver;
	}

	public void setMemberInfoByReceiver(MemberInfo memberInfoByReceiver) {
		this.memberInfoByReceiver = memberInfoByReceiver;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender")
	public MemberInfo getMemberInfoBySender() {
		return this.memberInfoBySender;
	}

	public void setMemberInfoBySender(MemberInfo memberInfoBySender) {
		this.memberInfoBySender = memberInfoBySender;
	}

	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "title", length = 300)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 3000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "readed")
	public int getReaded() {
		return this.readed;
	}

	public void setReaded(int readed) {
		this.readed = readed;
	}

}