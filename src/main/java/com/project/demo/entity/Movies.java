package com.project.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "movie_list")
public class Movies {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movId;
	
	@NotNull
	private String movName;
	
	@NotNull
	private String movGenre;
	
	@NotNull
	private String movLang;
	
	@NotNull
	private String movDuration;
	
	@ManyToMany
	@JoinTable(
            name = "movie_theater_mapping",
            joinColumns = @JoinColumn(name = "movid"),
            inverseJoinColumns = @JoinColumn(name = "theaterid")
    )
	private List<Theaters> theaters;

	public Movies() {
	}

	public Movies(String movName, String movGenre, String movLang, String movDuration) {
		super();
		this.movName = movName;
		this.movGenre = movGenre;
		this.movLang = movLang;
		this.movDuration = movDuration;
	}

	public Movies(Long movId, String movName, String movGenre, String movLang, String movDuration) {
		super();
		this.movId = movId;
		this.movName = movName;
		this.movGenre = movGenre;
		this.movLang = movLang;
		this.movDuration = movDuration;
	}

	public Movies(Long movId, String movName, String movGenre, String movLang,
			List<Theaters> theaters) {
		super();
		this.movId = movId;
		this.movName = movName;
		this.movGenre = movGenre;
		this.movLang = movLang;
		this.theaters = theaters;
	}

	public Long getMovid() {
		return movId;
	}

	public void setMovid(Long movId) {
		this.movId = movId;
	}

	public String getMovname() {
		return movName;
	}

	public void setMovname(String movName) {
		this.movName = movName;
	}

	public String getMovgenre() {
		return movGenre;
	}

	public void setMovgenre(String movGenre) {
		this.movGenre = movGenre;
	}

	public String getMovlang() {
		return movLang;
	}

	public void setMovlang(String movLang) {
		this.movLang = movLang;
	}

	public String getMovDuration() {
		return movDuration;
	}

	public void setMovDuration(String movDuration) {
		this.movDuration = movDuration;
	}

	public List<Theaters> getTheaters() {
		return theaters;
	}

	public void setTheaters(List<Theaters> theaters) {
		this.theaters = theaters;
	}

	@Override
	public String toString() {
		return "Movies [Movie Id=" + movId + ", Movie Name=" + movName + ", Movie Genre=" + movGenre + ", Language=" + movLang + ", Duration=" + movDuration + "]";
	}

}
