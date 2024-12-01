package com.tucandeira.myPiggy.model;

public class Category {
  private int id;
	private String name;
	private String description;
	
	public Category(int id, String name, String description) {
    this.setId(id);
	  this.setName(name);
    this.setDescription(description);
  }

  public void setName(String name){
    this.name = name;
  }

  public void setDescription(String description){
    this.description = description;
  }

  public String getName(){
    return name;
  }

  public String getDescription(){
    return description;
  }

  public int getId(){
    return id;
  }

  public void setId(int id){
    this.id = id;
  }

}
