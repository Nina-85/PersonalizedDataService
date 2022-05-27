package application.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShelvesDto {
	
	private String shopperId;
	private List<ItemProductDto> shelf;
	

}
