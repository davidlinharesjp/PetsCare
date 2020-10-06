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
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_category")
@SequenceGenerator(name = "sq_category", sequenceName = "sq_category", initialValue = 1, allocationSize = 1)
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_category", nullable = false)
	@GeneratedValue(generator = "sq_category", strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = " nm_name", nullable = false, length = 255)
	private String name;

	@Column(name = "dt_last_update", insertable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

	@JsonIgnore
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products = new HashSet<>();

	@PrePersist
	public void onInsert() {
		this.lastUpdate = new Date();
	}

	@PreUpdate
	public void onUpdate() {
		this.lastUpdate = new Date();
	}

	public Category() {
		super();
	}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public Set<Product> getProducts() {
		return products;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
