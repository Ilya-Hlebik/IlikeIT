package com.idglebik.ilikeit.config;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Locale;


@Setter
@Getter
@ApiModel
public class Response<T> {

    public static final Response AUTH_REQUERED_ERROR = Response.error("Необходимо авторизоваться");
    public static final Response AUTH_FORBIDDEN_ERROR = Response.error("Недостаточно прав");
    public static final Response WEB_SERVICE_ERROR = Response.error("Ошибка во время запроса к внешнему сервису");
    public static final Response WEB_SERVICE_PROCESSING_REQUEST = Response.error("Ошибка во время обработки ответа");
    private static String SUCCESS_TEXT = "Операция выполнена успешно";// mh.getMessage("response.common.success", locale)
    private Boolean success;
    private String message;
    private MultiValueMap<String, String> errors;
    private Long total;
    private T data;

    private Response() {
    }

    public static Response<Object> error(Errors result, MessageSource ms, Locale locale) {
        Response<Object> r = new Response<Object>();
        r.setSuccess(false);
        MultiValueMap<String, String> errors = new LinkedMultiValueMap<String, String>();

        if (result.hasGlobalErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError oe : result.getGlobalErrors()) {
                sb.append(ms.getMessage(oe, locale)).append(" ");
            }
            r.setMessage(sb.toString());
        }
        for (FieldError fe : result.getFieldErrors()) {
            errors.add(fe.getField(), ms.getMessage(fe, locale));
        }
        r.setErrors(errors);
        return r;
    }

    public static Response<Object> error(Errors result) {
        Response<Object> r = new Response<Object>();
        r.setSuccess(false);
        MultiValueMap<String, String> errors = new LinkedMultiValueMap<String, String>();

        if (result.hasGlobalErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError oe : result.getGlobalErrors()) {
                sb.append(oe.getDefaultMessage()).append(" ");
            }
            r.setMessage(sb.toString());
        }
        for (FieldError fe : result.getFieldErrors()) {
            errors.add(fe.getField(), fe.getDefaultMessage());
        }
        r.setErrors(errors);
        return r;
    }

    public static <T> Response<T> success(T data) {
        Response<T> r = new Response<T>();
        r.setSuccess(true);
        r.setData(data);
        r.setMessage(SUCCESS_TEXT);
        return r;
    }

    public static <T> Response<T> success(T data, Long total) {
        Response<T> r = new Response<T>();
        r.setSuccess(true);
        r.setData(data);
        r.setTotal(total);
        r.setMessage(SUCCESS_TEXT);
        return r;
    }

    public static <T> Response<T> error(T data) {
        Response<T> r = new Response<T>();
        r.setSuccess(false);
        r.setData(data);
        return r;
    }

    public static <T> Response<T> success(T data, String message) {
        Response<T> r = new Response<T>();
        r.setSuccess(true);
        r.setData(data);
        r.setMessage(message == null ? SUCCESS_TEXT : message);
        return r;
    }

    public static <T> Response<T> error(T data, String message) {
        Response<T> r = new Response<T>();
        r.setSuccess(false);
        r.setData(data);
        r.setMessage(message);
        return r;
    }

    public static Response success(String msg) {
        Response r = new Response<Object>();
        r.setSuccess(true);
        r.setMessage(msg);
        return r;
    }

    public static Response error(String msg) {
        Response r = new Response<Object>();
        r.setSuccess(false);
        r.setMessage(msg);
        return r;
    }

    public static Response success() {
        Response r = new Response<Object>();
        r.setSuccess(true);
        r.setMessage(SUCCESS_TEXT);
        return r;
    }
}