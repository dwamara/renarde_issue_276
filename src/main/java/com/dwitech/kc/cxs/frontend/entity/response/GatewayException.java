package com.dwitech.kc.cxs.frontend.entity.response;

import jakarta.ws.rs.core.Response.Status;

import static jakarta.json.bind.JsonbBuilder.create;

public class GatewayException extends Exception {
    public String internalCode;
    public Status httpStatus;

    public GatewayException(final String internalCode, final String message) {
        super(message);
        this.internalCode = internalCode;
    }

    @Override
    public String toString() {
        return create().toJson(this);
    }
}