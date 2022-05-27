package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, String>{

}
