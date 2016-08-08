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
 * SysRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role", catalog = "duang")
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SysRole implements java.io.Serializable {

	// Fields

	private String id;
	private String roleName;
	private String roleDesc;
	private Date optionTime;
	private Set<SysRolePower> sysRolePowers = new HashSet<SysRolePower>(0);
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** minimal constructor */
	public SysRole(String id, String roleName, Date optionTime) {
		this.id = id;
		this.roleName = roleName;
		this.optionTime = optionTime;
	}

	/** full constructor */
	public SysRole(String id, String roleName, String roleDesc,
			Date optionTime, Set<SysRolePower> sysRolePowers,
			Set<SysUser> sysUsers) {
		this.id = id;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.optionTime = optionTime;
		this.sysRolePowers = sysRolePowers;
		this.sysUsers = sysUsers;
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

	@Column(name = "role_name", nullable = false, length = 100)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "role_desc", length = 1000)
	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name = "option_time", nullable = false, length = 19)
	public Date getOptionTime() {
		return this.optionTime;
	}

	public void setOptionTime(Date optionTime) {
		this.optionTime = optionTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRole")
	public Set<SysRolePower> getSysRolePowers() {
		return this.sysRolePowers;
	}

	public void setSysRolePowers(Set<SysRolePower> sysRolePowers) {
		this.sysRolePowers = sysRolePowers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRole")
	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

}