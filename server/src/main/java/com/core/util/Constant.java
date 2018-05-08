package com.core.util;

public class Constant {
	
	public static final int RESCODE_SUCCESS = 1000; //返回成功
	public static final int RESCODE_EXCEPTION = 1100; //返回失败
	public static final int RESCODE_DELETE_ERROR = 1200;
	public static final int RESCODE_DELETE_EXCEPTION = 1300;// 删除异常

	/*JWT使用*/
	// jwt id
	public static final String JWT_ID = "6851A";
	//密钥
	public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
	//tocken有效时间
	public static final long JWT_TTL = 1 * 5 * 1000;

	
}