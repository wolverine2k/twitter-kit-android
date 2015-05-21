package com.digits.sdk.android;

import android.annotation.SuppressLint;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;

class ContactsPreferenceManager {
    static final String PREFKEY_CONTACTS_IMPORT_PERMISSION = "CONTACTS_IMPORT_PERMISSION";
    static final String PREFKEY_CONTACTS_READ_TIMESTAMP = "CONTACTS_READ_TIMESTAMP";
    static final String PREFKEY_CONTACTS_UPLOADED = "CONTACTS_CONTACTS_UPLOADED";

    final private PreferenceStore prefStore;

    ContactsPreferenceManager() {
        prefStore = new PreferenceStoreImpl(Fabric.getKit(Digits.class));
    }

    @SuppressLint("CommitPrefEdits")
    protected boolean hasContactImportPermissionGranted() {
        return prefStore.get().getBoolean(PREFKEY_CONTACTS_IMPORT_PERMISSION, false);
    }

    @SuppressLint("CommitPrefEdits")
    protected void setContactImportPermissionGranted() {
        prefStore.save(prefStore.edit().putBoolean(PREFKEY_CONTACTS_IMPORT_PERMISSION, true));
    }

    @SuppressLint("CommitPrefEdits")
    protected void clearContactImportPermissionGranted() {
        prefStore.save(prefStore.edit().remove(PREFKEY_CONTACTS_IMPORT_PERMISSION));
    }

    @SuppressLint("CommitPrefEdits")
    protected void setContactsReadTimestamp(long timestamp) {
        prefStore.save(prefStore.edit().putLong(PREFKEY_CONTACTS_READ_TIMESTAMP, timestamp));
    }

    @SuppressLint("CommitPrefEdits")
    protected void setContactsUploaded(int count) {
        prefStore.save(prefStore.edit().putInt(PREFKEY_CONTACTS_UPLOADED, count));
    }
}
