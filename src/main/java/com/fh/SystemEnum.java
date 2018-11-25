/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:SystemEnum.java 
 * 包名:com.fh.shop.admin.coommon 
 * 创建日期:2018年10月19日下午2:01:59 
 * Copyright (c) 2018, 1246436594@qq.com All Rights Reserved.</pre> 
 */  
package com.fh;

import com.fh.model.IEunm;

/**
 * <pre>项目名称：shop-admin    
 * 类名称：SystemEnum    
 * 类描述：    
 * 创建人：张连强 1246436594@qq.com
 * 创建时间：2018年10月19日 下午2:01:59    
 * 修改人：张连强 1246436594@qq.com
 * 修改时间：2018年10月19日 下午2:01:59    
 * 修改备注：       
 * @version </pre>    
 */
public enum SystemEnum implements IEunm {
	
	SUCCESS(200,"OK"),
	ERROR(-1,"error"),
	BRAND_NULL(1005,"后台接受参数失败"),
	BRANDNAME_NULL(1006,"品牌名称不能为空或者LOGO不能为空"),
	LOGIN_USER_LOCK(1004,"您好，您的账号已锁定！"),
	LOGIN_INFO_MISS(1003,"用户信息不完整"),
	LOGIN_PASSWORD_MISS(1002,"密码错误"),
	LOGIN_CODE_MISS(1000,"验证码错误"),
	SMS_MODIEL_NULL(2000,"手机号为空"),
	SMS_MODIEL_INVALIDATE(2001,"手机号不正确"),
	USER_MODIEL_NULL(3000,"手机号不能为空"),
	USER_PHOTOCODE_NULL(3001,"手机验证码不能为空"),
	USER_PHOTOCODE_NOTSMALL(3002,"验证码不一致"),
	USER_CODE_TIMEOUT(3003,"验证码超时"),
	USER_EXISTS(3004,"该用户已存在"),
	LOGIN_USER_MISS(1001,"用户名不存在");

	private int code;
	
	private String msg;
	
	private SystemEnum(int code, String msg){
		this.code=code;
		this.msg=msg;
	}


	@Override
	public int code() {
		return this.code;
	}

	@Override
	public String msg() {
		return this.msg;
	}
}
