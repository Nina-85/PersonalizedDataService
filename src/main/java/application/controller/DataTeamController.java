package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import application.service.IDataTeam;

@RestController
public class DataTeamController {
	@Autowired IDataTeam dataTeam;

	@PutMapping("/saveProducts")
	public String getAndSaveProducts() {
		return dataTeam.getAndSaveProducts();
	}
	
	@PutMapping("/saveShoppers")
	public String getAndSaveShoppers() {
		return dataTeam.getAndSaveShoppers();
	}
}
