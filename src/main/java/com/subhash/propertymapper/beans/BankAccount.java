package com.subhash.propertymapper.beans;

public class BankAccount {

	private Long id;

	private String name;

	private Long accountNumber;

	private Integer accountType;

	private Student student;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "BankAccount [ id: " + id + ", Name: " + name
				+ ", AccountNumber: " + accountNumber + ", AccountType: "
				+ accountType + "]";
	}
}
