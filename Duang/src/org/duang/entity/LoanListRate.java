package org.duang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

/**
 * 借贷基数配置
 * @ClassName:  LoanListRate   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年12月20日 上午10:32:43
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "loan_list_rate", catalog = "duang")
@DynamicInsert(true)
public class LoanListRate implements java.io.Serializable {

	// Fields

	private String id;
	private double platformRate;
	private double handRate;
	private double loanRate;
	private double overRate;
    private double fastLoanMaxSum;
	/** default constructor */
	public LoanListRate() {
	}

	/** minimal constructor */
	public LoanListRate(String id) {
		this.id = id;
	}
	

	public LoanListRate(String id, double platformRate, double handRate, double loanRate, double overRate, double fastLoanMaxSum) {
		super();
		this.id = id;
		this.platformRate = platformRate;
		this.handRate = handRate;
		this.loanRate = loanRate;
		this.overRate = overRate;
		this.fastLoanMaxSum = fastLoanMaxSum;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "platform_rate")
	public double getPlatformRate() {
		return platformRate;
	}

	public void setPlatformRate(double platformRate) {
		this.platformRate = platformRate;
	}

	@Column(name = "hand_rate")
	public double getHandRate() {
		return handRate;
	}

	public void setHandRate(double handRate) {
		this.handRate = handRate;
	}

	@Column(name = "loan_rate")
	public double getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(double loanRate) {
		this.loanRate = loanRate;
	}

	@Column(name = "over_rate")
	public double getOverRate() {
		return overRate;
	}
	public void setOverRate(double overRate) {
		this.overRate = overRate;
	}
	
	@Column(name = "fast_loan_max_sum")
	public double getFastLoanMaxSum() {
		return fastLoanMaxSum;
	}
	public void setFastLoanMaxSum(double fastLoanMaxSum) {
		this.fastLoanMaxSum = fastLoanMaxSum;
	}
	
}