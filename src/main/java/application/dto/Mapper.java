package application.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import application.entity.Brand;
import application.entity.Category;
import application.entity.Product;
import application.entity.Shopper;
import application.repository.ProductRepository;

public class Mapper {
	
	@Autowired ProductRepository productRepo;
	
	public static Product dtoToProduct(ProductDto productDto) {
		return new Product(productDto.getProductId(), new Category(productDto.getCategory()), new Brand(productDto.getBrand()));
	}
	
	public static List<Product> dtoListToProduct(List<ProductDto> listProductDto){
		return listProductDto.stream().map(Mapper::dtoToProduct).collect(Collectors.toList());
	}
	
	public static ProductDto productToDto(Product product) {
		return new ProductDto(product.getProductId(), product.getCategory().getName(), product.getBrand().getName());
	}
	
	public static List<ProductDto> productListToDto(List<Product> listProduct){
		return listProduct.stream().map(Mapper::productToDto).collect(Collectors.toList());
	}
	
	public static ShopperDto shopperToDto(Shopper shopper) {
		return new ShopperDto(shopper.getShopperId());
	}
	
	public static List<ShopperDto> shopperListToDto(List<Shopper> listShopper){
		return listShopper.stream().map(Mapper::shopperToDto).collect(Collectors.toList());
	}
	
	
}
