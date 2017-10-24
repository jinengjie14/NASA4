package com.nasa4.note.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class Note {
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	private String userId;
	private String title;
	private String link;
	@Column(columnDefinition="text")
	private String content;
	private String description;
	private String image;
	
	@CreationTimestamp
	private Date createTime;
	@UpdateTimestamp
	private Date updateTime;
	
	public Note() {
	}
	
	public Note(String userId) {
		this.userId = userId;
	}
	
}
