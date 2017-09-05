package com.zhongan.dwe.dao.service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.zhongan.dwe.dao.BaseTest;
import com.zhongan.dwe.dao.dto.TbFormBatearrayDTO;


public class TbFormBatearrayTest extends BaseTest {

	@Autowired
	@Qualifier("tbFormBatearrayService")
	private TbFormBatearrayService tbFormBatearrayService;

	@Autowired
	@Qualifier("tbFormBatearrayServiceDSF")
	private TbFormBatearrayService tbFormBatearrayServiceDSF;
	@Test
	public void test() {
		TbFormBatearrayDTO tbFormBatearrayDTO = new TbFormBatearrayDTO();
		//TODO set values
		tbFormBatearrayServiceDSF.add(tbFormBatearrayDTO);
	}
}