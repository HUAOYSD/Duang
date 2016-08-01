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
@Table(name = "invest_member", catalog = "duang")
public class SysInvestMember implements java.io.Serializable {
	
	private String id;
	private SysMemberInfo memberinfo_id;
	private String idCard;
	private String bank_card;
	private String bank;
	private String user_image;
	private String idcard_img1;
	private String idcard_img2;
	private String cust_manager_id;
	private String manager_name;
    private String is_contract;
    private String memberinfo_ids;
    
    

	/** default constructor */
	public SysInvestMember() {
	}

	/** minimal constructor */
	public SysInvestMember(String id) {
		this.id = id;
	}

	 @Column(name = "memberinfo_id", length = 36)
	public String getMemberinfo_ids() {
		return memberinfo_ids;
	}

	public void setMemberinfo_ids(String memberinfo_ids) {
		this.memberinfo_ids = memberinfo_ids;
	}

	public SysInvestMember(String id, SysMemberInfo memberinfo_id, String idCard,
			String bank_card, String bank, String user_image, String idcard_img1,
			String idcard_img2, String cust_manager_id, String manager_name) {
		super();
		this.id = id;
		this.memberinfo_id = memberinfo_id;
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
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberinfo_id")
	public SysMemberInfo getMemberinfo_id() {
		return memberinfo_id;
	}

	public void setMemberinfo_id(SysMemberInfo memberinfo_id) {
		this.memberinfo_id = memberinfo_id;
	}

	@Column(name = "is_contract", length = 255)
	public String getIs_contract() {
		return is_contract;
	}

	public void setIs_contract(String is_contract) {
		this.is_contract = is_contract;
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
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "user_image", length = 255)
	public String getUser_image() {
		return user_image;
	}

	public void setUser_image(String user_image) {
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