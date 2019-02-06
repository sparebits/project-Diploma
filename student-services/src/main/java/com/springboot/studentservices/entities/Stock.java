package com.springboot.studentservices.entities;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Stock")
public class Stock {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "stock_name",nullable=false)
	private String stockName;
	@Column(name = "stock_description", nullable = false)
	private String stockDesc;
	@Column(name = "measuring_units",nullable = false)
	private String measuringUnits;
	@Column(name="quantity",nullable = false) 
	private String quantity;
	@Column(name="price_per_item",nullable=false)
	private float pricePerItem;
	
	
	public Stock() {
		super();
	}

	public Stock(String stockName, String stockDesc,String measuringUnits,String quantity,float pricePerItem) {
		super();
		this.stockName = stockName;
		this.stockDesc = stockDesc;
		this.measuringUnits = measuringUnits;
		this.quantity = quantity;
		this.pricePerItem = pricePerItem;
	}
	
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "stocks")
    private Set<Companies> companies = new HashSet<>();
	public Set<Companies> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Companies> companies) {
		this.companies = companies;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockDesc() {
		return stockDesc;
	}
	public void setStockDesc(String stockDesc) {
		this.stockDesc = stockDesc;
	}
	public String getMeasuringUnits() {
		return measuringUnits;
	}
	public void setMeasuringUnits(String measuringUnits) {
		this.measuringUnits = measuringUnits;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public float getPricePerItem() {
		return pricePerItem;
	}
	public void setPricePerItem(float pricePerItem) {
		this.pricePerItem = pricePerItem;
	}
	
	@Override
	public String toString() {
		return String.format("Company: [id=%s, Stock Name=%s, Stock Description=%s,Measuring Units=%s,Quantity=%s,Price Per Item=%s]", id, stockName, measuringUnits,quantity,pricePerItem);
	}
	
}
