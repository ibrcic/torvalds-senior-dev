package com.ist.services.rest.pojo;

import java.sql.Date;

public class User {
	long userId;
	String userName;
	String email;
	String password;
	long majorId;
	String majorTitle;
	String majorAbbr;
	long classId;
	String classTitle;
	String className;
	long sectionId;
	int section;
	int flagged;
	int privilegeId;
	String privilegeName;
	Long offenseId;
	String offenseName;
	String offenseDescription;
	Date offenseDate;
	long rentalId;
	long itemId;

	public Long getOffenseId() {
		return offenseId;
	}

	public void setOffenseId(Long offenseId) {
		this.offenseId = offenseId;
	}

	public String getOffenseDescription() {
		return offenseDescription;
	}

	public void setOffenseDescription(String offenseDescription) {
		this.offenseDescription = offenseDescription;
	}

	public Date getOffenseDate() {
		return offenseDate;
	}

	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}

	public String getOffenseName() {
		return offenseName;
	}

	public void setOffenseName(String offenseName) {
		this.offenseName = offenseName;
	}

	public Long getRentalId() {
		return rentalId;
	}

	public void setRentalId(Long rentalId) {
		this.rentalId = rentalId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public int getPriviledgeId() {
		return privilegeId;
	}

	public void setPriviledgeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPriviledgeName() {
		return privilegeName;
	}

	public void setPriviledgeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getMajorId() {
		return majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public String getMajorTitle() {
		return majorTitle;
	}

	public void setMajorTitle(String majorTitle) {
		this.majorTitle = majorTitle;
	}

	public String getMajorAbbr() {
		return majorAbbr;
	}

	public void setMajorAbbr(String majorAbbr) {
		this.majorAbbr = majorAbbr;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getClassTitle() {
		return classTitle;
	}

	public void setClassTitle(String classTitle) {
		this.classTitle = classTitle;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public int getFlagged() {
		return flagged;
	}

	public void setFlagged(int flagged) {
		this.flagged = flagged;
	}

}
