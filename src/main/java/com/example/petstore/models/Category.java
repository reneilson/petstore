package com.example.petstore.models;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Category extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	@OneToMany
	private Set<Pet> pet;

	protected Category() {
	}

	public Category(String name, Set<Pet> pet) {
		super();
		this.name = name;
		this.pet = pet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Pet> getPet() {
		return pet;
	}

	public void setPet(Set<Pet> pet) {
		this.pet = pet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}