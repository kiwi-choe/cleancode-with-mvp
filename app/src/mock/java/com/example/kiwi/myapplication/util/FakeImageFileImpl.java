package com.example.kiwi.myapplication.util;

import com.example.kiwi.myapplication.util.ImageFile;

import java.io.IOException;

/**
 * Created by kiwi on 6/7/16.
 *
 * Fake implementation of {@link ImageFile} to inject a fake image in a hermetic test.
 */
public class FakeImageFileImpl extends ImageFileImpl {

    @Override
    public void create(String name, String extension) throws IOException {

        // Do nothing
    }

    @Override
    public String getPath() {
        return "file://android_asset/atsl-logo.png";
    }

    @Override
    public boolean exists() {
        return true;
    }
}
