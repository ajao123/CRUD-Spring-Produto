package com.example.demo.models.enums;

public enum OrderStatus {

	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private Integer code;
	
	private OrderStatus(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public static OrderStatus valueOf(Integer code) {
		for(OrderStatus os : OrderStatus.values()) {
			if(os.getCode() == code) {
				return os;
			}
		}
		throw new IllegalArgumentException("Invalid Order Status code");
	}
	
}
