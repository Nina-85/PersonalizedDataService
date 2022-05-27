package application.exceptionHandling;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.entity.Brand;
import application.entity.Category;
import application.entity.ItemProduct;
import application.entity.Product;
import application.entity.Shopper;
import application.repository.BrandRepository;
import application.repository.CategoryRepository;
import application.repository.ItemProductRepository;
import application.repository.ProductRepository;
import application.repository.ShopperRepository;

@Component
public class DataTeamChecks {
	
	@Autowired CategoryRepository categoryRepoAutowired;
	@Autowired BrandRepository brandRepoAutowired;
	@Autowired ProductRepository productRepoAutowired;
	@Autowired ShopperRepository shopperRepoAutowired;
	@Autowired ItemProductRepository itemProductRepoAutowired;
	
	private static CategoryRepository categoryRepo;
	private static BrandRepository brandRepo;
	private static ProductRepository productRepo;
	private static ShopperRepository shopperRepo;
	private static ItemProductRepository itemProductRepo;
	
	@PostConstruct
	private void plugAutowired() {
		categoryRepo = categoryRepoAutowired;
		brandRepo = brandRepoAutowired;
		productRepo = productRepoAutowired;
		shopperRepo = shopperRepoAutowired;
		itemProductRepo = itemProductRepoAutowired;
	}
		
	public static Category checkIsCategoryExists(String categoryName) {
		  
	      return categoryRepo.findById(categoryName).orElse(null);    
	}
	
	public static Brand checkIsBrandExists(String brandName) {

		return brandRepo.findById(brandName).orElse(null);
	}
	
	public static Product checkIsProductExists(String productId) {
		
		return productRepo.findById(productId).orElse(null);
			
	}
	
	public static Shopper checkIsShopperExists(String shopperId) {
		
		return shopperRepo.findById(shopperId).orElse(null);
	}
	
	public static void checkIsProductNotExists(String productId) {
		
		Product product = productRepo.findById(productId).orElse(null);
		if(product==null)
			throw new BadRequestException("Product not found");
		
	}
	
	public static ItemProduct checkIsItemProductExists(String productId, String shopperId)
	{
		return itemProductRepo.existsByInfo(productId, shopperId);
		
	}
	
	public static void checkIsCategoryNotExists(String categoryName) {
		if(categoryName!=null)
		{
			Category category = checkIsCategoryExists(categoryName);
			if(category==null)
				throw new BadRequestException("Category " + categoryName + " not fount");
		}
		
	}
	
	public static void checkIsBrandNotExists(String brandName) {
		if(brandName!=null)
		{
			Brand brand = checkIsBrandExists(brandName);
			if(brand==null)
				throw new BadRequestException("Brand " + brandName + " not fount");
		}
	}
	
	public static void checkIsShopperNotExists(String shopperId) {
		
		Shopper shopper = checkIsShopperExists(shopperId);
		if(shopper==null)
			throw new BadRequestException("Shopper with id " + shopperId +" not found");
		
	}
	
	
	

}
