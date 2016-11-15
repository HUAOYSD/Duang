package org.duang.entity;

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
 * Award entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "award", catalog = "duang")
public class Award implements java.io.Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String description;
	private String state;
	private Set<AwardActivityLevel> awardActivityLevels = new HashSet<AwardActivityLevel>(0);

	// Constructors

	/** default constructor */
	public Award() {
	}

	/** minimal constructor */
	public Award(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public Award(String id, String name, String description, String state, Set<AwardActivityLevel> awardActivityLevels) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.state = state;
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

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "award")
	public Set<AwardActivityLevel> getAwardActivityLevels() {
		return this.awardActivityLevels;
	}

	public void setAwardActivityLevels(Set<AwardActivityLevel> awardActivityLevels) {
		this.awardActivityLevels = awardActivityLevels;
	}

}