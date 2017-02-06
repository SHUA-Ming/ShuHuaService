package com.lst.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lst.dao.MstCodeMapper;
import com.lst.model.MstCode;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestTransation {

	private static ApplicationContext ctx;

	@Autowired
	private MstCodeMapper mapper = ctx.getBean(MstCodeMapper.class);

	static {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void test() {
		MstCode mc = new MstCode();
		mc.setLevel(1);

		int i = mapper.insert(mc);

		mc = new MstCode();
		mc.setName("wqerwreqwtrewqtwqetrwetrwetrwetrwewerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		
		i = mapper.insert(mc);
		
		fail("Not yet implemented");
	}
}
