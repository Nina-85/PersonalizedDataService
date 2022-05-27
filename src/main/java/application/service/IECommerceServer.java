package application.service;

import java.util.List;

import application.dto.ProductDto;
import application.dto.RequestDto;
import application.dto.ShopperDto;

public interface IECommerceServer {
	
	List<ProductDto> getProductsByShopper(RequestDto data);
	List<ShopperDto> getShoppersByProduct(String productId, Integer limit);

}
