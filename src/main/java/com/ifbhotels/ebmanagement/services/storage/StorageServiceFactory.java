package com.ifbhotels.ebmanagement.services.storage;

import com.ifbhotels.ebmanagement.enums.StorageServiceType;


public final class StorageServiceFactory {

    private StorageServiceFactory() {}

    public static StorageService getStorageService(StorageServiceType storageServiceType) {

        switch (storageServiceType) {
            case SIMPLE_STORAGE_TYPE: return SimpleStorageService.getInstance();
            default: return null;
        }
    }

}

