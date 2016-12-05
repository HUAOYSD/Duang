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
 * 放款中间人
 * @ClassName:  MemberMiddle   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年11月28日 上午9:43:07
 */
@Entity
@Table(name = "member_middle", catalog = "duang")
public class MemberMiddle implements java.io.Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	private String id;
	private String userName;
	private String idcard;
	private Date createTime;
	private Date modifyTime;
	private String state;
	private String createUser;
	private String modifyUser;
	private String payType;
	private String isAuth;
	private String  token;
	private double totalSum;
	private double lastSum;
	private Set<MemberMiddleRecords> memberMiddleRecords = new HashSet<MemberMiddleRecords>(0);

	/** default constructor */
	public MemberMiddle() {
	}

	/** minimal constructor */
	public MemberMiddle(String id) {
		this.id = id;
	}

	public MemberMiddle(String id, String userName, String idcard, Date createTime, Date modifyTime, String state, 
			String createUser, String modifyUser, String payType, double totalSum, double lastSum) {
		this.id = id;
		this.userName = userName;
		this.idcard = idcard;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.state = state;
		this.createUser = createUser;
		this.modifyUser = modifyUser;
		this.payType = payType;
		this.totalSum = totalSum;
		this.lastSum = lastSum;
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
	@Column(name = "userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "idcard")
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
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

	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "createUser")
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "modifyUser")
	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Column(name = "payType")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "isAuth")
	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}
	
	@Column(name = "token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(name = "totalSum")
	public double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}
	
	@Column(name = "lastSum")
	public double getLastSum() {
		return lastSum;
	}

	public void setLastSum(double lastSum) {
		this.lastSum = lastSum;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<MemberMiddleRecords> getMemberMiddleRecords() {
		return this.memberMiddleRecords;
	}

	public void setMemberMiddleRecords(Set<MemberMiddleRecords> memberMiddleRecords) {
		this.memberMiddleRecords = memberMiddleRecords;
	}
}