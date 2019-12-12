package com.maxtrain.bootcamp.prs.product;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.maxtrain.bootcamp.prs.vendor.Vendor;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UIDX_partNumber", columnNames = { "partNumber" }))
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "VendorId")
	private Vendor vendor;
	@Column(length = 50, nullable = false)
	private String partNumber;
	@Column(length = 150, nullable = false)
	private String name;
	@Column(columnDefinition = "decimal(10,2) NOT NULL DEFAULT 0.0")
	private double price;
	@Column(length = 250, nullable = true)
	private String unit;
	@Column(length = 250, nullable = true)
	private String photopath;

	public Product(int id, Vendor vendor, String partNumber, String name, double price, String unit, String photopath) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.partNumber = partNumber;
		this.name = name;
		this.unit = unit;
		this.photopath = photopath;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vendor getVendorId() {
		return vendor;
	}

	public void setVendorId(Vendor vendorId) {
		this.vendor = vendorId;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", vendorId=" + ", partNumber=" + partNumber + ", name=" + name
				+ ", price=" + price + ", unit=" + unit + ", photopath=" + photopath + "]";
	}

	public Product() {
	}

}
