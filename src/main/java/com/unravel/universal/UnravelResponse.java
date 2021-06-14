package com.unravel.universal;

import java.util.HashMap;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class UnravelResponse {
	
	private HashMap<String, String> emptyMap = new HashMap<String,String>();
	
	public String getMessage() {
		if(this.message==null)
			this.message = StringUtils.EMPTY;
		return message;
	}

	public Integer getStatus() {
		return status;
	}

	public Object getData() {
		if(data==null) {
			this.data=emptyMap;
		}
		return data;
	}

	private String message;
	
	private Integer status;
	
	private Object data;
	
}


