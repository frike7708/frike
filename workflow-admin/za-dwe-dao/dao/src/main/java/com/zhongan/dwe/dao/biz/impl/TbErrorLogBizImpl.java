package com.zhongan.dwe.dao.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhongan.base.repository.OrderByInfo;
import com.zhongan.base.repository.PageInfo;
import com.zhongan.base.repository.PageList;
import com.zhongan.base.repository.PageListImpl;
import com.zhongan.dwe.dao.biz.TbErrorLogBiz;
import com.zhongan.dwe.dao.dataobject.TbErrorLog;
import com.zhongan.dwe.dao.dto.TbErrorLogDTO;
import com.zhongan.dwe.dao.repository.tberrorlog.TbErrorLogRepository;
import com.zhongan.dwe.dao.repository.tberrorlog.condition.TbErrorLogSelect;
import com.zhongan.dwe.dao.repository.tberrorlog.condition.TbErrorLogWhere;
import com.zhongan.dwe.dao.repository.tberrorlog.condition.TbErrorLogCondition;

@Service("tbErrorLogBiz")
public class TbErrorLogBizImpl implements TbErrorLogBiz {

	@Autowired
	private TbErrorLogRepository tbErrorLogRepository;

	@Override
	@Transactional
	public int add(TbErrorLogDTO tbErrorLogDTO) {
		TbErrorLog tbErrorLog = new TbErrorLog();
		BeanUtils.copyProperties(tbErrorLogDTO, tbErrorLog);
		int numRow = tbErrorLogRepository.insert(tbErrorLog);
		tbErrorLogDTO.setId(tbErrorLog.getId());
		return numRow;
	}

	@Override
	@Transactional
	public int update(TbErrorLogDTO tbErrorLogDTO, String id) {
		TbErrorLog tbErrorLog = tbErrorLogRepository.get(id);
		int numRow = 0;
		if (tbErrorLog != null) {
			BeanUtils.copyProperties(tbErrorLogDTO, tbErrorLog);
			tbErrorLog.setId(id);
			numRow = tbErrorLogRepository.update(tbErrorLog);
		}
		return numRow;
	}
	
	@Override
	@Transactional
	public int updateByCondition(TbErrorLogDTO tbErrorLogDTO, TbErrorLogWhere where) {
		TbErrorLog tbErrorLog = new TbErrorLog();
		BeanUtils.copyProperties(tbErrorLogDTO, tbErrorLog);
		return tbErrorLogRepository.updateByCondition(tbErrorLog,where);
	}	

	@Override
	@Transactional
	public int delete(String id) {
		return tbErrorLogRepository.deleteByOid(id);
	}

	@Override
	public TbErrorLogDTO getByID(String id) {
		TbErrorLogDTO tbErrorLogDTO = new TbErrorLogDTO();
		TbErrorLog tbErrorLog = tbErrorLogRepository.get(id);
		if (tbErrorLog == null) {
			return null;
		}
		BeanUtils.copyProperties(tbErrorLog, tbErrorLogDTO);
		return tbErrorLogDTO;
	}


	@Override
	public List<TbErrorLogDTO> findAll() {
		return convert(tbErrorLogRepository.findAll());
	}
	
	@Override
	public PageList<TbErrorLogDTO> findAll(int start, int limit) {
		return findByCondition(start,limit,null);
	}

	@Override
	public PageList<TbErrorLogDTO> findByCondition(int start, int limit,TbErrorLogWhere where) {
		return findByCondition(start,limit,where,null);
	}
	
	@Override
	public PageList<TbErrorLogDTO> findByCondition(int start, int limit,TbErrorLogWhere where,OrderByInfo orderBy) {
		return findByCondition(start,limit,where,orderBy,null);
	}
	
	@Override
	public PageList<TbErrorLogDTO> findByCondition(int start, int limit,TbErrorLogWhere where,OrderByInfo orderBy,TbErrorLogSelect select) {
		int count = tbErrorLogRepository.countByCondition(where);
		TbErrorLogCondition condition = new TbErrorLogCondition();
		PageInfo page = new PageInfo();
		page.setStart(start);
		page.setLimit(limit);
		condition.setPage(page);
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return new PageListImpl<TbErrorLogDTO>(count, convert(tbErrorLogRepository.findByCondition(condition)));		
	}	
	
	@Override
	public List<TbErrorLogDTO> findByCondition(TbErrorLogWhere where) {
		return findByCondition(where,null);
	}
	
	@Override
	public List<TbErrorLogDTO> findByCondition(TbErrorLogWhere where,OrderByInfo orderBy) {
		return findByCondition(where,orderBy,null);
	}
	
	@Override
	public List<TbErrorLogDTO> findByCondition(TbErrorLogWhere where,OrderByInfo orderBy,TbErrorLogSelect select) {
		TbErrorLogCondition condition = new TbErrorLogCondition();
		condition.setSelect(select);
		condition.setOrderBy(orderBy);
		condition.setWhere(where);
		return convert(tbErrorLogRepository.findByCondition(condition));
	}
	
	private List<TbErrorLogDTO> convert(List<TbErrorLog> tbErrorLogs) {
		List<TbErrorLogDTO> tbErrorLogDTOs = new ArrayList<TbErrorLogDTO>();
		TbErrorLogDTO tbErrorLogDTO;
		for (TbErrorLog tbErrorLog : tbErrorLogs) {
			tbErrorLogDTO = new TbErrorLogDTO();
			BeanUtils.copyProperties(tbErrorLog, tbErrorLogDTO);
			tbErrorLogDTOs.add(tbErrorLogDTO);
		}
		return tbErrorLogDTOs;
	}
}