package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	public void create(Product product);
	public Product findProductById(String id);
	public List<Product> findProductsByCategory(String category);
	public List<Product> findAllProducts();
}
