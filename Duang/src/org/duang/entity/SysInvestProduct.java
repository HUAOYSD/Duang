package org.duang.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * 理财产品实体类
 * @ClassName:  SysInvestProduct   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月26日 下午2:08:44
 */
@Entity
@Table(name = "invest_product", catalog = "duang")
@DynamicInsert(true)
public class SysInvestProduct implements Serializable {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	private String id;
	private String nameZh; //总名称
	private String name; //名称
	private String name_describe; //描述
	private String yield_describe; //收益率描述
	private float yield; //准确的收益率
	private String charge_ratio; //手续费比例率
	private String title1; //标题1
	private String title2; //标题2
	private String min_deadline; //起投期限
	private String min_money; //起投金额
	private String refund_type;//还款方式
	
	private String is_sell; //是否起售
	private String is_lottery; //是否抽奖
	private String is_red_envel; //是否红包
	private String is_new_product;//是否新品
	private String is_recommend; //推荐
	private String product_describe;//介绍
	private String risk_control; //风险控制
	private String details; //详细
	private int isdelete;//是否删除 1删除，0未删除
	private Timestamp createtime;
	private Timestamp modifytime;
	private String createuser;
	private String modifyuser;
	
	
	public SysInvestProduct() {
	}
	public SysInvestProduct(String id, String nameZh) {
		this.id = id;
		this.nameZh = nameZh;
	}
	
	public SysInvestProduct(String id, String nameZh, String name, String name_describe, String yield_describe, float yield, String charge_ratio, String title1, String title2, String min_deadline, String min_money, String refund_type, String is_sell, String is_lottery, String is_red_envel, String is_new_product, String is_recommend, String product_describe, String risk_control, String details, int isdelete, Timestamp createtime, Timestamp modifytime, String createuser, String modifyuser) {
		this.id = id;
		this.nameZh = nameZh;
		this.name = name;
		this.name_describe = name_describe;
		this.yield_describe = yield_describe;
		this.yield = yield;
		this.charge_ratio = charge_ratio;
		this.title1 = title1;
		this.title2 = title2;
		this.min_deadline = min_deadline;
		this.min_money = min_money;
		this.refund_type = refund_type;
		this.is_sell = is_sell;
		this.is_lottery = is_lottery;
		this.is_red_envel = is_red_envel;
		this.is_new_product = is_new_product;
		this.is_recommend = is_recommend;
		this.product_describe = product_describe;
		this.risk_control = risk_control;
		this.details = details;
		this.isdelete = isdelete;
		this.createtime = createtime;
		this.modifytime = modifytime;
		this.createuser = createuser;
		this.modifyuser = modifyuser;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="name_zh",unique = true,nullable = false, length = 255 )
	public String getNameZh() {
		return nameZh;
	}
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}
	@Column(name="name",unique = true,nullable = false, length = 255 )
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="name_describe",length = 500 )
	public String getName_describe() {
		return name_describe;
	}
	public void setName_describe(String name_describe) {
		this.name_describe = name_describe;
	}
	@Column(name="yield_describe",length = 1000 )
	public String getYield_describe() {
		return yield_describe;
	}
	public void setYield_describe(String yield_describe) {
		this.yield_describe = yield_describe;
	}
	@Column(name="yield")
	public float getYield() {
		return yield;
	}
	public void setYield(float yield) {
		this.yield = yield;
	}
	@Column(name="charge_ratio",length = 255 )
	public String getCharge_ratio() {
		return charge_ratio;
	}
	public void setCharge_ratio(String charge_ratio) {
		this.charge_ratio = charge_ratio;
	}
	@Column(name="title1",length = 255 )
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	@Column(name="title2",length = 255 )
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	
	@Column(name="min_deadline")
	public String getMin_deadline() {
		return min_deadline;
	}
	public void setMin_deadline(String min_deadline) {
		this.min_deadline = min_deadline;
	}
	
	@Column(name="min_money")
	public String getMin_money() {
		return min_money;
	}
	public void setMin_money(String min_money) {
		this.min_money = min_money;
	}
	
	@Column(name="refund_type")
	public String getRefund_type() {
		return refund_type;
	}
	public void setRefund_type(String refund_type) {
		this.refund_type = refund_type;
	}
	
	@Column(name="is_sell")
	public String getIs_sell() {
		return is_sell;
	}
	public void setIs_sell(String is_sell) {
		this.is_sell = is_sell;
	}
	
	@Column(name="is_lottery")
	public String getIs_lottery() {
		return is_lottery;
	}
	public void setIs_lottery(String is_lottery) {
		this.is_lottery = is_lottery;
	}
	
	@Column(name="is_red_envel")
	public String getIs_red_envel() {
		return is_red_envel;
	}
	public void setIs_red_envel(String is_red_envel) {
		this.is_red_envel = is_red_envel;
	}
	
	@Column(name="is_new_product")
	public String getIs_new_product() {
		return is_new_product;
	}
	public void setIs_new_product(String is_new_product) {
		this.is_new_product = is_new_product;
	}
	
	@Column(name="is_recommend")
	public String getIs_recommend() {
		return is_recommend;
	}
	public void setIs_recommend(String is_recommend) {
		this.is_recommend = is_recommend;
	}
	
	@Column(name="product_describe",length = 2000)
	public String getProduct_describe() {
		return product_describe;
	}
	public void setProduct_describe(String product_describe) {
		this.product_describe = product_describe;
	}
	
	@Column(name="risk_control",length = 2000)
	public String getRisk_control() {
		return risk_control;
	}
	public void setRisk_control(String risk_control) {
		this.risk_control = risk_control;
	}
	
	@Column(name="details" ,length = 2000)
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Column(name="isdelete")
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	@Column(name="createtime",length = 19 )
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Column(name="modifytime",length = 19 )
	public Timestamp getModifytime() {
		return modifytime;
	}
	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}
	
	@Column(name="createuser",length = 255 )
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	@Column(name="modifyuser",length = 255 )
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	
	
	
}
