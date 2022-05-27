package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

	@Query(value = "select * from product join item_product using(product_id) where shopper_id=?1", nativeQuery = true)
	List<Product> getProductsByShopper(String shopperId);
}
