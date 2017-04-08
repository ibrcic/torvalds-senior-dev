package com.ist.services.rest.pojo;

import java.sql.Connection;
import java.sql.SQLException;

public class User {
	long borrowerId;
	String userName;
	String email;
	long majorId;
	String majorTitle;
	String majorAbbr;
	long classId;
	String classTitle;
	String className;
	int section;
	int flagged;

	public User(long borrowerId, String userName, String email, long majorId, String majorTitle, String majorAbbr,
			int flagged) {
		this.borrowerId = borrowerId;
		this.userName = userName;
		this.email = email;
		this.majorId = majorId;
		this.majorTitle = majorTitle;
		this.majorAbbr = majorAbbr;
		this.flagged = flagged;
	}

	public User(long borrowerId, String userName, String email, long majorId, String majorTitle, String majorAbbr,
			long classId, String classTitle, String className, int section, int flagged) {
		this.borrowerId = borrowerId;
		this.userName = userName;
		this.email = email;
		this.majorId = majorId;
		this.majorTitle = majorTitle;
		this.majorAbbr = majorAbbr;
		this.classId = classId;
		this.classTitle = classTitle;
		this.className = className;
		this.section = section;
		this.flagged = flagged;
	}

	public User(long borrowerId) {
		this.borrowerId = borrowerId;
	}

	public Long getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(long borrowerId) {
		this.borrowerId = borrowerId;
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

	public long getMajorId() {
		return majorId;
	}

	public void setMajorId(long majorId) {
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

	public void setBorrowerId(String majorAbbr) {
		this.majorAbbr = majorAbbr;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
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

	protected User sync(Connection conn) throws SQLException {
		return null;
	}

	public void fetch(Connection conn) throws SQLException {

	}

}
