package com.zhongan.workflow.listen;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;

public class WorkApplyListener implements ExecutionListener,TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		
	}

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		//执行
		System.out.println("======************=======");
		
	}

}
