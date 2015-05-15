package com.twitter.sdk.android.tweetui;


import android.text.TextUtils;

import com.twitter.sdk.android.core.models.User;

final class UserUtils {
    private UserUtils() {}

    // see https://dev.twitter.com/docs/user-profile-images-and-banners
    // see also: https://confluence.twitter.biz/display/PLATFORM/Image+Types+and+Sizes
    enum AvatarSize {
        NORMAL("_normal"),
        BIGGER("_bigger"),
        MINI("_mini"),
        ORIGINAL("_original"),
        REASONABLY_SMALL("_reasonably_small");

        private final String suffix;

        private AvatarSize(String suffix) {
            this.suffix = suffix;
        }

        String getSuffix() {
            return suffix;
        }
    }

    static String getProfileImageUrlHttps(User user, AvatarSize size) {
        if (user != null && user.profileImageUrlHttps != null) {
            final String url = user.profileImageUrlHttps;
            if (size == null || url == null) {
                return url;
            }

            switch (size) {
                case NORMAL:
                case BIGGER:
                case MINI:
                case ORIGINAL:
                case REASONABLY_SMALL:
                    return url
                            .replace(AvatarSize.NORMAL.getSuffix(), size.getSuffix());
                default:
                    return url;
            }
        } else {
            return null;
        }
    }

    /**
     * @return the given screenName, prepended with an "@"
     */
    static CharSequence formatScreenName(CharSequence screenName) {
        if (TextUtils.isEmpty(screenName)) {
            return "";
        }

        if (screenName.charAt(0) == '@') {
            return screenName;
        }
        return "@" + screenName;
    }
}
