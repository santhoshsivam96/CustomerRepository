package com.infy.utility;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

@Component 
@Aspect
public class LoggingAspect {
	public static final Log LOGGER=LogFactory.getLog(LoggingAspect.class);
	
	/*
	 * @Around("execution(* com.infy.api.*.*(..))") public Object
	 * logAroundAllMethods(ProceedingJoinPoint point) throws Throwable { long
	 * startTime=System.currentTimeMillis(); String
	 * className=point.getSignature().getDeclaringTypeName(); String
	 * method=point.getSignature().getName();
	 * LOGGER.info(className+"--"+method+"--"+" Entering into "
	 * +method+" method with params "+Arrays.toString(point.getArgs())); Object
	 * connect=point.proceed(); long endTime=System.currentTimeMillis();
	 * LOGGER.info(className+"--"+method+"--"+" Exiting the "+method+" with result "
	 * +connect+ "Spent Time: "+(endTime-startTime)+"ms"); return connect; }
	 */
	@AfterThrowing(pointcut = "execution(* com.infy.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception) throws Exception {
		LOGGER.error(exception.getMessage(), exception);
	}
	@Around("execution(* com.infy.*.*.*(..))")
	public Object logAroundMethods(ProceedingJoinPoint point) throws Throwable {
	long startTime=System.currentTimeMillis(); Object connect=null;
	String className=point.getSignature().getDeclaringTypeName();
	String method=point.getSignature().getName();
	String result=" method with params ";//+Arrays.toString(point.getArgs());
	LOGGER.info(className+"--"+method+"--"+" Entering into "+method+result);
	connect=point.proceed();
	long endTime=System.currentTimeMillis();
	LOGGER.info(className+"--"+method+"--"+" Exiting the "+method+" with result "+connect+ "Spent Time: "+(endTime-startTime)+"ms");
	return connect;
	}
  
}
