package com.educandoweb.course.entities.enums;

public enum OrderStatus {

	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code; //atributo code para receber o código
	
	private OrderStatus(int code) { //construtor com o atributo code
		this.code = code;
	}
	
	public int getCode() { //método de acesso ao atributo
		return code;
	}
	
	public static OrderStatus valueOf(int code) {           //recebe um código no parâmetro
		for(OrderStatus value : OrderStatus.values()) { //para cada valor do OrderStatus enum,
			if (value.getCode() == code) {         //verifique se o valor do código fornecido é o mesmo que um dos códigos na lista de status
				return value;                  //se sim, retorne o código
			}                        //esse método serve basicamente para validar um código inserido no parâmetro OrderStatus
		}
	throw new IllegalArgumentException("Invalid OrderStatus code"); //caso contrário, ou seja, caso o if = false, exiba a mensagem
	}
}