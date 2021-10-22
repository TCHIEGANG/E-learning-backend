package com.datnek.elearning.lib.common.enumeration;

/**
 * Enum contenant les code erreurs classiques / standards / globaux.
 */
public enum ServiceResponseCodeEnum implements ServiceResponseCode {

    ACTION_NOT_ALLOWED("ACTION_NOT_ALLOWED"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    BAD_REQUEST("BAD_REQUEST"),
    INVALID_DATA("INVALID_DATA"),
    DATA_NOT_FOUND("DATA_NOT_FOUND");

    private String code;

    ServiceResponseCodeEnum(String code) {
        this.code = code;
    }

    @Override
    public String getServiceResponseCode() {
        return code;
    }
}
