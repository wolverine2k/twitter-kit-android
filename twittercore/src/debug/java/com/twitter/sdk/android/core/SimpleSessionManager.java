package com.twitter.sdk.android.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleSessionManager<T extends Session> implements SessionManager<T> {
    private HashMap<Long, T> sessionMap;
    private final AtomicReference<T> activeSessionRef;

    public SimpleSessionManager() {
        activeSessionRef = new AtomicReference<T>();
        sessionMap = new HashMap<>();
    }

    @Override
    public T getActiveSession() {
        return activeSessionRef.get();
    }

    @Override
    public void setActiveSession(T session) {
        activeSessionRef.set(session);
    }

    @Override
    public void clearActiveSession() {
        activeSessionRef.set(null);
    }

    @Override
    public T getSession(long id) {
        return sessionMap.get(id);
    }

    @Override
    public void setSession(long id, T session) {
        final T activeSession = activeSessionRef.get();
        if (activeSession == null) {
            activeSessionRef.compareAndSet(null, session);
        }
        sessionMap.put(id, session);
    }

    @Override
    public void clearSession(long id) {
        sessionMap.remove(id);
    }

    @Override
    public Map<Long, T> getSessionMap() {
        return Collections.unmodifiableMap(sessionMap);
    }
}
