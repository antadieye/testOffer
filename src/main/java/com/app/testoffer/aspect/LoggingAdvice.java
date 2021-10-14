package com.app.testoffer.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author A697004
 *
 */
@Aspect
@Component
public class LoggingAdvice {
	
	Logger log=LoggerFactory.getLogger(LoggingAdvice.class);
	
	Jackson2ObjectMapperBuilder mapperBuilder;
	
	public LoggingAdvice(Jackson2ObjectMapperBuilder mapperBuilder) {
	this.mapperBuilder = mapperBuilder;
}

	@Pointcut(value="execution(* com.app.testoffer.*.*.*(..))")
	public void myPointcut() {
		
	}
	
	/**
	 * to log inputs and outputs of each call as well as the processing time
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		
		ObjectMapper mapper = mapperBuilder.build();
		String methodName=pjp.getSignature().getName();
		String className=pjp.getTarget().getClass().toString();
		Object[] array=pjp.getArgs();
		log.info("method invoked "+className+" : "+methodName+"()"+ "arguments : "
				+ mapper.writeValueAsString(array));
		
		Object object =pjp.proceed();
		log.info(className+" : "+methodName+"()"+ "Response : "
				+ mapper.writeValueAsString(object));
		
		return object;
		
	}

}
