/**
 * 
 */
package com.xjy.security.validate.code;

import java.time.LocalDateTime;


/**
 * @author zhailiang ,因为验证码有很多种，但是码和超时是不会变的，抽出父类，供子类继承
 *由于短信验证码和验证码的父类一样就能满足需求，故用父类验证码类充当短信验证码存在session，图片验证码需要扩展父类验证码才能存session
 */
public class ValidateCode {
	
	private String code;
	
	private LocalDateTime expireTime;
	
	public ValidateCode(String code, int expireIn){
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public ValidateCode(String code, LocalDateTime expireTime){
		this.code = code;
		this.expireTime = expireTime;
	}

	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}
	
}
