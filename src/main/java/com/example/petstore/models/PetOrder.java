package com.example.petstore.models;

import java.util.Date;

import javax.persistence.*;

@Entity
public class PetOrder extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(optional=false) @MapsId
	private Pet pet;

	private int quantity;

	private Date shipDate;

	@Enumerated(EnumType.STRING)
	private StatusOrder status;

	private boolean complete;

	public PetOrder() {
	}

	public PetOrder(Pet pet, int quantity, Date shipDate, StatusOrder status, boolean complete) {
		super();
		this.pet = pet;
		this.quantity = quantity;
		this.shipDate = shipDate;
		this.status = status;
		this.complete = complete;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public StatusOrder getStatus() {
		return status;
	}

	public void setStatus(StatusOrder status) {
		this.status = status;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

}
