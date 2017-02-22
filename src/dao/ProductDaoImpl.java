package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	private static final String COLLECTION = "products";
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void create(Product product) {
		product.setImagePath(product.getCategory(), product.getAlbum());
		this.mongoTemplate.insert(product, COLLECTION);
	}

	@Override
	public Product findCustomerById(String id) {
		return this.mongoTemplate.findById(id, Product.class, COLLECTION);
	}

	@Override
	public List<Product> findProductsByCategory(String category) {
		List<Product> productsByCategory = new ArrayList<>();
		
		Query query = new Query();
		query.addCriteria(Criteria.where("category").is(category));
		
		productsByCategory = this.mongoTemplate.find(query, Product.class, COLLECTION);
		
		return productsByCategory;
	}

	@Override
	public List<Product> findAllProducts() {
		List<Product> products = new ArrayList<>();
		products = this.mongoTemplate.findAll(Product.class, COLLECTION);
		return products;
	}
}
