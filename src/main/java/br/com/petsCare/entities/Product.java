package br.com.petsCare.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product")
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "sq_product", sequenceName = "sq_product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_Product", nullable = false)
	@GeneratedValue(generator = "sq_product", strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nm_product", length = 100, nullable = false, unique = true)
	private String name;

	@Column(name = "nm_description", length = 255)
	private String description;

	@Column(name = "vl_price")
	private Double price;

	@Column(name = "nm_url_img")
	private String imgUrl;

	@Column
	private Integer quantity;

	@Column
	private Double porcentagemVenda;
	
	@Column
	private Double porcentagemLucro;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "fk_product"), inverseJoinColumns = @JoinColumn(name = "fk_category"))
	private Set<Category> categories = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_product_suppliers", joinColumns = @JoinColumn(name = "fk_product"), inverseJoinColumns = @JoinColumn(name = "fk_supplier"))
	private Set<Supplier> suppliers = new HashSet<>();

	@Column(name = "ts_last_update", insertable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

	@JsonIgnore
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();

	@PreUpdate
	public void onUpdate() {
		this.lastUpdate = new Date();
	}

	@PrePersist
	public void onInsert() {
		this.lastUpdate = new Date();
	}

	public Product(Long id, String name, String description, Double price, String imgUrl, Set<Category> categories,
			Set<Supplier> suppliers) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		if (!categories.isEmpty() && categories.size() > 0) {
			categories.forEach(cat -> this.categories.add(cat));
		}
		if (!suppliers.isEmpty() && suppliers.size() > 0) {
			suppliers.forEach(sup -> this.suppliers.add(sup));
		}
	}

	public Product(Optional<Product> prod) {
		super();
		this.id = prod.get().getId();
		this.name = prod.get().getName();
		this.description = prod.get().getDescription();
		this.price = prod.get().getPrice();
		this.imgUrl = prod.get().getImgUrl();
		if (!prod.get().categories.isEmpty() && prod.get().categories.size() > 0) {
			prod.get().categories.forEach(cat -> this.categories.add(cat));
		}
		if (!prod.get().suppliers.isEmpty() && prod.get().suppliers.size() > 0) {
			prod.get().suppliers.forEach(sup -> this.suppliers.add(sup));
		}

	}

	public Product() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPorcentagemVenda() {
		return porcentagemVenda;
	}

	public void setPorcentagemVenda(Double porcentagemVenda) {
		this.porcentagemVenda = porcentagemVenda;
	}

	public Double getPorcentagemLucro() {
		return porcentagemLucro;
	}

	public void setPorcentagemLucro(Double porcentagemLucro) {
		this.porcentagemLucro = porcentagemLucro;
	}

	public void addCategory(Category cat) {
		if (cat != null) {
			this.categories.add(cat);
		}
	}

	public void addSupplier(Supplier sup) {
		if (sup != null) {
			this.suppliers.add(sup);
		}
	}

	@JsonIgnore
	public Set<Order> getOrders() {
		Set<Order> set = new HashSet<>();
		for (OrderItem item : items) {
			set.add(item.getOrder());
		}
		return set;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}

}
