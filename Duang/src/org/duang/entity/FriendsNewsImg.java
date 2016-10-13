package org.duang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FriendsNewsImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "friends_news_img", catalog = "duang")
public class FriendsNewsImg implements java.io.Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	private String id;
	private FriendsNews friendsNews;
	private String imgPath;
	private Integer orderIndex;

	// Constructors

	/** default constructor */
	public FriendsNewsImg() {
	}

	/** minimal constructor */
	public FriendsNewsImg(String id) {
		this.id = id;
	}

	/** full constructor */
	public FriendsNewsImg(String id, FriendsNews friendsNews, String imgPath, Integer orderIndex) {
		this.id = id;
		this.friendsNews = friendsNews;
		this.imgPath = imgPath;
		this.orderIndex = orderIndex;
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
	@JoinColumn(name = "friends_news_id")
	public FriendsNews getFriendsNews() {
		return this.friendsNews;
	}

	public void setFriendsNews(FriendsNews friendsNews) {
		this.friendsNews = friendsNews;
	}

	@Column(name = "img_path", length = 1000)
	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Column(name = "order_index")
	public Integer getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

}