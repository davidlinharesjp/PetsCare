package br.com.petsCare.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author david
 * @apiNote - Classe Helper Files
 */
@Component
public class Disco {

	@Value("${file.disco.raiz}")
	private String raiz;

	/**
	 * 
	 * @return Retorna - O Path do File
	 * @param file      - O arquivo do File
	 * @param directory - O Diretorio da pasta rederente a file
	 * @param nameFile  - O nome que o arquive sera Salvo
	 * @author david
	 * 
	 */
	public Map<String, String> insertFile(MultipartFile file, String directory, String nameFile) {
		Map<String, String> map = new HashMap<>();
		try {
			/* Criando Diretorio URL do File a salvara */
			Path diretorioPath = Paths.get("C:/img", directory);
			/* Criando o path com nome arquivo */
			nameFile = nameFile.replace(" ", "");
			Path arquivoPath = diretorioPath.resolve(nameFile + "-" + file.getOriginalFilename());
			String url = raiz + directory + "/" + nameFile + "-" + file.getOriginalFilename();

			Files.createDirectories(diretorioPath);
			file.transferTo(arquivoPath.toFile());
			map.put("urlServer", url);
			map.put("arquivoPath", arquivoPath.toString());

			return map;
		} catch (IOException e) {
			throw new RuntimeException("Erro ao inserir ao salvar arquivo");
		}

	}

	/**
	 * Função do arquivo File no formato Byte *
	 * 
	 * @author david
	 * @param url - A url local do File
	 * @return Retorna o byte do File
	 */

	public byte[] getFileBytes(String url) {
		try {
			File file = new File(url);
			@SuppressWarnings("resource")
			FileInputStream fileStream = new FileInputStream(file);
			byte[] data = new byte[fileStream.available()];
			fileStream.read(data);
			return data;

		} catch (IOException e) {
			throw new RuntimeException("Error ao Carregar file");
		}

	}

	/*
	 * @Test public void givenNIO2_whenCopied_thenCopyExistsWithSameContents()
	 * throws IOException {
	 * 
	 * Path copied = Paths.get("src/test/resources/copiedWithNio.txt"); Path
	 * originalPath = original.toPath(); Files.copy(originalPath, copied,
	 * StandardCopyOption.REPLACE_EXISTING);
	 * 
	 * assertThat(copied).exists(); assertThat(Files.readAllLines(originalPath)
	 * .equals(Files.readAllLines(copied))); }
	 */

}
