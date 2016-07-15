package org.duang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Englist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "englist", catalog = "myenglist")
public class English implements java.io.Serializable {

	// Fields

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;



	private Integer wordId;
	private String chinese;
	private String english;
	private String wordClass;
	private String wordType;
	private Integer errorCount;
	private Integer rightCount;
	private String percent;
	private String moxie;

	// Constructors

	/** default constructor */
	public English() {
	}

	/** full constructor */
	public English(String chinese, String english, String wordClass,
			String wordType, Integer errorCount, Integer rightCount,
			String percent, String moxie) {
		this.chinese = chinese;
		this.english = english;
		this.wordClass = wordClass;
		this.wordType = wordType;
		this.errorCount = errorCount;
		this.rightCount = rightCount;
		this.percent = percent;
		this.moxie = moxie;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "WordId", unique = true, nullable = false)
	public Integer getWordId() {
		return this.wordId;
	}

	public void setWordId(Integer wordId) {
		this.wordId = wordId;
	}

	@Column(name = "Chinese", length = 100)
	public String getChinese() {
		return this.chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	@Column(name = "English", length = 100)
	public String getEnglish() {
		return this.english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	@Column(name = "WordClass", length = 100)
	public String getWordClass() {
		return this.wordClass;
	}

	public void setWordClass(String wordClass) {
		this.wordClass = wordClass;
	}

	@Column(name = "WordType", length = 100)
	public String getWordType() {
		return this.wordType;
	}

	public void setWordType(String wordType) {
		this.wordType = wordType;
	}

	@Column(name = "ErrorCount")
	public Integer getErrorCount() {
		return this.errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	@Column(name = "RightCount")
	public Integer getRightCount() {
		return this.rightCount;
	}

	public void setRightCount(Integer rightCount) {
		this.rightCount = rightCount;
	}

	@Column(name = "percent", length = 20)
	public String getPercent() {
		return this.percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	@Column(name = "moxie", length = 55)
	public String getMoxie() {
		return this.moxie;
	}

	public void setMoxie(String moxie) {
		this.moxie = moxie;
	}

}