package com.mycompany.myapp.dto;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

public class Member {
	
	private String mid;
	private String mpassword;
	private String mname;
	private String mtel;
	private String memail;
	private String madderss;
	private Date mbirth;
	private Date mdate;
	private String moriginalimgname;
	private String msavedimgname;
	private String mimgtype;
	private MultipartFile mattach;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpassword() {
		return mpassword;
	}
	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMtel() {
		return mtel;
	}
	public void setMtel(String mtel) {
		this.mtel = mtel;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMadderss() {
		return madderss;
	}
	public void setMadderss(String madderss) {
		this.madderss = madderss;
	}
	public String getMoriginalimgname() {
		return moriginalimgname;
	}
	public void setMoriginalimgname(String moriginalimgname) {
		this.moriginalimgname = moriginalimgname;
	}
	public String getMsavedimgname() {
		return msavedimgname;
	}
	public void setMsavedimgname(String msavedimgname) {
		this.msavedimgname = msavedimgname;
	}
	public String getMimgtype() {
		return mimgtype;
	}
	public void setMimgtype(String mimgtype) {
		this.mimgtype = mimgtype;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public MultipartFile getMattach() {
		return mattach;
	}
	public void setMattach(MultipartFile mattach) {
		this.mattach = mattach;
	}
	public Date getMbirth() {
		return mbirth;
	}
	public void setMbirth(Date mbirth) {
		this.mbirth = mbirth;
	}
	
	
}
