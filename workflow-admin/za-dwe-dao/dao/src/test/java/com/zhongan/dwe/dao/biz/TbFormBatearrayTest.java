package com.zhongan.dwe.dao.biz;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.zhongan.dwe.dao.BaseTest;
import com.zhongan.dwe.dao.dto.TbFormBatearrayDTO;


public class TbFormBatearrayTest extends BaseTest {

	@Autowired
	@Qualifier("tbFormBatearrayBiz")
	private TbFormBatearrayBiz tbFormBatearrayBiz;

	@Test
	public void test() {
		TbFormBatearrayDTO tbFormBatearrayDTO = new TbFormBatearrayDTO();
		//TODO set values
		tbFormBatearrayBiz.add(tbFormBatearrayDTO);
	}
}