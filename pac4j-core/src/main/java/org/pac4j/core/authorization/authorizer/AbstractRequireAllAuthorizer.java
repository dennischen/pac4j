package org.pac4j.core.authorization.authorizer;

import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.UserProfile;

/**
 * An authorizer to require all the elements.
 *
 * @author Jerome Leleu
 * @since 1.8.1
 */
public abstract class AbstractRequireAllAuthorizer<E extends Object, U extends UserProfile> extends AbstractRequireElementAuthorizer<E, U> {

    @Override
    public boolean isAuthorized(final WebContext context, final U profile) {
        if (elements == null || elements.isEmpty()) {
            return true;
        }
        for (final E element : elements) {
            if (!check(context, profile, element)) {
                return false;
            }
        }
        return true;
    }
}
