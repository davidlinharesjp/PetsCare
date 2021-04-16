package br.com.petsCare.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.petsCare.helpers.Disco;

@RestController
@RequestMapping(value = "/file")
public class FileResource {

	@Autowired
	private Disco disco;
	
	@PostMapping
	public void upload(@RequestParam MultipartFile file) {
		disco.insertFile(file, "produto", "0002Primeiro");
		
	}
}
