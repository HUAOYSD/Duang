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
 * Product entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "product", catalog = "duang")
@DynamicInsert(true)
public class Product implements java.io.Serializable {

	// Fields

	private String id;
	private int category;
	private String nameZh;
	private String name;
	private String nameDescribe;
	private String yieldDescribe;
	private double yield;
	private String timeLimit;
	private String title1;
	private String title2;
	private int isSell;
	private int isNewProduct;
	private int isRecommend;
	private String productDescribe;
	private String riskControl;
	private String details;
	private int isdelete;
	private Date createtime;
	private Date modifytime;
	private String createuser;
	private String modifyuser;
	private Set<Scale> scales = new HashSet<Scale>(0);

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(String id, int category, String nameZh) {
		this.id = id;
		this.category = category;
		this.nameZh = nameZh;
	}

	/** full constructor */
	public Product(String id, int category, String nameZh, String name, String nameDescribe, String yieldDescribe, double yield, String timeLimit, String title1, String title2, int isSell, int isNewProduct, int isRecommend, String productDescribe, String riskControl, String details, int isdelete, Date createtime, Date modifytime, String createuser, String modifyuser, Set<Scale> scales) {
		this.id = id;
		this.category = category;
		this.nameZh = nameZh;
		this.name = name;
		this.nameDescribe = nameDescribe;
		this.yieldDescribe = yieldDescribe;
		this.yield = yield;
		this.timeLimit = timeLimit;
		this.title1 = title1;
		this.title2 = title2;
		this.isSell = isSell;
		this.isNewProduct = isNewProduct;
		this.isRecommend = isRecommend;
		this.productDescribe = productDescribe;
		this.riskControl = riskControl;
		this.details = details;
		this.isdelete = isdelete;
		this.createtime = createtime;
		this.modifytime = modifytime;
		this.createuser = createuser;
		this.modifyuser = modifyuser;
		this.scales = scales;
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

	@Column(name = "category", nullable = false)
	public int getCategory() {
		return this.category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	@Column(name = "name_zh", nullable = false)
	public String getNameZh() {
		return this.nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name_describe", length = 500)
	public String getNameDescribe() {
		return this.nameDescribe;
	}

	public void setNameDescribe(String nameDescribe) {
		this.nameDescribe = nameDescribe;
	}

	@Column(name = "yield_describe", length = 2000)
	public String getYieldDescribe() {
		return this.yieldDescribe;
	}

	public void setYieldDescribe(String yieldDescribe) {
		this.yieldDescribe = yieldDescribe;
	}

	@Column(name = "yield", precision = 22, scale = 0)
	public double getYield() {
		return this.yield;
	}

	public void setYield(double yield) {
		this.yield = yield;
	}

	@Column(name = "time_limit", length = 60)
	public String getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name = "title1", length = 500)
	public String getTitle1() {
		return this.title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	@Column(name = "title2", length = 500)
	public String getTitle2() {
		return this.title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	@Column(name = "is_sell")
	public int getIsSell() {
		return this.isSell;
	}

	public void setIsSell(int isSell) {
		this.isSell = isSell;
	}

	@Column(name = "is_new_product")
	public int getIsNewProduct() {
		return this.isNewProduct;
	}

	public void setIsNewProduct(int isNewProduct) {
		this.isNewProduct = isNewProduct;
	}

	@Column(name = "is_recommend")
	public int getIsRecommend() {
		return this.isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

	@Column(name = "product_describe", length = 16777215)
	public String getProductDescribe() {
		return this.productDescribe;
	}

	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}

	@Column(name = "risk_control", length = 16777215)
	public String getRiskControl() {
		return this.riskControl;
	}

	public void setRiskControl(String riskControl) {
		this.riskControl = riskControl;
	}

	@Column(name = "details", length = 16777215)
	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Column(name = "isdelete")
	public int getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "modifytime", length = 19)
	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Scale> getScales() {
		return this.scales;
	}

	public void setScales(Set<Scale> scales) {
		this.scales = scales;
	}

}