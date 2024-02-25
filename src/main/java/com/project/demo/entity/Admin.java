package com.project.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.project.demo.validation.Gmail;

@Entity
@Table(name = "admins")
public class Admin {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aId;

    @NotNull
    private String aName;
    
	@Column(unique = true, nullable = false)
	@NotNull
    private String aMail;

	@NotNull
    private String aPass;
	
	public Admin() {
	}

	public Admin(String aMail, String aPass) {
		super();
		this.aMail = aMail;
		this.aPass = aPass;
	}

	public Admin(Long aId, String aName, String aMail, String aPass) {
		super();
		this.aId = aId;
		this.aName = aName;
		this.aMail = aMail;
		this.aPass = aPass;
	}

	public Long getAid() {
		return aId;
	}

	public void setAid(Long aId) {
		this.aId = aId;
	}

	public String getAname() {
		return aName;
	}

	public void setAname(String aName) {
		this.aName = aName;
	}

	public String getAmail() {
		return aMail;
	}

	public void setAmail(String aMail) {
		this.aMail = aMail;
	}

	public String getApass() {
		return aPass;
	}

	public void setApass(String aPass) {
		this.aPass = aPass;
	}

	@Override
	public String toString() {
		return "Admin [aid=" + aId + ", aname=" + aName + ", amail=" + aMail + ", apass=" + aPass + "]";
	}
	
}
