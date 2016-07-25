package org.duang.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysPower entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_power", catalog = "duang")
public class SysPower implements java.io.Serializable {

	// Fields

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7413186991393949927L;
	
	
	private String id;
	private String name;
	private String url;
	private String parentId;
	private Integer sortIndex;
	private Timestamp optionTime;
	private String icon;
	private String desc;

	// Constructors

	/** default constructor */
	public SysPower() {
	}

	/** minimal constructor */
	public SysPower(String id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
	}

	/** full constructor */
	public SysPower(String id, String name, String url, String parentId,
			Integer sortIndex, Timestamp optionTime, String icon, String desc) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.parentId = parentId;
		this.sortIndex = sortIndex;
		this.optionTime = optionTime;
		this.icon = icon;
		this.desc = desc;
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

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "url", nullable = false, length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "parent_id", length = 36)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "sort_index")
	public Integer getSortIndex() {
		return this.sortIndex;
	}

	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}

	@Column(name = "option_time", length = 19)
	public Timestamp getOptionTime() {
		return this.optionTime;
	}

	public void setOptionTime(Timestamp optionTime) {
		this.optionTime = optionTime;
	}

	@Column(name = "icon", length = 200)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "desc", length = 1000)
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}