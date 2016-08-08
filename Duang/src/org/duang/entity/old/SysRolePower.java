package org.duang.entity.old;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SysRolePower entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_role_power", catalog = "duang")
public class SysRolePower implements java.io.Serializable {

	// Fields

	private String rolePowerId;
	private SysPower sysPower;
	private SysRole sysRole;

	// Constructors

	/** default constructor */
	public SysRolePower() {
	}

	/** full constructor */
	public SysRolePower(String rolePowerId, SysPower sysPower, SysRole sysRole) {
		this.rolePowerId = rolePowerId;
		this.sysPower = sysPower;
		this.sysRole = sysRole;
	}

	// Property accessors
	@Id
	@Column(name = "role_power_id", unique = true, nullable = false, length = 36)
	public String getRolePowerId() {
		return this.rolePowerId;
	}

	public void setRolePowerId(String rolePowerId) {
		this.rolePowerId = rolePowerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "power_id", nullable = false)
	public SysPower getSysPower() {
		return this.sysPower;
	}

	public void setSysPower(SysPower sysPower) {
		this.sysPower = sysPower;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

}