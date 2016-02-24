package org.pac4j.oauth.run;

import com.esotericsoftware.kryo.Kryo;
import org.pac4j.core.client.IndirectClient;
import org.pac4j.core.run.RunClient;
import org.pac4j.core.profile.Gender;
import org.pac4j.core.profile.ProfileHelper;
import org.pac4j.core.profile.UserProfile;
import org.pac4j.core.util.CommonHelper;
import org.pac4j.oauth.client.DropBoxClient;
import org.pac4j.oauth.profile.dropbox.DropBoxProfile;

import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Run manually a test for the {@link DropBoxClient}.
 *
 * @author Jerome Leleu
 * @since 1.9.0
 */
public final class RunDropboxClient extends RunClient {

    public static void main(String[] args) throws Exception {
        new RunDropboxClient().run();
    }

    @Override
    protected String getLogin() {
        return "testscribeup@gmail.com";
    }

    @Override
    protected String getPassword() {
        return "testpwdscribeup";
    }

    @Override
    protected IndirectClient getClient() {
        final DropBoxClient dropBoxClient = new DropBoxClient();
        dropBoxClient.setKey("0194c6m79qll0ia");
        dropBoxClient.setSecret("a0ylze9a0bhsvxv");
        dropBoxClient.setCallbackUrl(GOOGLE_URL);
        return dropBoxClient;
    }

    @Override
    protected void registerForKryo(final Kryo kryo) {
        kryo.register(DropBoxProfile.class);
    }

    @Override
    protected void verifyProfile(UserProfile userProfile) {
        final DropBoxProfile profile = (DropBoxProfile) userProfile;
        assertEquals("75206624", profile.getId());
        assertEquals(DropBoxProfile.class.getName() + UserProfile.SEPARATOR + "75206624", profile.getTypedId());
        assertTrue(ProfileHelper.isTypedIdOf(profile.getTypedId(), DropBoxProfile.class));
        assertTrue(CommonHelper.isNotBlank(profile.getAccessToken()));
        assertCommonProfile(userProfile, null, null, null, "Test ScribeUP", null, Gender.UNSPECIFIED, Locale.FRENCH,
                null, "https://db.tt/RvmZyvJa", null);
        assertEquals(0L, profile.getShared().longValue());
        assertEquals(1410412L, profile.getNormal().longValue());
        assertEquals(2147483648L, profile.getQuota().longValue());
        assertNotNull(profile.getAccessSecret());
        assertEquals(8, profile.getAttributes().size());
    }
}
