package br.com.petsCare.entities.enumeration;

public enum OrderStatus {
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERD(4),
	CANCELED(5);

	private int code;

	private OrderStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static OrderStatus valueOf(int code) {
		for (OrderStatus value : values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return null;
	}

}
