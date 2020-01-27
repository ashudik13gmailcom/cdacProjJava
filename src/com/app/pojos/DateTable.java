package com.app.pojos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class DateTable {
	
	private Integer dateId;
	private String eventName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date eventDate;
	
	public DateTable() {
		
	}

	public DateTable(String eventName, Date eventDate) {
		super();
		this.eventName = eventName;
		this.eventDate = eventDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getDateId() {
		return dateId;
	}

	public void setDateId(Integer dateId) {
		this.dateId = dateId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Temporal(value = TemporalType.DATE)
	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		return "DateTable [dateId=" + dateId + ", eventName=" + eventName + ", eventDate=" + eventDate + "]";
	}
	
	
	

}
