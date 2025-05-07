package com.dwitech.kc.cxs.frontend;

import com.dwitech.kc.cxs.frontend.boundary.Security;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import jakarta.ws.rs.Path;

public class Application extends Controller {
    @Path("/")
    public TemplateInstance index() {
        return Security.Templates.login();
    }

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index();
    }
}