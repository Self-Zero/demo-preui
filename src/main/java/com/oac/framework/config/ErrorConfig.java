package com.oac.framework.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/errorPageController/error_400");
        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/errorPageController/error_401");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/errorPageController/error_404");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorPageController/error_500");
        registry.addErrorPages(error400Page,error401Page,error404Page,error500Page);
    }
}
