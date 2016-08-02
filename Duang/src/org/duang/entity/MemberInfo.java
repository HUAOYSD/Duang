package org.duang.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MemberInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member_info", catalog = "duang")
public class MemberInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String realName;
	private String nickname;
	private String email;
	private String age;
	private String sex;
	private String phone;
	private String describe;
	private String isdelete;
	private Date createTime;
	private Date modifyTime;
	private String createuser;
	private String modifyuser;
	private String userImg;
	private String isEliteAccount;
	private String type;
	private String level;
	private String price;
	private String password;
	private String handPassword;
	private String isFreeze;
	private Set<InvestMember> investMembers = new HashSet<InvestMember>(0);

	// Constructors

	/** default constructor */
	public MemberInfo() {
	}

	/** minimal constructor */
	public MemberInfo(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public MemberInfo(String id, String name, String realName, String nickname, String email, String age, String sex, String phone, String describe, String isdelete, Date createTime, Date modifyTime, String createuser, String modifyuser, String userImg, String isEliteAccount, String type, String level, String price, String password, String handPassword, String isFreeze, Set<InvestMember> investMembers) {
		this.id = id;
		this.name = name;
		this.realName = realName;
		this.nickname = nickname;
		this.email = email;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
		this.describe = describe;
		this.isdelete = isdelete;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createuser = createuser;
		this.modifyuser = modifyuser;
		this.userImg = userImg;
		this.isEliteAccount = isEliteAccount;
		this.type = type;
		this.level = level;
		this.price = price;
		this.password = password;
		this.handPassword = handPassword;
		this.isFreeze = isFreeze;
		this.investMembers = investMembers;
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

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "real_name")
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "nickname")
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "age", length = 3)
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Column(name = "sex", length = 3)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "describe", length = 1000)
	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Column(name = "isdelete", length = 2)
	public String getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "modifyTime", length = 19)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "createuser", length = 36)
	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	@Column(name = "modifyuser", length = 36)
	public String getModifyuser() {
		return this.modifyuser;
	}

	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}

	@Column(name = "user_img")
	public String getUserImg() {
		return this.userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	@Column(name = "is_elite_account", length = 3)
	public String getIsEliteAccount() {
		return this.isEliteAccount;
	}

	public void setIsEliteAccount(String isEliteAccount) {
		this.isEliteAccount = isEliteAccount;
	}

	@Column(name = "type", length = 3)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "level", length = 3)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "price", length = 20)
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "hand_password")
	public String getHandPassword() {
		return this.handPassword;
	}

	public void setHandPassword(String handPassword) {
		this.handPassword = handPassword;
	}

	@Column(name = "is_freeze", length = 3)
	public String getIsFreeze() {
		return this.isFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<InvestMember> getInvestMembers() {
		return this.investMembers;
	}

	public void setInvestMembers(Set<InvestMember> investMembers) {
		this.investMembers = investMembers;
	}

}