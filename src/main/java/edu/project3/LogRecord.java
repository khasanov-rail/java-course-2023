package edu.project3;

import java.time.OffsetDateTime;

public class LogRecord {
    private String remoteAddr;
    private String remoteUser;
    private OffsetDateTime timeLocal;
    private String request;
    private int status;
    private int bodyBytesSent;
    private String httpReferer;
    private String userAgent;

    private LogRecord() {
    }


    public String getRemoteAddr() {
        return remoteAddr;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public OffsetDateTime getTimeLocal() {
        return timeLocal;
    }

    public String getRequest() {
        return request;
    }

    public int getStatus() {
        return status;
    }

    public int getBodyBytesSent() {
        return bodyBytesSent;
    }

    public String getHttpReferer() {
        return httpReferer;
    }

    public String getUserAgent() {
        return userAgent;
    }


    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public void setTimeLocal(OffsetDateTime timeLocal) {
        this.timeLocal = timeLocal;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBodyBytesSent(int bodyBytesSent) {
        this.bodyBytesSent = bodyBytesSent;
    }

    public void setHttpReferer(String httpReferer) {
        this.httpReferer = httpReferer;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }


    public static class Builder {
        private String remoteAddr;
        private String remoteUser;
        private OffsetDateTime timeLocal;
        private String request;
        private int status;
        private int bodyBytesSent;
        private String httpReferer;
        private String userAgent;

        public Builder remoteAddr(String remoteAddr) {
            this.remoteAddr = remoteAddr;
            return this;
        }

        public Builder remoteUser(String remoteUser) {
            this.remoteUser = remoteUser;
            return this;
        }

        public Builder timeLocal(OffsetDateTime timeLocal) {
            this.timeLocal = timeLocal;
            return this;
        }

        public Builder request(String request) {
            this.request = request;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder bodyBytesSent(int bodyBytesSent) {
            this.bodyBytesSent = bodyBytesSent;
            return this;
        }

        public Builder httpReferer(String httpReferer) {
            this.httpReferer = httpReferer;
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public LogRecord build() {
            LogRecord logRecord = new LogRecord();
            logRecord.remoteAddr = this.remoteAddr;
            logRecord.remoteUser = this.remoteUser;
            logRecord.timeLocal = this.timeLocal;
            logRecord.request = this.request;
            logRecord.status = this.status;
            logRecord.bodyBytesSent = this.bodyBytesSent;
            logRecord.httpReferer = this.httpReferer;
            logRecord.userAgent = this.userAgent;
            return logRecord;
        }
    }
}
