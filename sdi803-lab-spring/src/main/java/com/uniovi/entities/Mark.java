package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mark {
	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private Double score;

	public Mark() {

	}

	public Mark(Long id, String description, Double score) {
		super();
		this.id = id;
		this.email = description;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Mark [id=" + id + ", email=" + email + ", score=" + score + "]";
	}

}
