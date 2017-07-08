package org.magic.am;

public class Pair {
	private long id;
	private String name; //Lee Min Ho
	private String birth; //6/22/87
	private String nationality; //Korean
	private String title; //Personal Taste
	private String release; //2010
	private String genre; //Comedy, Romance
	private String country;//Korea
	private String imagepath;

	
	public Pair(long id, String name, String birth, String nationality, String title, String release, String genre, String country, String imagepath){
	this.id =  id;
	this.name =  name;
	this.birth = birth;
	this.nationality = nationality;
	this.title = title;
	this.release = release;
	this.genre = genre;
	this.country = country;
	this.imagepath = imagepath; 
	
	}
	
	
	//get and set methods
	public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getTitle() {
		    return title;
		  }

		  public void setTitle(String title) {
		    this.title = title;
		  }

	  public String getBirth() {
		    return birth;
		  }

	 public void setBirth(String birth) {
		    this.birth = birth;
	    }
		  
	  public String getNationality() {
			    return nationality;
			  }

	  public void setNationality(String nationality) {
			    this.nationality = nationality;
			  }

	  public String getRelease() {
				    return release;
				  }

	  public void setRelease(String release) {
				    this.release = release;
		 }

	  
	  public String getGenre() {
		    return genre;
		  }

	  public void setGenre(String genre) {
		    this.genre = genre;
		  }
	  public String getCountry() {
			    return country;
			  }

	  public void setCountry(String country) {
			    this.country = country;
			  }
	  public String getImagepath() {
				    return imagepath;
				  }

	  public void setImagepath(String imagepath) {
				    this.imagepath = imagepath;
				  }

}
