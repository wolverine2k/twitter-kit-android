package com.twitter.sdk.android.core;

import java.util.Map;

/**
 * SessionManager for managing sessions.
 */
public interface SessionManager<T extends Session> {

    /**
     * @return the active session, restoring saved session if available
     */
    T getActiveSession();

    /**
     * Sets the active session.
     */
    void setActiveSession(T session);

    /**
     * Clears the active session.
     */
    void clearActiveSession();

    /**
     * @return the session associated with the id.
     */
    T getSession(long id);

    /**
     * Sets the session to associate with the id. If there is no active session, this session also
     * becomes the active session.
     */
    void setSession(long id, T session);

    /**
     * Clears the session associated with the id.
     */
    void clearSession(long id);

    /**
     * @return the session map containing all managed sessions
     */
    Map<Long, T> getSessionMap();
}
