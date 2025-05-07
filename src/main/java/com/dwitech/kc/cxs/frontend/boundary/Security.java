package com.dwitech.kc.cxs.frontend.boundary;


import com.dwitech.kc.cxs.frontend.entity.db.User;
import io.micrometer.core.instrument.MeterRegistry;
import io.quarkiverse.renarde.security.ControllerWithUser;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import static com.dwitech.kc.cxs.frontend.control.CurrentMethod.name;
import static io.quarkiverse.renarde.router.Router.getURI;
import static io.quarkus.elytron.security.common.BcryptUtil.matches;
import static jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static org.jboss.logging.Logger.getLogger;

@Path("/security")
@Blocking
public class Security extends ControllerWithUser<User> {
	private final MeterRegistry registry;

	Security(MeterRegistry registry) {
		this.registry = registry;
	}

	public TemplateInstance display_login() {
		return Templates.login();
	}

	@POST
	@Path("login")
	@Consumes(APPLICATION_FORM_URLENCODED)
	public Response login(@FormParam("username") String username, @FormParam("password") String password) {
		System.out.println("Login triggered: " + username);
		var action = name();
		var user = User.findByUsername(username);
		if (user == null || !matches(password, user.credentials.password) || !user.registered()) {
			System.out.println("username --> Invalid username/password");
			validation.addError("username", "Invalid username/password");
			prepareForErrorRedirect();
			display_login();
		}

		var cookie = security.makeUserCookie(user);
		TemplateInstance template = Templates.ok_login();
		return Response.ok(template
				.data("source", action)
				.data("user", user)
		).cookie(cookie).build();
	}

	@CheckedTemplate(requireTypeSafeExpressions = false)
	public static class Templates {
		public static native TemplateInstance login();
		public static native TemplateInstance ok_login();
	}
}