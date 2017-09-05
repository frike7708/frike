package com.zhongan.dwe.dao.service.impl;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zhongan.base.repository.PageList;
import com.zhongan.dsf.provider.annotation.Provider;
import com.zhongan.dwe.dao.biz.TbFormBatearrayBiz;
import com.zhongan.dwe.dao.dataobject.TbFormBatearray;
import com.zhongan.dwe.dao.dto.TbFormBatearrayDTO;
import com.zhongan.dwe.dao.repository.tbformbatearray.TbFormBatearrayRepository;
import com.zhongan.dwe.dao.repository.tbformbatearray.condition.TbFormBatearrayWhere;
import com.zhongan.dwe.dao.service.TbFormBatearrayService;


@Provider
@Service("tbFormBatearrayService")
public class TbFormBatearrayServiceImpl implements TbFormBatearrayService {

	@Autowired
	@Qualifier("tbFormBatearrayBiz")
	private TbFormBatearrayBiz tbFormBatearrayBiz;
	
	@Autowired
	private TbFormBatearrayRepository tbFormBatearrayRepository;

	@Override
	public void add(TbFormBatearrayDTO tbFormBatearrayDTO) {
		tbFormBatearrayBiz.add(tbFormBatearrayDTO);
	}

	@Override 
	public void update(TbFormBatearrayDTO tbFormBatearrayDTO, String id) {
		tbFormBatearrayBiz.update(tbFormBatearrayDTO, id);
	}

	@Override
	public void delete(String id) {
		tbFormBatearrayBiz.delete(id);
	}

	@Override
	public TbFormBatearrayDTO getByID(String id) {
		return tbFormBatearrayBiz.getByID(id);
	}

	@Override
	public List<TbFormBatearrayDTO> selectFormList() {
		return tbFormBatearrayBiz.findAll();
	}

	@Override
	public List<TbFormBatearrayDTO> selectFormList(int start, int limit) {
		PageList<TbFormBatearrayDTO> findAll = tbFormBatearrayBiz.findAll(start, limit);
		return findAll.getRecords();
	}

	@Override
	public TbFormBatearrayDTO getByCode(String code) {
		TbFormBatearrayWhere where = new TbFormBatearrayWhere();
		where.addInCode(code);
		List<TbFormBatearrayDTO> findByCondition = tbFormBatearrayBiz.findByCondition(where);
		if(findByCondition.size() == 0){
			return null;
		}
		return findByCondition.get(0);
	}
	
	@Override
	public void insert(TbFormBatearrayDTO tbFormBatearrayDTO) {
		TbFormBatearray tbFormBatearray = new TbFormBatearray();
		BeanUtils.copyProperties(tbFormBatearrayDTO, tbFormBatearray);
		tbFormBatearrayRepository.insertTbFormbate(tbFormBatearray);
	}

}