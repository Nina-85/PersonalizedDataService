package application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class DataTeamParametres {
	
	@Value("${file_products:products.json}")
	private String fileProducts;

	@Value("${file_shelves:shelves.json}")
	private String fileShelves;
	
}
