package br.com.petsCare.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "fk_product"), inverseJoinColumns = @JoinColumn(name = "fk_category"))
	private Set<Category> categories = new HashSet<>();

	@JsonIgnore
	@ManyToMany
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

	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
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
