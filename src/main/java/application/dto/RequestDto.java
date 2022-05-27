package application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestDto {

	private String shopperId;
	private String category;
	private String brand;
	private Integer limit;
	
}
