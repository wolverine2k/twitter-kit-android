package com.twitter.sdk.android.tweetui.internal;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;

import java.util.List;

/**
 * Mockable class that simplifies code that depends on sessions but is agnostic to session type.
 *
 * Note, the order of the SessionManager list is important if one type is more desirable to use
 * than another.
 *
 * TODO Remove implicit ordering and codify session type preference as a testable sort order
 */
public class ActiveSessionProvider {

    private final List<SessionManager<? extends  Session>> sessionManagers;

    public ActiveSessionProvider(List<SessionManager<? extends Session>> sessionManagers) {
        this.sessionManagers = sessionManagers;
    }

    public Session getActiveSession() {
        Session session = null;
        for (SessionManager<? extends Session> sessionManager : sessionManagers) {
            session = sessionManager.getActiveSession();
            if (session != null) {
                break;
            }
        }
        return session;
    }
}
