package com.dwitech.kc.cxs.frontend.entity.response;


import jakarta.ws.rs.core.Response;

import static jakarta.json.bind.JsonbBuilder.create;

public class GatewayResponse {
	public boolean success = false;
	public String errorMessage;
	public String internalCode;
	public int httpCode;
	public Object entity;

	@Override
	public String toString() {
		return create().toJson(this);
	}

	public static class Builder {
		private boolean success = false;
		private String errorMessage;
		private String internalCode;
		private Response.Status status;
		private Object entity;

		public Builder withException(GatewayException exc) {
			this.errorMessage = exc.getMessage();
			this.internalCode = exc.internalCode;
			this.status = exc.httpStatus;
			return this;
		}

		public Builder withErrorCode(String internalCode) {
			this.internalCode = internalCode;
			return this;
		}

		public Builder withEntity(Object entity) {
			this.entity = entity;
			this.success = true;
			return this;
		}

		public GatewayResponse build(Response.Status status) {
			GatewayResponse response = new GatewayResponse();
			response.success = this.success;
			response.errorMessage = this.errorMessage;
			response.internalCode = this.internalCode;
			response.httpCode = status.getStatusCode();
			response.entity = this.entity;
			return response;
		}

		public GatewayResponse build() {
			GatewayResponse response = new GatewayResponse();
			response.success = this.success;
			response.errorMessage = this.errorMessage;
			response.internalCode = this.internalCode;
			response.httpCode = status.getStatusCode();
			response.entity = this.entity;
			return response;
		}
	}
}