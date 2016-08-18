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
 * Notification entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name="notification"
    ,catalog="duang"
)
@DynamicInsert(true)
public class Notification  implements java.io.Serializable {


    // Fields    

     private String id;
     private int status;
     private Date publishTime;
     private String title;
     private String content;
     private Date beginDate;
     private Date endDate;
     private Set<NotificationReaded> notificationReadeds = new HashSet<NotificationReaded>(0);


    // Constructors

    /** default constructor */
    public Notification() {
    }

	/** minimal constructor */
    public Notification(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public Notification(String id, int status, Date publishTime, String title, String content, Date beginDate, Date endDate, Set<NotificationReaded> notificationReadeds) {
        this.id = id;
        this.status = status;
        this.publishTime = publishTime;
        this.title = title;
        this.content = content;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.notificationReadeds = notificationReadeds;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="id", unique=true, nullable=false, length=1)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="status")

    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    @Column(name="publish_time", length=19)

    public Date getPublishTime() {
        return this.publishTime;
    }
    
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    
    @Column(name="title", length=3000)

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="content", length=16777215)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="begin_date", length=19)

    public Date getBeginDate() {
        return this.beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    
    @Column(name="end_date", length=19)

    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="notification")

    public Set<NotificationReaded> getNotificationReadeds() {
        return this.notificationReadeds;
    }
    
    public void setNotificationReadeds(Set<NotificationReaded> notificationReadeds) {
        this.notificationReadeds = notificationReadeds;
    }
   








}