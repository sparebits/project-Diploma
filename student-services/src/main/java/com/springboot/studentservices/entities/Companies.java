package com.springboot.studentservices.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;




@Entity
@Table(name = "Companies")
public class Companies {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "company_name",nullable=false)
	private String companyName;
	@Column(name = "bulstat", nullable = false)
	private String bulstat;
	@Column(name = "location",nullable = false)
	private String location;
	@Column(name="phone_number",nullable = false) 
	private String phoneNumber;
	@Column(name="email",nullable=false)
	private String email;
	@Column(name="password",nullable=false)
	private String password;
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "companies_stock",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "stock_id") })
    private Set<Stock> stocks = new HashSet<>();


	public Companies() {
		super();
	}

	public Companies(String companyName, String bulstat,String location,String phoneNumber,String email,String password) {
		super();
		this.companyName = companyName;
		this.bulstat = bulstat;
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.email=email;
		this.password=password;
	}

	public Set<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBulstat() {
		return bulstat;
	}

	public void setBulstat(String bulstat) {
		this.bulstat = bulstat;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation (String location) {
		this.location = location;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Company: [id=%s, Company Name=%s, Bulstat=%s,Location=%s,Phone Number=%s,Email=%s]", id, companyName, bulstat,location,phoneNumber,email);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
