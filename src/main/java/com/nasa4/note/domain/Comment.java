package com.nasa4.note.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.nasa4.note.utils.PrettyTimeUtil;

import lombok.Data;

@Data
@Entity
public class Comment {
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "note_id")
	private Note note;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String content;
	
	@CreationTimestamp
	private Date createTime;
	
	public String prettyCreateTime() {
		return PrettyTimeUtil.format(getCreateTime());
	}
	
	public Comment() {
	}
	
	public Comment(String userId) {
		this.user = new User();
		this.user.setId(userId);
	}

}
