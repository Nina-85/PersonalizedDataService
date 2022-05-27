package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import application.entity.ItemProduct;
import application.entity.Shopper;

public interface ItemProductRepository extends JpaRepository<ItemProduct, Long>{

	@Query(value = "select shopper_id from item_product where product_id=?1 limit ?2", nativeQuery = true)
	List<Shopper> getShoppersByProduct(String productId, Integer limit);

	@Query("select itemProduct from ItemProduct itemProduct where itemProduct.product.productId=?1 and itemProduct.shopper.shopperId=?2")
	ItemProduct existsByInfo(String productId, String shopperId);
	
	@Modifying
	@Query(value="UPDATE item_product SET relevancy_score=?2 WHERE id=?1 ", nativeQuery = true)
	void updateRelevanceScore(long id, double relevanceScore);

	
}
