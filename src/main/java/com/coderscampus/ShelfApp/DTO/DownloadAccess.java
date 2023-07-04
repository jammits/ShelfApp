package com.coderscampus.ShelfApp.DTO;

public class DownloadAccess {
    private String kind;
    private String volumeId;
    private Boolean restricted;
    private Boolean deviceAllowed;
    private Boolean justAcquired;
    private Integer maxDownloadDevices;
    private Integer downloadsAcquired;
    private String nonce;
    private String source;
    private String reasonCode;
    private String message;
    private String signature;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public Boolean getRestricted() {
        return restricted;
    }

    public void setRestricted(Boolean restricted) {
        this.restricted = restricted;
    }

    public Boolean getDeviceAllowed() {
        return deviceAllowed;
    }

    public void setDeviceAllowed(Boolean deviceAllowed) {
        this.deviceAllowed = deviceAllowed;
    }

    public Boolean getJustAcquired() {
        return justAcquired;
    }

    public void setJustAcquired(Boolean justAcquired) {
        this.justAcquired = justAcquired;
    }

    public Integer getMaxDownloadDevices() {
        return maxDownloadDevices;
    }

    public void setMaxDownloadDevices(Integer maxDownloadDevices) {
        this.maxDownloadDevices = maxDownloadDevices;
    }

    public Integer getDownloadsAcquired() {
        return downloadsAcquired;
    }

    public void setDownloadsAcquired(Integer downloadsAcquired) {
        this.downloadsAcquired = downloadsAcquired;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
