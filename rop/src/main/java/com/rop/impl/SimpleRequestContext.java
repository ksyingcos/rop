/**
 *
 * 日    期：12-2-27
 */
package com.rop.impl;

import com.rop.*;
import com.rop.annotation.HttpAction;
import com.rop.session.Session;
import com.rop.session.SessionManager;
import com.rop.validation.MainError;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author 陈雄华
 * @version 1.0
 */
public class SimpleRequestContext implements RequestContext {

    public static final String SPRING_VALIDATE_ERROR_ATTRNAME = "$SPRING_VALIDATE_ERROR_ATTRNAME";

    private RopContext ropContext;

    private String method;

    private String version;

    private String appKey;

    private String sessionId;

    private Locale locale;

    private String format;

    public static ThreadLocal<MessageFormat> messageFormat = new ThreadLocal<MessageFormat>();

    private String sign;

    private Map<String, Object> attributes = new HashMap<String, Object>();

    private ServiceMethodHandler serviceMethodHandler;

    private MainError mainError;

    private RopResponse ropResponse;

    private RopRequest ropRequest;

    private long serviceBeginTime = -1;

    private long serviceEndTime = -1;

    private String ip;

    private HttpAction httpAction;

    private Object rawRequestObject;

    private Map<String, String> allParams;

    private SessionManager sessionManager;

    @Override
    public long getServiceBeginTime() {
        return this.serviceBeginTime;
    }

    @Override
    public long getServiceEndTime() {
        return this.serviceEndTime;
    }

    @Override
    public void setServiceBeginTime(long serviceBeginTime) {
        this.serviceBeginTime = serviceBeginTime;
    }

    @Override
    public void setServiceEndTime(long serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    @Override
    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public Object getRawRequestObject() {
        return this.rawRequestObject;
    }

    public void setRawRequestObject(Object rawRequestObject) {
        this.rawRequestObject = rawRequestObject;
    }

    public SimpleRequestContext(RopContext ropContext) {
        this.ropContext = ropContext;
        this.serviceBeginTime = System.currentTimeMillis();
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public RopContext getRopContext() {
        return ropContext;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getSessionId() {
        return this.sessionId;
    }

    @Override
    public Session getSession() {
        return sessionManager.getSession(getSessionId());
    }

    @Override
    public void addSession(String sessionId,Session session) {
        sessionManager.addSession(sessionId,session);
    }

    @Override
    public void removeSession() {
        sessionManager.removeSession(getSessionId());
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

    public ServiceMethodHandler getServiceMethodHandler() {
        return this.serviceMethodHandler;
    }

    @Override
    public MessageFormat getMessageFormat() {
        return messageFormat.get();
    }

    @Override
    public RopResponse getRopResponse() {
        return this.ropResponse;
    }

    @Override
    public RopRequest getRopRequest() {
        return this.ropRequest;
    }

    @Override
    public void setRopRequest(RopRequest ropRequest) {
        this.ropRequest = ropRequest;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setServiceMethodHandler(ServiceMethodHandler serviceMethodHandler) {
        this.serviceMethodHandler = serviceMethodHandler;
    }

    public void setMessageFormat(MessageFormat messageFormat) {
        this.messageFormat.set(messageFormat);
    }

    @Override
    public void setRopResponse(RopResponse ropResponse) {
        this.ropResponse = ropResponse;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setMainError(MainError mainError) {
        this.mainError = mainError;
    }

    public MainError getMainError() {
        return this.mainError;
    }

    @Override
    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean isSignEnable() {
        return ropContext.isSignEnable() && !getServiceMethodDefinition().isIgnoreSign();
    }

    @Override
    public ServiceMethodDefinition getServiceMethodDefinition() {
        return serviceMethodHandler.getServiceMethodDefinition();
    }

    @Override
    public Map<String, String> getAllParams() {
        return this.allParams;
    }

    public void setAllParams(Map<String, String> allParams) {
        this.allParams = allParams;
    }

    @Override
    public String getParamValue(String paramName) {
        if (allParams != null) {
            return allParams.get(paramName);
        } else {
            return null;
        }
    }

    public void setHttpAction(HttpAction httpAction) {
        this.httpAction = httpAction;
    }

    @Override
    public HttpAction getHttpAction() {
        return this.httpAction;
    }
}

