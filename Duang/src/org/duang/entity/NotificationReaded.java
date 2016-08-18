package org.duang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;


/**
 * NotificationReaded entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name="notification_readed"
    ,catalog="duang"
)
@DynamicInsert(true)
public class NotificationReaded  implements java.io.Serializable {


    // Fields    

     private String id;
     private Notification notification;
     private MemberInfo memberInfo;


    // Constructors

    /** default constructor */
    public NotificationReaded() {
    }

	/** minimal constructor */
    public NotificationReaded(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public NotificationReaded(String id, Notification notification, MemberInfo memberInfo) {
        this.id = id;
        this.notification = notification;
        this.memberInfo = memberInfo;
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
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="notification")

    public Notification getNotification() {
        return this.notification;
    }
    
    public void setNotification(Notification notification) {
        this.notification = notification;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="member_info")

    public MemberInfo getMemberInfo() {
        return this.memberInfo;
    }
    
    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }
   








}