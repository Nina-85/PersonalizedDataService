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
@EqualsAndHashCode(of = {"name"})

@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@Column(name = "category_name")
	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	List<Product> products = new ArrayList<>();

	public Category(String name) {
		super();
		this.name = name;
	}
	
	
}
