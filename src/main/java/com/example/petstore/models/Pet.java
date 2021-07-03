package com.example.petstore.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Pet extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Category category;

	@ManyToMany
	private Set<Tag> tags = new HashSet<>();

	private String name;

	private String photoUrl;

	@Enumerated(EnumType.STRING)
	private PetStatus status;

	public Pet() {
	}

	public Pet(Category category, String name, String photoUrl, PetStatus status) {
		this.category = category;
		this.name = name;
		this.photoUrl = photoUrl;
		this.status = status;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Tag> getTags() {
		return this.tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public PetStatus getStatus() {
		return this.status;
	}

	public void setStatus(PetStatus status) {
		this.status = status;
	}

}