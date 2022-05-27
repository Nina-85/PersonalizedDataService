package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Shopper;

public interface ShopperRepository extends JpaRepository<Shopper, String>{
	
}
