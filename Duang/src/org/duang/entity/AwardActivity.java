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
 * AwardAtivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "award_activity", catalog = "duang")
public class AwardActivity implements java.io.Serializable {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private int isRepeat;
	private int repeatNum;
	private Date startTime;
	private Date endTime;
	private Date createTime;
	private int nowNumber;
	private int winNumber;
	private String code;
	private int useScore;
	private Set<AwardActivityLevel> awardActivityLevels = new HashSet<AwardActivityLevel>(0);

	// Constructors

	/** default constructor */
	public AwardActivity() {
	}

	/** minimal constructor */
	public AwardActivity(String id) {
		this.id = id;
	}

	/** full constructor */
	public AwardActivity(String id, String code, String title, int isRepeat, int repeatNum, Date startTime, Date endTime, 
			Date createTime, int nowNumber, int winNumber, Set<AwardActivityLevel> awardActivityLevels,int useScore) {
		this.id = id;
		this.title = title;
		this.code = code;
		this.isRepeat = isRepeat;
		this.repeatNum = repeatNum;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.nowNumber = nowNumber;
		this.winNumber = winNumber;
		this.useScore = useScore;
		this.awardActivityLevels = awardActivityLevels;
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

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "isRepeat")
	public int getIsRepeat() {
		return this.isRepeat;
	}

	public void setIsRepeat(int isRepeat) {
		this.isRepeat = isRepeat;
	}

	@Column(name = "repeatNum")
	public int getRepeatNum() {
		return this.repeatNum;
	}

	public void setRepeatNum(int repeatNum) {
		this.repeatNum = repeatNum;
	}

	@Column(name = "startTime", length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "createTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "nowNumber")
	public int getNowNumber() {
		return this.nowNumber;
	}

	public void setNowNumber(int nowNumber) {
		this.nowNumber = nowNumber;
	}

	@Column(name = "winNumber")
	public int getWinNumber() {
		return this.winNumber;
	}

	public void setWinNumber(int winNumber) {
		this.winNumber = winNumber;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "awardAtivity")
	public Set<AwardActivityLevel> getAwardActivityLevels() {
		return this.awardActivityLevels;
	}

	public void setAwardActivityLevels(Set<AwardActivityLevel> awardActivityLevels) {
		this.awardActivityLevels = awardActivityLevels;
	}

	@Column(name = "useScore")
	public int getUseScore() {
		return this.useScore;
	}

	public void setUseScore(int useScore) {
		this.useScore = useScore;
	}

}