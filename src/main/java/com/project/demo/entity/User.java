package com.project.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.project.demo.validation.Gmail;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uId;

    @NotNull
    private String uName;
    
	@Column(unique = true, nullable = false)
	@NotNull
    private String uMail;

	@NotNull
    private String uPass;

	public User() {
	}

	public User(String uMail, String uPass) {
		super();
		this.uMail = uMail;
		this.uPass = uPass;
	}
	
	public User(Long uId, String uMail, String uPass) {
		super();
		this.uId = uId;
		this.uMail = uMail;
		this.uPass = uPass;
	}

	public User(String uName, String uMail, String uPass) {
		super();
		this.uName = uName;
		this.uMail = uMail;
		this.uPass = uPass;
	}

	public User(Long uId, String uName, String uMail, String uPass) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.uMail = uMail;
		this.uPass = uPass;
	}

	public Long getUid() {
		return uId;
	}

	public void setUid(Long uId) {
		this.uId = uId;
	}
	
	public String getUname() {
		return uName;
	}

	public void setUname(String uName) {
		this.uName = uName;
	}

	public String getUmail() {
		return uMail;
	}

	public void setUmail(String uMail) {
		this.uMail = uMail;
	}

	public String getUpass() {
		return uPass;
	}

	public void setUpass(String uPass) {
		this.uPass = uPass;
	}

	@Override
	public String toString() {
		return "User [uid=" + uId + ", uname=" + uName + ", umail=" + uMail + ", upass=" + uPass + "]";
	}
	
}

