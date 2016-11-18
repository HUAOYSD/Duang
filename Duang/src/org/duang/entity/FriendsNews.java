package org.duang.entity;

import java.util.Date;
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
import javax.persistence.Transient;

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
	//图片信息，只用于接收，不保存friends_new 表中
	private String img_1;
	private String img_2;
	private String img_3;
	private String img_4;
	private String img_5;
	private String img_6;
	private String img_7;
	private String img_8;
	private String img_9;
	private int num;
	@Transient
	public String getImg_1() {
		return img_1;
	}

	public void setImg_1(String img_1) {
		this.img_1 = img_1;
	}
	@Transient
	public String getImg_2() {
		return img_2;
	}

	public void setImg_2(String img_2) {
		this.img_2 = img_2;
	}
	@Transient
	public String getImg_3() {
		return img_3;
	}

	public void setImg_3(String img_3) {
		this.img_3 = img_3;
	}
	@Transient
	public String getImg_4() {
		return img_4;
	}

	public void setImg_4(String img_4) {
		this.img_4 = img_4;
	}
	@Transient
	public String getImg_5() {
		return img_5;
	}

	public void setImg_5(String img_5) {
		this.img_5 = img_5;
	}
	@Transient
	public String getImg_6() {
		return img_6;
	}

	public void setImg_6(String img_6) {
		this.img_6 = img_6;
	}
	@Transient
	public String getImg_7() {
		return img_7;
	}

	public void setImg_7(String img_7) {
		this.img_7 = img_7;
	}
	@Transient
	public String getImg_8() {
		return img_8;
	}

	public void setImg_8(String img_8) {
		this.img_8 = img_8;
	}
	@Transient
	public String getImg_9() {
		return img_9;
	}

	public void setImg_9(String img_9) {
		this.img_9 = img_9;
	}
	@Transient
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	/** default constructor */
	public FriendsNews() {
	}

	/** minimal constructor */
	public FriendsNews(String id, MemberInfo memberInfo) {
		this.id = id;
		this.memberInfo = memberInfo;
	}

	/** full constructor */
	public FriendsNews(String id, MemberInfo memberInfo, String content, Date createtime, int state) {
		this.id = id;
		this.memberInfo = memberInfo;
		this.content = content;
		this.createtime = createtime;
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