package com.dwitech.kc.cxs.frontend.security;

import com.dwitech.kc.cxs.frontend.entity.db.User;
import io.quarkiverse.renarde.security.RenardeSecurity;
import io.quarkiverse.renarde.security.RenardeUser;
import io.quarkiverse.renarde.security.RenardeUserProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserSecuritySetup implements RenardeUserProvider {
	@Inject RenardeSecurity security;

	@Override
	public RenardeUser findUser(final String tenantId, final String username) {
		return User.findByUsername(username);

	}
}