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
	private String idCardImg2;
	private String miDescribe;
	private String isdelete;
	private Date createTime;
	private Date modifyTime;
	private String createuser;
	private String modifyuser;
	private String userImg;
	private int isEliteAccount;
	private String type;
	private String level;
	private String price;
	private String password;
	private String payPassword;
	private String handPassword;
	private int isFreeze;
	private String myQr;
	private Set<MemberExtraInfo> memberExtraInfos = new HashSet<MemberExtraInfo>(0);
	private Set<Friends> friendsesForTarget = new HashSet<Friends>(0);
	private Set<Friends> friendsesForSelf = new HashSet<Friends>(0);
	private Set<LoanMember> loanMembers = new HashSet<LoanMember>(0);
	private Set<Message> messagesForReceiver = new HashSet<Message>(0);
	private Set<BindCard> bindCards = new HashSet<BindCard>(0);
	private Set<InvestMember> investMembers = new HashSet<InvestMember>(0);
	private Set<ScoreList> scoreLists = new HashSet<ScoreList>(0);
	private Set<Message> messagesForSender = new HashSet<Message>(0);

	// Constructors

	/** default constructor */
	public MemberInfo() {
	}

	/** minimal constructor */
	public MemberInfo(String id, String loginName) {
		this.id = id;
		this.loginName = loginName;
	}

	/** full constructor */
	public MemberInfo(String id, String loginName, String realName,
			String nickname, String email, String age, String sex,
			String phone, String idCard, String idCardImg1, String idCardImg2,
			String miDescribe, String isdelete, Date createTime,
			Date modifyTime, String createuser, String modifyuser,
			String userImg, int isEliteAccount, String type, String level,
			String price, String password, String payPassword,
			String handPassword, int isFreeze, String myQr,
			Set<MemberExtraInfo> memberExtraInfos,
			Set<Friends> friendsesForTarget, Set<Friends> friendsesForSelf,
			Set<LoanMember> loanMembers, Set<Message> messagesForReceiver,
			Set<BindCard> bindCards, Set<InvestMember> investMembers,
			Set<ScoreList> scoreLists, Set<Message> messagesForSender) {
		this.id = id;
		this.loginName = loginName;
		this.realName = realName;
		this.nickname = nickname;
		this.email = email;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
		this.idCard = idCard;
		this.idCardImg1 = idCardImg1;
		this.idCardImg2 = idCardImg2;
		this.miDescribe = miDescribe;
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

	@Column(name = "id_card_img2", length = 500)
	public String getIdCardImg2() {
		return this.idCardImg2;
	}

	public void setIdCardImg2(String idCardImg2) {
		this.idCardImg2 = idCardImg2;
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

	@Column(name = "type", length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

}