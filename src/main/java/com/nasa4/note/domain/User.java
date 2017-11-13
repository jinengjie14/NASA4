package com.nasa4.note.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = -8688117244346455153L;

	@Id
	private String id;
	
	@JSONField(serialize = false)
	private String password;
	private String account;
	
	@CreationTimestamp
	private Date createTime;

	public User() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
