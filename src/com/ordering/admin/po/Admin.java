package com.ordering.admin.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(length=50,nullable=false)
	private String aid;
	@Column(length=50,nullable=false)
	private String aname;
	@Column(length=50,nullable=false)
	private String apassword;
	@Column(length=50,nullable=false)
	private String aemail;

	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}

	public String getAemail() {
		return aemail;
	}
	public void setAemail(String aemail) {
		this.aemail = aemail;
	}

	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", aname=" + aname + ", apassword="
				+ apassword + ", aemail=" + aemail + "]";
	}

}
