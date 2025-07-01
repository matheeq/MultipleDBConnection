package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {
	Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping(value="/log" , method = RequestMethod.POST)
    public String index() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "Check out the Logs to see the output...";
        
        
    }
    
    public void criterianQuery() {
		/*
		 * CriteriaBuilder builder; CriteriaQuery<Product>
		 * criteriaQuery=builder.createQuery(Product.class); Root<Product>
		 * productRoot=criteriaQuery.from(Product.class);
		 */
    	
    }

}
