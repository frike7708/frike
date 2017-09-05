package com.zhongan.dwe.model;

public class Code {
	
	public static final int SUCCESS_CODE = 0;//成功标记
	public static final int FIAL_CODE = -1;//失败
	
	//参数错误
	public static final int param_missing = 1000;//请求参数缺失

	//http 响应CODE错误
	public static final int ok = 200 ;//ok  - 成功返回状态，对应，GET,PUT,PATCH,DELETE.
	public static final int created =201;// created  - 成功创建。
	public static final int not_modified =304;// not modified   - HTTP缓存有效。
	public static final int bad_request =400;//bad request   - 请求格式错误。
	public static final int unauthorized =401;// unauthorized   - 未授权。
	public static final int forbidden =403 ;//forbidden   - 鉴权成功，但是该用户没有权限。
	public static final int not_found =404;// not found - 请求的资源不存在
	public static final int method_not_allowed =405;// method not allowed - 该http方法不被允许。
	public static final int gone =410;// gone - 这个url对应的资源现在不可用。
	public static final int unsupported_media_type =415;// unsupported media type - 请求类型错误。
	public static final int unprocessable_entity =422 ;//unprocessable entity - 校验错误时用。
	public static final int too_many_request =429 ;//too many request - 请求过多。

}
