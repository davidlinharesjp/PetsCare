package br.com.pestCare.resource.exception;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

// Erro Padrão
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	public StandardError() {
		super();
	}

	public StandardError(Instant instant, Integer status, String error, String message, String path) {
		super();
		this.timestamp = instant;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Instant getInstant() {
		return timestamp;
	}

	public void setInstant(Instant instant) {
		this.timestamp = instant;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
