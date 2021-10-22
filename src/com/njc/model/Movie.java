package com.njc.model;

public class Movie {
//name, actor, actress, director, year of release

	private String name;
	private String actorName;
	private String actressName;
	private String directorName;
	private Long yor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActressName() {
		return actressName;
	}

	public void setActressName(String actressName) {
		this.actressName = actressName;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public Long getYor() {
		return yor;
	}

	public void setYor(Long yor) {
		this.yor = yor;
	}

}
