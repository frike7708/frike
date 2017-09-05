package com.zhongan.claim.flow.exec;

import com.zhongan.comm.util.SpringContextHolder;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.el.FixedValue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by chenshiyang on 2017/7/7.
 */
public class SpringExecService implements JavaDelegate {

    private String clsz;

    private String method;

    public String getclsz() {
        return clsz;
    }

    public void setClsz(FixedValue clsz) {
        this.clsz = clsz.getExpressionText();
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(FixedValue method) {
        this.method = method.getExpressionText();
    }


    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(DelegateExecution execution) {
        //
        if (StringUtils.isNotBlank(clsz) && StringUtils.isNotBlank(method)) {
            try {
                Class clz = Class.forName(clsz);
                Method mtd = clz.getDeclaredMethod(method, Map.class);
                //
                if (null != clz && null != mtd) {
                    Object target= SpringContextHolder.getBeans(clz); //获取target
                    if (null != target) {
                        mtd.invoke(target, execution.getVariables());
                    }
                    else{
                        logger.error("ServiceTask-SpringExec Spring容器中未找到相应的Bean:{}",clsz);
                    }
                }
            } catch (Exception ex) {
                logger.error("ServiceTask 调用出错:", ex);
            }
        }
        else {
            //
            logger.error("ServiceTask-SpringExec参数丢失，clsz:{},method:{}", clsz, method);
        }
    }


}
