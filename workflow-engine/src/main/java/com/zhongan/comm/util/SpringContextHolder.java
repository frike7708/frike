package com.zhongan.comm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 * 
 * @author Zaric
 * @date 2013-5-29 下午1:25:40
 */
@Component
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

	private static ApplicationContext applicationContext = null;
	private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

	public SpringContextHolder()
	{
		logger.debug("SpringContextHolder  init");
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return assertContextInjected() ? (T) applicationContext.getBean(name) : null;
	}


	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		return assertContextInjected()? applicationContext.getBean(requiredType):null;
	}

	/**
	 *从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * @param requiredType
	 * @param <T>
     * @return
     */
	public static <T> Map<String,T> getBeans(Class<T> requiredType) {
		return assertContextInjected()? applicationContext.getBeansOfType(requiredType):null;
	}

	/**
	 *使用名称，Bean构造参数获取
	 * @param name
	 * @param args
	 * @return Object
	 * @throws BeansException
     */
	public static Object getBean(String name, Object... args) throws BeansException {
		return assertContextInjected() ? applicationContext.getBean(name,args):null;
	}


	/**
	 * 使用类型和取Bean构造参数获取
	 */
	public static <T> T  getBean(Class<T> requiredType, Object... args) throws BeansException
	{
		return assertContextInjected() ? applicationContext.getBean(requiredType,args):null;
	}

	/**
	 * 清除SpringContextHolder中的ApplicationContext为Null.
	 */
	public static void clearHolder() {
		if (logger.isDebugEnabled()){
			logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
		}
		applicationContext = null;
	}

	/**
	 * 是否存在Bean
	 * @param name
     */
	public static boolean containsBean(String name)
	{
		return  assertContextInjected() ? applicationContext.containsBean(name):false;
	}


	/**
	 * 检查ApplicationContext不为空.
	 */
	private static boolean assertContextInjected() {
		if(null==applicationContext)
		{
			logger.warn("applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
			return false;
		}
		return true;
	}

	/**
	 * 初始化并注入Bean
	 * @param beanName
	 * @param beanClass
	 * @param propertys
	 * @throws Exception
	 */
	public static void addBean(String beanName,Class<?> beanClass, Map<String,Object> propertys)throws Exception {
		if (!containsBean(beanName)) {
			BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
			if (propertys != null) {
				for (Map.Entry<String, Object> entry : propertys.entrySet()) {
					beanDefinitionBuilder.addPropertyValue(entry.getKey(), entry.getValue());
				}
			}
			registerBean(beanName, beanDefinitionBuilder.getRawBeanDefinition());
		}
	}

	/**
	 * 初始化并注入Bean
	 * @param className
	 * @param propertys
	 * @throws Exception
	 */
	public static void addBean(String beanName,String className, Map<String,Object> propertys)throws Exception {
		Class<?> beanclass = getClass(className);
		if (beanclass!=null) {
			addBean(beanName,beanclass,propertys);
		}
	}

	/**
	 * 初始化并注入Bean
	 * @param className
	 * @param propertys
	 * @throws Exception
     */
	public static void addBean(String className, Map<String,Object> propertys)throws Exception {
		addBean(className,className,propertys);
	}

	/**
	 * @desc 向spring容器注册bean
	 * @param beanName
	 * @param beanDefinition
	 */
	private static void registerBean(String beanName, BeanDefinition beanDefinition) {
		if( assertContextInjected()) {
			DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory(applicationContext.getParentBeanFactory());
			beanFactory.registerBeanDefinition(beanName, beanDefinition);
		}
	}

	/**
	 *  @desc 向spring容器注册已初始化bean,使用Singleton
	 * @param name
	 * @param bean
     */
	public static void registerBean(String name,Object bean) {
		if( assertContextInjected()) {
			AbstractRefreshableConfigApplicationContext webApplicationContext = (AbstractRefreshableConfigApplicationContext) applicationContext;
			webApplicationContext.getBeanFactory().registerSingleton(name, bean);
		}
	}

	/**
	 * @desc 根据类名查找class
	 * @param className
	 * @return
	 * @throws Exception
	 */
	private static Class<?> getClass(String className) throws Exception {

		try {
			return Thread.currentThread().getContextClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			logger.error("not found service class:" + className, e);
			throw new Exception("not found service class:" + className, e);
		}
	}

	/**
	 * 获得i18n字符串
	 */
	public static String getMessage(String code, Object[] args) {
		LocaleResolver localLocaleResolver = (LocaleResolver) SpringContextHolder.getBean(LocaleResolver.class);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Locale localLocale = localLocaleResolver.resolveLocale(request);
		return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
	}

	/**
	 * 实现ApplicationContextAware接口, 注入Context到静态变量中.
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		logger.debug("注入ApplicationContext到SpringContextHolder:{}", applicationContext);
		if (SpringContextHolder.applicationContext != null) {
			logger.info("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
		}
		//
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * 实现DisposableBean接口, 在Context关闭时清理静态变量.
	 */
	@Override
	public void destroy() throws Exception {
		SpringContextHolder.clearHolder();
	}
}
