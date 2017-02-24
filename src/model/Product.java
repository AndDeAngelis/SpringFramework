package model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
	private String id;
	private String artist;
	private String album;
	private String category;
	private String description;
	private double price;
	private int inStock;
	private String imagePath;
	private int exitYear;
	
	public Product() { }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String category, String album) {
		String path = "resources/img/" + category.toLowerCase() + "/" + album.replaceAll(" ", "_").toLowerCase() + ".jpg";
		this.imagePath = path;
	}

	public int getExitYear() {
		return exitYear;
	}

	public void setExitYear(int exitYear) {
		this.exitYear = exitYear;
	}
}
