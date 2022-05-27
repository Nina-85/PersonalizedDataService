package application.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static application.exceptionHandling.DataTeamChecks.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import application.config.DataTeamParametres;
import application.dto.ItemProductDto;
import application.dto.Mapper;
import application.dto.ProductDto;
import application.dto.ShelvesDto;
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

@Service
public class DataTeam implements IDataTeam{
	
	@Autowired ProductRepository productRepo;
	@Autowired CategoryRepository categoryRepo;
	@Autowired BrandRepository brandRepo;
	@Autowired DataTeamParametres parametres;
	@Autowired ShopperRepository shopperRepo;
	@Autowired ItemProductRepository itemProductRepo;

	Logger logger = LoggerFactory.getLogger(DataTeam.class);
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	@Transactional
	public String getAndSaveProducts() {
				
		try {
			ProductDto[] productsDto = objectMapper.readValue(new File(parametres.getFileProducts()), ProductDto[].class);
			List<Product> products = Mapper.dtoListToProduct(Arrays.asList(productsDto));
			for(Product product: products) {
				if(checkIsProductExists(product.getProductId())==null)
				{
					saveProductToDB(product);
				}
				
			}
		} catch (IOException e) {
			e.getMessage();
		}
		return "Successful";
		
	}

	private void saveProductToDB(Product product) {

		String categoryName = product.getCategory().getName();
		String brandName = product.getBrand().getName();
		
		Category category = checkIsCategoryExists(categoryName);
		Brand brand = checkIsBrandExists(brandName);
				
		if(category==null) {
			categoryRepo.save(new Category(categoryName));
		}
		
		if(brand==null) {
			brandRepo.save(new Brand(brandName));
		}
		
		productRepo.save(product);			
	}

	@Override
	@Transactional
	public String getAndSaveShoppers() {
		try {
			ShelvesDto[] shelves = objectMapper.readValue(new File(parametres.getFileShelves()), ShelvesDto[].class);
			for(ShelvesDto shelf: shelves) {
				saveShelvesToDB(shelf);
			}
		} catch (IOException e) {
			e.getMessage();
		}
		
		return "Successfull";
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private void saveShelvesToDB(ShelvesDto shelf) {
		Shopper shopper = checkIsShopperExists(shelf.getShopperId());
				
		if(shopper==null) {
			shopper = shopperRepo.save(new Shopper(shelf.getShopperId()));			
		}
		
		List<ItemProductDto> itemsDto = shelf.getShelf();
		for(ItemProductDto item: itemsDto) {
			ItemProduct itemProduct = itemProductRepo.existsByInfo(item.getProductId(), shopper.getShopperId());
			
			if(itemProduct==null)
			{
				Product product = productRepo.findById(item.getProductId()).orElse(null);	
				if(product!=null) {
					itemProductRepo.save(new ItemProduct(shopper, product, item.getRelevancyScore()));
				}
			}
			else {
				itemProductRepo.updateRelevanceScore(itemProduct.getId(), item.getRelevancyScore());
			}
	
						
		}			
	}
	
	

}
