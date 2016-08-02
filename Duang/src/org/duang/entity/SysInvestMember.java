package org.duang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 理财客户
 * @ClassName:  SysInvestMember   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月30日 下午10:52:07
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "investmember", catalog = "duang")
public class SysInvestMember implements java.io.Serializable {
	
	private String id;
	private SysUser user_id;
	private String idCard;
	private String bank_card;
	private Date bank;
	private Date user_image;
	private String idcard_img1;
	private String idcard_img2;
	private String cust_manager_id;
	private String manager_name;

	// Constructors

	/** default constructor */
	public SysInvestMember() {
	}

	/** minimal constructor */
	public SysInvestMember(String id) {
		this.id = id;
	}

	

	public SysInvestMember(String id, SysUser user_id, String idCard,
			String bank_card, Date bank, Date user_image, String idcard_img1,
			String idcard_img2, String cust_manager_id, String manager_name) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.idCard = idCard;
		this.bank_card = bank_card;
		this.bank = bank;
		this.user_image = user_image;
		this.idcard_img1 = idcard_img1;
		this.idcard_img2 = idcard_img2;
		this.cust_manager_id = cust_manager_id;
		this.manager_name = manager_name;
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
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public SysUser getUser_id() {
		return user_id;
	}

	public void setUser_id(SysUser user_id) {
		this.user_id = user_id;
	}

	@Column(name = "idCard", length = 255)
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "bank_card", length = 255)
	public String getBank_card() {
		return bank_card;
	}

	public void setBank_card(String bank_card) {
		this.bank_card = bank_card;
	}

	@Column(name = "bank", length = 255)
	public Date getBank() {
		return bank;
	}

	public void setBank(Date bank) {
		this.bank = bank;
	}

	@Column(name = "user_image", length = 255)
	public Date getUser_image() {
		return user_image;
	}

	public void setUser_image(Date user_image) {
		this.user_image = user_image;
	}

	@Column(name = "idcard_img1", length = 255)
	public String getIdcard_img1() {
		return idcard_img1;
	}

	public void setIdcard_img1(String idcard_img1) {
		this.idcard_img1 = idcard_img1;
	}

	@Column(name = "idcard_img2", length = 255)
	public String getIdcard_img2() {
		return idcard_img2;
	}

	public void setIdcard_img2(String idcard_img2) {
		this.idcard_img2 = idcard_img2;
	}

	@Column(name = "cust_manager_id", length = 255)
	public String getCust_manager_id() {
		return cust_manager_id;
	}

	public void setCust_manager_id(String cust_manager_id) {
		this.cust_manager_id = cust_manager_id;
	}

	@Column(name = "manager_name", length = 255)
	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

}