package application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dto.Mapper;
import application.dto.ProductDto;
import application.dto.RequestDto;
import application.dto.ShopperDto;
import application.entity.Product;
import application.entity.Shopper;
import application.repository.ItemProductRepository;
import application.repository.ProductRepository;

import static application.exceptionHandling.DataTeamChecks.*;

@Service 
public class ECommerceServer implements IECommerceServer{

	@Autowired ProductRepository productRepo;
	@Autowired ItemProductRepository itemProductRepo;
	
	@Override
	public List<ProductDto> getProductsByShopper(RequestDto data) {
		
		checkIsShopperNotExists(data.getShopperId());		
		checkIsCategoryNotExists(data.getCategory());
		checkIsBrandNotExists(data.getBrand());
		
		Integer limit = data.getLimit()==null ? 10 : data.getLimit();
		List<Product> products = productRepo.getProductsByShopper(data.getShopperId())
				.stream()
				.filter(product -> isCorrectCategory(product, data.getCategory()))
				.filter(product -> isCorrectBrand(product, data.getBrand()))
				.limit(limit).collect(Collectors.toList());	
	
		return Mapper.productListToDto(products);
	}

	private boolean isCorrectBrand(Product product, String brand) {
		if(brand==null || brand.isEmpty())
			return true;
		
		return product.getBrand().getName().equals(brand);
	}

	private boolean isCorrectCategory(Product product, String category) {
		
		if(category==null || category.isEmpty())
			return true;
		
		return product.getCategory().getName().equals(category);
	}

	@Override
	public List<ShopperDto> getShoppersByProduct(String productId, Integer limit) {
		
		checkIsProductNotExists(productId);
		
		Integer lim = limit==null ? 10 : limit;
		List<Shopper> shoppers = itemProductRepo.getShoppersByProduct(productId, lim);
		
		return Mapper.shopperListToDto(shoppers);
	}

}
