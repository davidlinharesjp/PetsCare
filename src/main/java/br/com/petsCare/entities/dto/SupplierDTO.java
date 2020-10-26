package br.com.petsCare.entities.dto;

import br.com.petsCare.entities.Supplier;

public class SupplierDTO {

	private Long id; 
	private String name;
	private String companyName;
	
	public SupplierDTO (Supplier supplier) {
		this.id = supplier.getId();
		this.name = supplier.getName();
		this.companyName = supplier.getCompanyName();
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
