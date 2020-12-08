package br.com.petsCare.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.petsCare.entities.Product;
import br.com.petsCare.entities.dto.ProductDTO;
import br.com.petsCare.helpers.Disco;
import br.com.petsCare.repository.ProductRepository;
import br.com.petsCare.service.exception.DatabaseException;
import br.com.petsCare.service.exception.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Disco discoHelper;

	private static final String PRODUCT = "produto";

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> opProduct = productRepository.findById(id);
		return opProduct.get();
	}

	public Product insert(Product product) {
		try {
			return productRepository.save(product);
		} catch (EntityExistsException e) {
			throw new EntityExistsException(product.getName());
		}
	}

	public Product update(Product newProduct, Long id) {
		try {
			Product oldProduct = findById(id);	
			updateProduct(oldProduct, newProduct);			
			return productRepository.save(oldProduct);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(newProduct.getId());
		}
	}
	
	public void updateProduct(Product oldProd, Product newProduc) {
		oldProd.setDescription(newProduc.getDescription());
		oldProd.setName(newProduc.getName());
		oldProd.setPorcentagemLucro(newProduc.getPorcentagemLucro());
		oldProd.setPrice(newProduc.getPrice());
		oldProd.setQuantity(newProduc.getQuantity());
		oldProd.updateCategories(newProduc.getCategories());
		oldProd.updateSupplier(newProduc.getSuppliers());		
	}

	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public ProductDTO insertImg(MultipartFile img, Long id) {
		Optional<Product> prod = productRepository.findById(id);
		Product produto = productRepository.findByID(id);
		byte[] data = null;
		if (!prod.isEmpty() && produto.getId() != null) {
			String nameFile = prod.map(p -> p.getName() + "-" + p.getId()).orElse(null);
			Map<String, String> urlMap = discoHelper.insertFile(img, PRODUCT, nameFile);
			
			produto.setImgUrl(urlMap.get("urlServer"));
			productRepository.save(produto);
			data = discoHelper.getFileBytes(urlMap.get("arquivoPath"));

		}

		ProductDTO prodDTO = new ProductDTO(produto);
		if (data != null) {
			prodDTO.setImg(data);
		}
		return prodDTO;
	}

}
