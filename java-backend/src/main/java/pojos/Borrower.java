package pojos;

import java.util.Collection;

/**
 * 
 */

/**
 * @author Noah
 * @version 0.1.1
 */
public class Borrower {
	int borrowerId;
	Collection<Major> majors;
	Collection<ClassSection> classSections;
	Collection<Rental> rentals;
	Collection<Reservation> reservations;
	String username;
	boolean flagged;
	
	
	/**
	 * @param borrowerId
	 * @param majors
	 * @param classSections
	 * @param rentals
	 * @param reservations
	 * @param username
	 * @param flagged
	 */
	public Borrower(int borrowerId, Collection<Major> majors, Collection<ClassSection> classSections,
			Collection<Rental> rentals, Collection<Reservation> reservations, String username, Boolean flagged) {
		this.borrowerId = borrowerId;
		this.majors = majors;
		this.classSections = classSections;
		this.rentals = rentals;
		this.reservations = reservations;
		this.username = username;
		this.flagged = flagged;
	}
	
	/**
	 * @param borrowerId
	 */
	public Borrower(int borrowerId) {
		this.borrowerId = borrowerId;
		sync();
	}
	 
	private void sync() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the borrowerId
	 */
	protected int getBorrowerId() {
		return borrowerId;
	}
	/**
	 * @return the majors
	 */
	protected Collection<Major> getMajors() {
		return majors;
	}
	/**
	 * @return the classSections
	 */
	protected Collection<ClassSection> getClassSections() {
		return classSections;
	}
	/**
	 * @return the rentals
	 */
	protected Collection<Rental> getRentals() {
		return rentals;
	}
	/**
	 * @return the reservations
	 */
	protected Collection<Reservation> getReservations() {
		return reservations;
	}
	/**
	 * @return the username
	 */
	protected String getUsername() {
		return username;
	}
	/**
	 * @return the flagged
	 */
	protected Boolean getFlagged() {
		return flagged;
	}
	/**
	 * @param borrowerId the borrowerId to set
	 */
	protected void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}
	/**
	 * @param majors the majors to set
	 */
	protected void setMajors(Collection<Major> majors) {
		this.majors = majors;
	}
	/**
	 * @param classSections the classSections to set
	 */
	protected void setClassSections(Collection<ClassSection> classSections) {
		this.classSections = classSections;
	}
	/**
	 * @param rentals the rentals to set
	 */
	protected void setRentals(Collection<Rental> rentals) {
		this.rentals = rentals;
	}
	/**
	 * @param reservations the reservations to set
	 */
	protected void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}
	/**
	 * @param username the username to set
	 */
	protected void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param flagged the flagged to set
	 */
	protected void setFlagged(Boolean flagged) {
		this.flagged = flagged;
	}
	
	
	
	
	
	
}
