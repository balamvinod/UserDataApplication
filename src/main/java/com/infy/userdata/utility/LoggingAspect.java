package com.infy.userdata.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.infy.userdata.exception.UserDataException;

@Component
@Aspect
public class LoggingAspect
{

    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.infy.userdata.service.UserDataServiceImpl.*(..))", throwing = "exception")
    public void logServiceException(UserDataException exception)
    {
    	LOGGER.error(exception.getMessage(),exception);

    }

}
