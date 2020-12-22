package database;

public class Book {
	private int id;
	private String name;
	private float price;
	private int remain;
	
	public void setId(int id){
		this.id=id;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setPrice(float price){
		this.price=price;
	}
	public void setRemain(int remain){
		this.remain=remain;
	}
	
	public int getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public float getPrice(){
		return price;
	}
	public int getRemain(){
		return remain;
	}	
}
