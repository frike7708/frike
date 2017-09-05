package com.zhongan.workflow.auto;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ClaimsTaskExcuteService implements JavaDelegate {
	
	private static final String targetURL = "http://localhost:8040/ClaimsSystem/claimsTask";

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("执行ClaimsTaskExcuteService  开始");
		String processInstanceId = execution.getProcessInstanceId();
		System.out.println("执行serviceTask   结束" + "processInstanceId==" + processInstanceId);
	}

}
