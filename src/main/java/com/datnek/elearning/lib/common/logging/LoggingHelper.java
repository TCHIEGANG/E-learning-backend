package com.datnek.elearning.lib.common.logging;


import com.datnek.elearning.lib.common.enumeration.ServiceResponseCodeEnum;
import com.datnek.elearning.lib.common.exception.ActionNotAllowedException;
import com.datnek.elearning.lib.common.exception.DatabaseRequestException;
import com.datnek.elearning.lib.common.exception.InvalidParameterException;
import com.datnek.elearning.lib.common.wrapper.GenericMessageDetails;

public class LoggingHelper {

    public static LoggableList<GenericMessageDetails> buildGenericMessageDetails(Exception e){
        return buildGenericMessageDetails(e, null, null);
    }

    /**
     * build details of messages when exception
     *
     * @param e thrown exception
     * @param object object to be checked
     * @param field field in error
     * @return list of messages
     */
    public static LoggableList<GenericMessageDetails> buildGenericMessageDetails(Exception e, Loggable object, String field){
        LoggableList<GenericMessageDetails> messageDetailsList = new LoggableList<>();
        GenericMessageDetails messageDetails = new GenericMessageDetails();
        if (e instanceof InvalidParameterException){
            messageDetails.setCode(ServiceResponseCodeEnum.INVALID_DATA);
        }else if (e instanceof DatabaseRequestException){
            messageDetails.setCode(ServiceResponseCodeEnum.DATA_NOT_FOUND);
        }else if (e instanceof ActionNotAllowedException){
            messageDetails.setCode(ServiceResponseCodeEnum.ACTION_NOT_ALLOWED);
        }else {
            messageDetails.setCode(ServiceResponseCodeEnum.INTERNAL_SERVER_ERROR);
        }
        messageDetails.setMessage(e.getMessage());
        messageDetails.setField(field);
        messageDetails.setObject(object);

        messageDetailsList.add(messageDetails);
        return messageDetailsList;
    }
}
