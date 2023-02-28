package com.ifbhotels.ebmanagement.services.storage;

import com.ifbhotels.ebmanagement.enums.StorageServiceType;
import junit.framework.TestCase;

public class StorageServiceFactoryTest extends TestCase {

    private StorageService storageService;

    @Override
    protected void setUp() throws Exception {
        storageService = StorageServiceFactory.getStorageService(StorageServiceType.SIMPLE_STORAGE_TYPE);
        super.setUp();
    }

    public void testGetStorageService() {
        assertEquals(storageService,
                StorageServiceFactory.getStorageService(StorageServiceType.SIMPLE_STORAGE_TYPE));
    }
}