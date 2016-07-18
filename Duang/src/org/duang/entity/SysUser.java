package org.duang.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="sys_user"
,catalog="duang"
		)

public class SysUser  implements java.io.Serializable {


	// Fields    

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6672777832415788812L;


	private String id;
	private String name;
	private String password;
	private String email;


	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** minimal constructor */
	public SysUser(String id) {
		this.id = id;
	}

	/** full constructor */
	public SysUser(String id, String name, String password, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}


	// Property accessors
	@Id 
	@Column(name="id", unique=true, nullable=false, length=36)
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name="name", length=100)
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="password", length=200)
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="email", length=50)
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}