package com.zhongan.dwe;

import java.util.Date;
import lombok.Data;

@Data
public class TbFormBatearray {
/**
* 
*/	
private String id;
/**
* 
*/	
private String code;
/**
* 表单名称
*/	
private String name;
/**
* 
*/	
private byte[] bytes;
/**
* 创建时间
*/	
private Date addtime;
/**
* 修改时间
*/	
private Date motifytime;
/**
* 创建人
*/	
private String createuser;
/**
* 备注
*/	
private String remark;
 }