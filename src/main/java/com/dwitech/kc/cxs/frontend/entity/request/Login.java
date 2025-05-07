package com.dwitech.kc.cxs.frontend.entity.request;

import org.jboss.resteasy.reactive.RestForm;

public class Login {
    @RestForm("username") public String username;
    @RestForm("password") public String password;
}