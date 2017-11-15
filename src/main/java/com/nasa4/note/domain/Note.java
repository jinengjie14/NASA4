package com.nasa4.note.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.nasa4.note.utils.PrettyTimeUtil;

import lombok.Data;

@Data
@Entity
public class Note {
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "note", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy(value = "create_time DESC")
	private List<Comment> comments = new ArrayList<>();
	
	private String title;
	private String link;
	@Column(columnDefinition="text")
	private String content;
	@Column(columnDefinition="text")
	private String description;
	private String image;
	private Integer commentCount;
	
	@CreationTimestamp
	private Date createTime;
	@UpdateTimestamp
	private Date updateTime;
	
	public String prettyCreateTime() {
		return PrettyTimeUtil.format(getCreateTime());
	}
	
	public String prettyUpdateTime() {
		return PrettyTimeUtil.format(getUpdateTime());
	}
	
	public Note() {
	}
	
	public Note(String userId) {
		this.user = new User();
		this.user.setId(userId);
		this.commentCount = 0;
	}
	
}
