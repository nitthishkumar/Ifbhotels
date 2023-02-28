package com.ifbhotels.ebmanagement.services.storage;

import static com.ifbhotels.ebmanagement.constants.Constants.SIMPLE_STORAGE_TYPE;

public final class StorageServiceFactory {

    private StorageServiceFactory() {}

    public static StorageService getStorageService(String storageServiceType) {

        switch (storageServiceType) {
            case SIMPLE_STORAGE_TYPE: return SimpleStorageService.getInstance();

            default: return null;
        }
    }

}

