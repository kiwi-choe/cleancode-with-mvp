package com.example.kiwi.myapplication.util;

import java.io.IOException;

/**
 * Created by kiwi on 6/7/16.
 */
public interface ImageFile {

    void create(String name, String extension) throws IOException;

    boolean exists();

    void delete();

    String getPath();

}
