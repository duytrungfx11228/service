package com.globits.da.utils;

public enum Error {
    OK(200, ""),
    NAME_ERROR(501, "Name is not empty"),
    CODE_ERROR(502, "code is invalid"),
    PHONE_ERROR(503, "Phone is not empty and (10 <= lenght <= 11)"),
    EMAIL_ERROR(504, "Email is invalid"),
    AGE_ERROR(505, "Age is not negative"),
    PROVINCE_ERROR(506, "Province is not empty"),
    DISTRICT_ERROR(507, "District is not empty"),
    COMMUNE_ERROR(508, "Commune is not empty"),
    DISTRICT_NOT_BELONG_PROVINCE_INVALID(509, "District not belong province"),
    COMMUNE_NOT_BELONG_DISTRICT_INVALID(510, "Commune not belong district"),
    DTO_ERROR(511, "Dto is null");

    private int code;
    private String message;

    Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
