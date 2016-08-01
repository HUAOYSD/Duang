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

import org.hibernate.annotations.DynamicInsert;

/**
 * SysPower entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "member_info", catalog = "duang")
@DynamicInsert(true)
public class SysMemberInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String name; //登录名
	private String real_name; //真实姓名
	private String nickname; //
	private String email;
	private int age;
	private String sex;
	private String phone;
	private String describe;
	private int isdelete;
	private Date createTime;
	private Date modifyTime;
	private String createuser;
	private String modifyuser;
	
	private String user_img;
	private String is_elite_account;
	private String type;
	
	private String level;
	private String price;
	private String password;
	private String hand_password;
	private String is_freeze;
	// Constructors

	/** default constructor */
	public SysMemberInfo() {
	}

	/** minimal constructor */
	public SysMemberInfo(String id, String name, String url) {
		this.id = id;
		this.name = name;
	}
	
	public SysMemberInfo(String id, String name, String real_name, String nickname, String email, int age, String sex, String phone, String describe, int isdelete, Date createTime, Date modifyTime, String createuser, String modifyuser, String user_img, String is_elite_account, String type, String level, String price, String password, String hand_password, String is_freeze) {
		super();
		this.id = id;
		this.name = name;
		this.real_name = real_name;
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
		this.user_img = user_img;
		this.is_elite_account = is_elite_account;
		this.type = type;
		this.level = level;
		this.price = price;
		this.password = password;
		this.hand_password = hand_password;
		this.is_freeze = is_freeze;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 255)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "real_name", length = 255)
	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Column(name = "nickname", length = 255)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "email", length = 255)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(name = "sex", length =3)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "describe", length = 1000)
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Column(name = "isdelete", length = 2)
	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "modifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "createuser", length = 36)
	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	@Column(name = "modifyuser", length = 36)
	public String getModifyuser() {
		return modifyuser;
	}

	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}

	@Column(name = "user_img", length = 255)
	public String getUser_img() {
		return user_img;
	}

	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}

	@Column(name = "is_elite_account", length = 3)
	public String getIs_elite_account() {
		return is_elite_account;
	}

	public void setIs_elite_account(String is_elite_account) {
		this.is_elite_account = is_elite_account;
	}

	@Column(name = "type", length = 3)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "level", length = 3)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "price", length = 20)
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "password", length = 255)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "hand_password", length = 255)
	public String getHand_password() {
		return hand_password;
	}

	public void setHand_password(String hand_password) {
		this.hand_password = hand_password;
	}

	@Column(name = "is_freeze", length = 3)
	public String getIs_freeze() {
		return is_freeze;
	}

	public void setIs_freeze(String is_freeze) {
		this.is_freeze = is_freeze;
	}

}