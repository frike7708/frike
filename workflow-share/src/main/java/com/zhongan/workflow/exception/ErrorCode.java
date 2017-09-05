package com.zhongan.workflow.exception;

/**
 * -1 参数异常
 * 
 * -2 未知异常
 * 
 * 关于流程性错误：1000 +
 * 
 * 关于任务型错误：2000 +
 * 
 * @author za-shiyafei
 *
 */
public class ErrorCode {
	
	
	public final static int PARAM_NULL_POINT = -1;
	public final static String PARAM_NULL_POINT_MESSAGE = "参数错误";
	
	public final static int UNKNOW_EXCEPTION = -100;
	public final static String UNKNOW_EXCEPTION_MESSAGE = "未知错误";
	
	
	public final static int PROCESS_ERROR_CODE_100 = 1000;
	public final static String PROCESS_ERROR_100_MESSAGE = "流程启动错误";
	
	public final static int TASK_ERROR_CODE_200 = 2000;
	public final static String TASK_ERROR_CODE_200_MESSAGE = "该任务已经被领取";

}
