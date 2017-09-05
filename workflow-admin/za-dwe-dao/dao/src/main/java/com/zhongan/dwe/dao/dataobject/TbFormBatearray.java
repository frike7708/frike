package com.zhongan.dwe.dao.dataobject;

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
private Date gmtCreated;
/**
* 修改时间
*/	
private Date gmtModified;
/**
* 创建人
*/	
private String creator;
/**
* 修改人
*/	
private String modifier;
/**
* 是否删除（Y/N)
*/	
private String isDeleted;
/**
* 备注
*/	
private String remark;
 }