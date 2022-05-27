package application.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"productId"})

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@Column(name = "product_id")
	private String productId;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	List<ItemProduct> items = new ArrayList<>();

	public Product(String productId, Category category, Brand brand) {
		super();
		this.productId = productId;
		this.category = category;
		this.brand = brand;
	}
	
	
	

}
