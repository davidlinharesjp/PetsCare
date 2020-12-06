package br.com.petsCare.entities.dto;

import java.util.Optional;
import java.util.Set;

import br.com.petsCare.entities.Category;
import br.com.petsCare.entities.Product;
import br.com.petsCare.entities.Supplier;

//Classe de Recebimento do Producto
public class ProductDTO {

	private Long id;

	private String name;

	private String descricao;

	private byte[] img;

	private String url;

	private Double price;

	private Set<Category> category;
	
	private Set<Supplier> supplier;

	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.descricao = product.getDescription();
		this.price = product.getPrice();
		this.url = product.getImgUrl();
		this.category = product.getCategories();
		this.supplier = product.getSuppliers();
		
	}

	public ProductDTO(Optional<Product> prodOp) {
		this.id = prodOp.get().getId();
		this.name = prodOp.get().getName();
		this.descricao = prodOp.get().getDescription();
		this.url = prodOp.get().getImgUrl();
		this.category = prodOp.get().getCategories();
		this.supplier = prodOp.get().getSuppliers();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Supplier> getSupplier() {
		return supplier;
	}

	public void setSupplier(Set<Supplier> supplier) {
		this.supplier = supplier;
	}
	
	

}
