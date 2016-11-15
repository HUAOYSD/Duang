package org.duang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AwardActivityLevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "award_activity_level", catalog = "duang")
public class AwardActivityLevel implements java.io.Serializable {
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	private String id;
	private AwardActivity awardAtivity;
	private Award award;
	private String title;
	private int awardNum;
	private int odds;
	private String userId;
	private int winNumber;
	private int onceNum;
	private String winCode;
	// Constructors

	/** default constructor */
	public AwardActivityLevel() {
	}

	/** minimal constructor */
	public AwardActivityLevel(String id) {
		this.id = id;
	}

	/** full constructor */
	public AwardActivityLevel(String id, AwardActivity awardAtivity, Award award, String title,int onceNum, int awardNum, int odds, 
			String userId, int winNumber,String winCode) {
		this.id = id;
		this.awardAtivity = awardAtivity;
		this.award = award;
		this.title = title;
		this.onceNum = onceNum;
		this.awardNum = awardNum;
		this.odds = odds;
		this.userId = userId;
		this.winNumber = winNumber;
		this.winCode = winCode;
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
	@JoinColumn(name = "activityId")
	public AwardActivity getAwardAtivity() {
		return this.awardAtivity;
	}

	public void setAwardAtivity(AwardActivity awardAtivity) {
		this.awardAtivity = awardAtivity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "awardId")
	public Award getAward() {
		return this.award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "winCode")
	public String getWinCode() {
		return this.winCode;
	}

	public void setWinCode(String winCode) {
		this.winCode = winCode;
	}
	
	@Column(name = "onceNum")
	public int getOnceNum() {
		return this.onceNum;
	}

	public void setOnceNum(int onceNum) {
		this.onceNum = onceNum;
	}

	@Column(name = "awardNum")
	public int getAwardNum() {
		return this.awardNum;
	}

	public void setAwardNum(int awardNum) {
		this.awardNum = awardNum;
	}
	
	@Column(name = "odds")
	public int getOdds() {
		return this.odds;
	}

	public void setOdds(int odds) {
		this.odds = odds;
	}

	@Column(name = "userId", length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "winNumber")
	public int getWinNumber() {
		return this.winNumber;
	}

	public void setWinNumber(int winNumber) {
		this.winNumber = winNumber;
	}

}