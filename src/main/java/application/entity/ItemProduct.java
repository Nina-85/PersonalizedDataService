package application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "item_product")
public class ItemProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@ManyToOne
	@JoinColumn(name = "shopper_id")
	private Shopper shopper;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	 
	@Column(name = "relevancy_score")
	private double relevancyScore;
	
	public ItemProduct(Shopper shopper, Product product, double relevancyScore) {
		super();
		this.shopper = shopper;
		this.product = product;
		this.relevancyScore = relevancyScore;
	}
	
	
	

}
