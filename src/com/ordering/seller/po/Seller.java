package com.ordering.seller.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length=50,nullable=false)
	private String sid;      //商家编号
	@Column(length=50,nullable=false)
	private String salename; //商家名称
	@Column(length=50,nullable=false)
	private String password; //商家密码
	@Column(length=50,nullable=false)
	private String email;    //商家邮箱
	@Column(length=50,nullable=false)
	private String date;     //商家成立时间
	@Column(length=200,nullable=false)
	private String verfiyCard; //商家身份证明
	@Column(length=150,nullable=false)
	private String address;    //商家地址
	@Column(length=150,nullable=false)
	private String types;    //商家主营类型
	@Column(length=50,nullable=false)
	private String perfect;    //商家推荐
	@Column(length=50,nullable=false)
	private String phone;      //商家联系电话
	@Column(length=200,nullable=false)
	private String salefile;   //商家营业执照
	@Column(length=200,nullable=false)
	private String safefile;   //商家卫生执照
	@Column(length=50,nullable=false)
	private String states;	   //商家规模
	
	@Column(length=50,nullable=false)
	private String activeCode; //邮箱激活校验码
	@Column(length=10,nullable=false)
	private String emailActive;//邮箱激活校验位
	/**
	 * 审核位
	 * 0：未审核；
	 * 1：审核通过；
	 * 2：审核未过；
	 */
	@Column(length=10,nullable=false)
	private String AccpetActive;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSalename() {
		return salename;
	}
	public void setSalename(String salename) {
		this.salename = salename;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVerfiyCard() {
		return verfiyCard;
	}
	public void setVerfiyCard(String verfiyCard) {
		this.verfiyCard = verfiyCard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getPerfect() {
		return perfect;
	}
	public void setPerfect(String perfect) {
		this.perfect = perfect;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSalefile() {
		return salefile;
	}
	public void setSalefile(String salefile) {
		this.salefile = salefile;
	}
	public String getSafefile() {
		return safefile;
	}
	public void setSafefile(String safefile) {
		this.safefile = safefile;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	public String getEmailActive() {
		return emailActive;
	}
	public void setEmailActive(String emailActive) {
		this.emailActive = emailActive;
	}
	public String getAccpetActive() {
		return AccpetActive;
	}
	public void setAccpetActive(String accpetActive) {
		AccpetActive = accpetActive;
	}
	
	@Override
	public String toString() {
		return "Seller [sid=" + sid + ", salename=" + salename + ", password="
				+ password + ", email=" + email + ", date=" + date
				+ ", verfiyCard=" + verfiyCard + ", address=" + address
				+ ", types=" + types + ", perfect=" + perfect + ", phone="
				+ phone + ", salefile=" + salefile + ", safefile=" + safefile
				+ ", states=" + states + ", activeCode=" + activeCode
				+ ", emailActive=" + emailActive + ", AccpetActive="
				+ AccpetActive + "]";
	}

}
