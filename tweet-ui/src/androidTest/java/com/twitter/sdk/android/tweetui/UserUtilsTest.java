package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.TwitterAndroidTestCase;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.models.UserBuilder;

public class UserUtilsTest extends TwitterAndroidTestCase {
    private User user;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        user = new UserBuilder()
                .setId(1)
                .setProfileImageUrlHttps(
                        "https://pbs.twimg.com/profile_images/2284174872/7df3h38zabcvjylnyfe3_normal.png"
                ).build();
    }

    public void testGetProfileImageUrlHttps_nullSize() {
        assertEquals(user.profileImageUrlHttps,
                UserUtils.getProfileImageUrlHttps(user, null));
    }

    public void testGetProfileImageUrlHttps_reasonablySmall() {
        final String reasonableSize = "https://pbs.twimg.com/profile_images/2284174872/" +
                  "7df3h38zabcvjylnyfe3_reasonably_small.png";
        assertEquals(reasonableSize,
                UserUtils.getProfileImageUrlHttps(user, UserUtils.AvatarSize.REASONABLY_SMALL));
    }

    public void testFormatScreenName_alreadyFormatted() {
        final String test = "@test";
        assertEquals(test, UserUtils.formatScreenName(test));
    }

    public void testFormatScreenName() {
        final String test = "@test";
        assertEquals("@test", UserUtils.formatScreenName(test));
    }
}
