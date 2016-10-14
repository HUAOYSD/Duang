package org.duang.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FriendsNews entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "friends_news", catalog = "duang")
public class FriendsNews implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private MemberInfo memberInfo;
	private String content;
	private Date createtime;
	private int state;
	private Set<FriendsNewsImg> friendsNewsImgs = new HashSet<FriendsNewsImg>(0);

	// Constructors

	/** default constructor */
	public FriendsNews() {
	}

	/** minimal constructor */
	public FriendsNews(String id, MemberInfo memberInfo) {
		this.id = id;
		this.memberInfo = memberInfo;
	}

	/** full constructor */
	public FriendsNews(String id, MemberInfo memberInfo, String content, Date createtime, int state, Set<FriendsNewsImg> friendsNewsImgs) {
		this.id = id;
		this.memberInfo = memberInfo;
		this.content = content;
		this.createtime = createtime;
		this.state = state;
		this.friendsNewsImgs = friendsNewsImgs;
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

	@Column(name = "content", length = 16777215)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "state")
	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "friendsNews")
	public Set<FriendsNewsImg> getFriendsNewsImgs() {
		return this.friendsNewsImgs;
	}

	public void setFriendsNewsImgs(Set<FriendsNewsImg> friendsNewsImgs) {
		this.friendsNewsImgs = friendsNewsImgs;
	}

}