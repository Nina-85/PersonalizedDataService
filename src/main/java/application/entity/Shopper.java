package application.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
@EqualsAndHashCode(of = {"shopperId"})

@Entity
@Table(name = "shopper")
public class Shopper {
	
	@Id
	@Column(name = "shopper_id")
	private String shopperId;
	
	@OneToMany(mappedBy = "shopper", fetch = FetchType.LAZY)
	List<ItemProduct> products = new ArrayList<>();

	public Shopper(String shopperId) {
		super();
		this.shopperId = shopperId;
	}

	
}
