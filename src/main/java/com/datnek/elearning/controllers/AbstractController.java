package com.datnek.elearning.controllers;

import com.datnek.elearning.lib.common.exception.GenericException;
import com.datnek.elearning.lib.common.exception.InvalidParameterException;
import com.datnek.elearning.lib.common.logging.LoggingHelper;
import com.datnek.elearning.lib.common.wrapper.GenericResultDto;
import com.datnek.elearning.services.LanguageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {
    
    protected static final String PAGE = "page";
    protected static final String LIMIT = "limit";
    protected static final String CONTENT = "content";
    protected static final String COUNTRY = "country";
    protected static final String STATUS = "status";

    @Autowired
    protected LanguageService languageService;
   

    protected GenericResultDto result = null;
    

    protected ResponseEntity<GenericResultDto> genericResultDtoError(String message, HttpStatus httpStatus){
        InvalidParameterException e = new InvalidParameterException(message);
        result = new GenericResultDto();
        result.setHttpStatus(httpStatus);
        result.setMessage(e.getMessage());
        result.setGenericMessageDetailsList(LoggingHelper.buildGenericMessageDetails(e));
        return new ResponseEntity<>(result, result.getHttpStatus());
    }

    protected GenericResultDto processToken(String token, String id) throws GenericException {
        if (StringUtils.isBlank(token) || StringUtils.isBlank(id)){
            throw new GenericException("Invalid input data");
        }
        result = new GenericResultDto<>();
        return result;
    }

    protected void genericResultDtoSuccess(){
        result.setHttpStatus(HttpStatus.OK);
        result.setMessage("Success");
    }
}
