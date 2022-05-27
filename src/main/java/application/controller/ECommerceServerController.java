package application.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.dto.ProductDto;
import application.dto.RequestDto;
import application.dto.ShopperDto;
import application.service.IECommerceServer;


@RestController
@Validated
public class ECommerceServerController {
	
		@Autowired IECommerceServer eCommerceServer;

		@GetMapping("/productsByShopper")
		List<ProductDto> getProductsByShopper(
				@RequestParam (name = "shopper_id", required = true) @NotNull String shopperId,
				@RequestParam(name = "category", required = false) String category, 
				@RequestParam(name = "brand", required = false) String brand,
				@RequestParam(name = "limit", required = false) @Max(100) Integer limit)
		{
			
			RequestDto data = new RequestDto(shopperId, category, brand, limit);
			return eCommerceServer.getProductsByShopper(data);
		}
		
		@GetMapping("/shoppersByProduct")
		List<ShopperDto> getShoppersByProduct(
				@RequestParam(name = "product_id", required = true) @NotNull String productId, 
				@RequestParam(name = "limit", required = false) @Max(value = 1000, message = "Value must be less then 1001") Integer limit)
		{	
			return eCommerceServer.getShoppersByProduct(productId, limit);
		}
		
		
}
