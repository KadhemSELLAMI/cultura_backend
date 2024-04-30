package com.culturascope.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "new_sites")
public class Site {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "location")
	private String location;

	@Column(name = "description", length = 10000)
	private String description;

	@Column(name = "media")
	private String media;

	public Site() {}

	public Site(
		String name,
		String location,
		String description,
		String media
	) {
		this.name = name;
		this.location = location;
		this.description = description;
		this.media = media;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}
}
