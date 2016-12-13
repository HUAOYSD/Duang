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

import org.hibernate.annotations.DynamicInsert;

/**
 * MemberInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member_info", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class MemberInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String loginName;
	private String realName;
	private String nickname;
	private String email;
	private String age;
	private String sex;
	private String phone;
	private String idCard;
	private String idCardImg1;
	private String miDescribe;
	private String isdelete;
	private Date createTime;
	private Date modifyTime;
	private String createuser;
	private String modifyuser;
	private String userImg;
	private int isEliteAccount;
	private String level;
	private String price;
	private String password;
	private String payPassword;
	private String handPassword;
	private int isFreeze;
	private String myQr;
	private int registerStyle;
	private int useableScore;
	
	private int isAuth;
	private String payType;
	private String token;
	private String requestId;
	private String entityCode;
	//资金存管平台用户ID
	private String userId;
	private Set<MemberExtraInfo> memberExtraInfos = new HashSet<MemberExtraInfo>(0);
	private Set<Friends> friendsesForTarget = new HashSet<Friends>(0);
	private Set<Friends> friendsesForSelf = new HashSet<Friends>(0);
	private Set<LoanMember> loanMembers = new HashSet<LoanMember>(0);
	private Set<Message> messagesForReceiver = new HashSet<Message>(0);
	private Set<BindCard> bindCards = new HashSet<BindCard>(0);
	private Set<InvestMember> investMembers = new HashSet<InvestMember>(0);
	private Set<ScoreList> scoreLists = new HashSet<ScoreList>(0);
	private Set<Message> messagesForSender = new HashSet<Message>(0);
	private Set<MemberInvestTicket> memberInvestTickets = new HashSet<MemberInvestTicket>(0);
	private Set<InvestList> investLists = new HashSet<InvestList>(0);
	private Set<LoanList> loanLists = new HashSet<LoanList>(0);
	
	private CustomerManager customerManager;
	private MemberInfo memberInfo;
	private String cusmembername;
	private Set<MemberInfo> memberInfos = new HashSet<MemberInfo>(0);
	
	private Set<NotificationReaded> notificationReadeds = new HashSet<NotificationReaded>(0);
	private Set<BillLoan> billLoans = new HashSet<BillLoan>(0);
	private Set<BillInvest> billInvests = new HashSet<BillInvest>(0);
	
	
	//图片信息，只用于接收，不保存数据库表中
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
	
	
	public MemberInfo(String id, CustomerManager customerManager, MemberInfo memberInfo, String loginName, String realName, String nickname, String email, String age, String sex, String phone, String idCard, 
			String idCardImg1, String miDescribe, String isdelete, Date createTime, Date modifyTime, String createuser, String modifyuser, String userImg, 
			Integer isEliteAccount, String level, String price, String password, String payPassword, String handPassword, Integer isFreeze, String myQr, 
			Integer registerStyle, String cusmembername, Set<MemberInvestTicket> memberInvestTickets, Set<InvestMember> investMembers, 
			Set<LoanMember> loanMembers, Set<Message> messagesForReceiver, Set<InvestList> investLists, Set<ScoreList> scoreLists,
			Set<Message> messagesForSender, Set<Friends> friendsesForTarget, Set<MemberExtraInfo> memberExtraInfos, 
			Set<BillLoan> billLoans, Set<BillInvest> billInvests, Set<Friends> friendsesForSelf, Set<NotificationReaded> notificationReadeds, 
			Set<BindCard> bindCards, Set<MemberInfo> memberInfos, Set<LoanList> loanLists, int useableScore) {
		this.id = id;
		this.customerManager = customerManager;
		this.memberInfo = memberInfo;
		this.loginName = loginName;
		this.realName = realName;
		this.nickname = nickname;
		this.email = email;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
		this.idCard = idCard;
		this.idCardImg1 = idCardImg1;
		this.miDescribe = miDescribe;
		this.isdelete = isdelete;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createuser = createuser;
		this.modifyuser = modifyuser;
		this.userImg = userImg;
		this.isEliteAccount = isEliteAccount;
		this.level = level;
		this.price = price;
		this.password = password;
		this.payPassword = payPassword;
		this.handPassword = handPassword;
		this.isFreeze = isFreeze;
		this.myQr = myQr;
		this.registerStyle = registerStyle;
		this.cusmembername = cusmembername;
		this.memberInvestTickets = memberInvestTickets;
		this.investMembers = investMembers;
		this.loanMembers = loanMembers;
		this.messagesForReceiver = messagesForReceiver;
		this.investLists = investLists;
		this.scoreLists = scoreLists;
		this.messagesForSender = messagesForSender;
		this.friendsesForTarget = friendsesForTarget;
		this.memberExtraInfos = memberExtraInfos;
		this.billLoans = billLoans;
		this.billInvests = billInvests;
		this.friendsesForSelf = friendsesForSelf;
		this.notificationReadeds = notificationReadeds;
		this.bindCards = bindCards;
		this.memberInfos = memberInfos;
		this.loanLists = loanLists;
		this.useableScore = useableScore;
	}
	
	
	
	
	
	
	
	
	
	
	// Constructors

	/** default constructor */
	public MemberInfo() {
	}

	/** minimal constructor */
	public MemberInfo(String id) {
		this.id = id;
	}
	
	public MemberInfo(String id, String loginName) {
		this.id = id;
		this.loginName = loginName;
	}

	/** full constructor */
	public MemberInfo(String id, String loginName, String realName,
			String nickname, String email, String age, String sex,
			String phone, String idCard, String idCardImg1, 
			String miDescribe, String isdelete, Date createTime,
			Date modifyTime, String createuser, String modifyuser,
			String userImg, int isEliteAccount, String level,
			String price, String password, String payPassword,
			String handPassword, int isFreeze, String myQr,
			Set<MemberExtraInfo> memberExtraInfos,
			Set<Friends> friendsesForTarget, Set<Friends> friendsesForSelf,
			Set<LoanMember> loanMembers, Set<Message> messagesForReceiver,Set<LoanList> loanLists,
			Set<BindCard> bindCards, Set<InvestMember> investMembers,Set<MemberInvestTicket> memberInvestTickets,
			Set<ScoreList> scoreLists, Set<Message> messagesForSender,Set<InvestList> investLists,int registerStyle) {
		this.id = id;
		this.loginName = loginName;
		this.realName = realName;
		this.nickname = nickname;
		this.email = email;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
		this.idCard = idCard;
		this.loanLists = loanLists;
		this.idCardImg1 = idCardImg1;
		this.miDescribe = miDescribe;
		this.isdelete = isdelete;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createuser = createuser;
		this.modifyuser = modifyuser;
		this.userImg = userImg;
		this.isEliteAccount = isEliteAccount;
		this.level = level;
		this.price = price;
		this.password = password;
		this.payPassword = payPassword;
		this.handPassword = handPassword;
		this.isFreeze = isFreeze;
		this.myQr = myQr;
		this.memberExtraInfos = memberExtraInfos;
		this.friendsesForTarget = friendsesForTarget;
		this.friendsesForSelf = friendsesForSelf;
		this.loanMembers = loanMembers;
		this.messagesForReceiver = messagesForReceiver;
		this.bindCards = bindCards;
		this.investMembers = investMembers;
		this.scoreLists = scoreLists;
		this.messagesForSender = messagesForSender;
		this.memberInvestTickets = memberInvestTickets;
		this.investLists = investLists;
		this.registerStyle = registerStyle;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<MemberInvestTicket> getMemberInvestTickets() {
		return this.memberInvestTickets;
	}

	public void setMemberInvestTickets(
			Set<MemberInvestTicket> memberInvestTickets) {
		this.memberInvestTickets = memberInvestTickets;
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

	@Column(name = "login_name", nullable = false)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	@Column(name = "age", length = 10)
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Column(name = "sex", length = 10)
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

	@Column(name = "id_card", length = 20)
	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "id_card_img1", length = 500)
	public String getIdCardImg1() {
		return this.idCardImg1;
	}

	public void setIdCardImg1(String idCardImg1) {
		this.idCardImg1 = idCardImg1;
	}

	@Column(name = "mi_describe", length = 16777215)
	public String getMiDescribe() {
		return this.miDescribe;
	}

	public void setMiDescribe(String miDescribe) {
		this.miDescribe = miDescribe;
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

	@Column(name = "is_elite_account")
	public int getIsEliteAccount() {
		return this.isEliteAccount;
	}

	public void setIsEliteAccount(int isEliteAccount) {
		this.isEliteAccount = isEliteAccount;
	}

	@Column(name = "level", length = 10)
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

	@Column(name = "password", length = 500)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "pay_password", length = 500)
	public String getPayPassword() {
		return this.payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	@Column(name = "hand_password", length = 500)
	public String getHandPassword() {
		return this.handPassword;
	}

	public void setHandPassword(String handPassword) {
		this.handPassword = handPassword;
	}

	@Column(name = "is_freeze")
	public int getIsFreeze() {
		return this.isFreeze;
	}

	public void setIsFreeze(int isFreeze) {
		this.isFreeze = isFreeze;
	}

	@Column(name = "my_qr", length = 500)
	public String getMyQr() {
		return this.myQr;
	}

	public void setMyQr(String myQr) {
		this.myQr = myQr;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<MemberExtraInfo> getMemberExtraInfos() {
		return this.memberExtraInfos;
	}

	public void setMemberExtraInfos(Set<MemberExtraInfo> memberExtraInfos) {
		this.memberExtraInfos = memberExtraInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfoByTarget")
	public Set<Friends> getFriendsesForTarget() {
		return this.friendsesForTarget;
	}

	public void setFriendsesForTarget(Set<Friends> friendsesForTarget) {
		this.friendsesForTarget = friendsesForTarget;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfoBySelf")
	public Set<Friends> getFriendsesForSelf() {
		return this.friendsesForSelf;
	}

	public void setFriendsesForSelf(Set<Friends> friendsesForSelf) {
		this.friendsesForSelf = friendsesForSelf;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<LoanMember> getLoanMembers() {
		return this.loanMembers;
	}

	public void setLoanMembers(Set<LoanMember> loanMembers) {
		this.loanMembers = loanMembers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfoByReceiver")
	public Set<Message> getMessagesForReceiver() {
		return this.messagesForReceiver;
	}

	public void setMessagesForReceiver(Set<Message> messagesForReceiver) {
		this.messagesForReceiver = messagesForReceiver;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<BindCard> getBindCards() {
		return this.bindCards;
	}

	public void setBindCards(Set<BindCard> bindCards) {
		this.bindCards = bindCards;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<InvestMember> getInvestMembers() {
		return this.investMembers;
	}

	public void setInvestMembers(Set<InvestMember> investMembers) {
		this.investMembers = investMembers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<ScoreList> getScoreLists() {
		return this.scoreLists;
	}

	public void setScoreLists(Set<ScoreList> scoreLists) {
		this.scoreLists = scoreLists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfoBySender")
	public Set<Message> getMessagesForSender() {
		return this.messagesForSender;
	}

	public void setMessagesForSender(Set<Message> messagesForSender) {
		this.messagesForSender = messagesForSender;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<InvestList> getInvestLists() {
		return this.investLists;
	}

	public void setInvestLists(Set<InvestList> investLists) {
		this.investLists = investLists;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<LoanList> getLoanLists() {
		return this.loanLists;
	}

	public void setLoanLists(Set<LoanList> loanLists) {
		this.loanLists = loanLists;
	}
	@Column(name = "register_style")
	public int getRegisterStyle() {
		return this.registerStyle;
	}

	public void setRegisterStyle(int registerStyle) {
		this.registerStyle = registerStyle;
	}
	
	@Column(name = "useable_score")
	public int getUseableScore() {
		return this.useableScore;
	}
	public void setUseableScore(int useableScore) {
		this.useableScore = useableScore;
	}
	
	@Column(name = "is_auth")
	public int getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}

	@Column(name = "payType")
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "userId")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "token",length=50)
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "requestId",length=50)
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Column(name = "entity_code",length=50)
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	public CustomerManager getCustomerManager() {
		return this.customerManager;
	}
	public void setCustomerManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cusmemberid")
	public MemberInfo getMemberInfo() {
		return this.memberInfo;
	}
	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}
	@Column(name = "cusmembername", length = 355)
	public String getCusmembername() {
		return this.cusmembername;
	}
	public void setCusmembername(String cusmembername) {
		this.cusmembername = cusmembername;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<MemberInfo> getMemberInfos() {
		return this.memberInfos;
	}
	public void setMemberInfos(Set<MemberInfo> memberInfos) {
		this.memberInfos = memberInfos;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<BillLoan> getBillLoans() {
		return this.billLoans;
	}
	public void setBillLoans(Set<BillLoan> billLoans) {
		this.billLoans = billLoans;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<BillInvest> getBillInvests() {
		return this.billInvests;
	}
	public void setBillInvests(Set<BillInvest> billInvests) {
		this.billInvests = billInvests;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memberInfo")
	public Set<NotificationReaded> getNotificationReadeds() {
		return this.notificationReadeds;
	}
	public void setNotificationReadeds(Set<NotificationReaded> notificationReadeds) {
		this.notificationReadeds = notificationReadeds;
	}
}