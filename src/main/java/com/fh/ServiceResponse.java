/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:ServletResponse.java 
 * 包名:com.fh.shop.admin.coommon 
 * 创建日期:2018年10月18日下午6:57:22 
 * Copyright (c) 2018, 1246436594@qq.com All Rights Reserved.</pre> 
 */  
package com.fh;

import com.fh.model.IEunm;

import java.io.Serializable;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：ServletResponse    
 * 类描述：    
 * 创建人：张连强 1246436594@qq.com
 * 创建时间：2018年10月18日 下午6:57:22    
 * 修改人：张连强 1246436594@qq.com
 * 修改时间：2018年10月18日 下午6:57:22    
 * 修改备注：       
 * @version </pre>    
 */
public class ServiceResponse<T> implements Serializable {

	private static final long serialVersionUID = 6767870544093689105L;

	private int code;
	
	private String message;
	
	private T data;
	
	public ServiceResponse(){
		
	}
	
	private  ServiceResponse(int code,String message,T data){
		this.code=code;
		this.message=message;
		this.data=data;
	}

	public static <T> ServiceResponse<T> success(T data){
		return new ServiceResponse(200,"ok",data);
	}
	public static ServiceResponse success(){
		return new ServiceResponse(200,"ok",null);
	}
	
	public static ServiceResponse error(int code,String message){
		return new ServiceResponse(code,message,null);
	}
	
	public static ServiceResponse error(IEunm systemEnum){
		return new ServiceResponse(systemEnum.code(),systemEnum.msg(),null);
	}
	
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}
	
	
}
