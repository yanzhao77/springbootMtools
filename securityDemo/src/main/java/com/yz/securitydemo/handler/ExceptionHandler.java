package com.yz.securitydemo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        // TODO Auto-generated method stub
        logger.error(ex.getMessage(), ex);

        ModelAndView modelAndView = new ModelAndView();
        String errorMsg = ex.getMessage();


        if (ex instanceof NullPointerException) {
            modelAndView.addObject("msg", errorMsg);
            modelAndView.setViewName("BaseError");
        } else if (ex instanceof ArithmeticException) {
            modelAndView.addObject("msg", "除零异常");
            modelAndView.addObject("code", "500");
            modelAndView.addObject("text", "You don't have permission to access the URL on this server.");
            modelAndView.setViewName("BaseError");
        }
        return modelAndView;
    }

}
