package com.maxtrain.bootcamp.prs.lineitem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import com.maxtrain.bootcamp.prs.product.Product;
import com.maxtrain.bootcamp.prs.request.Request;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UIDX_request_product", columnNames = { "requestId", "productId" }))
public class LineItem {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "RequestId")
	private Request request;
	@ManyToOne(optional = false)
	@JoinColumn(name = "ProductId")
	private Product product;
	private int quantity;

	public LineItem(int id, Request request, Product product, int quantity) {
		super();

		this.id = id;
		this.request = request;
		this.product = product;
		this.quantity = quantity;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LineItem() {
	}

	@Override
	public String toString() {
		return "LineItem [id=" + id + ", request=" + request + ", product=" + product + ", quantity=" + quantity + "]";
	}

	
}
