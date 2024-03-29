/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.kiwi.myapplication;

import com.example.kiwi.myapplication.data.FakeNotesServiceApiImpl;
import com.example.kiwi.myapplication.data.NoteRepositories;
import com.example.kiwi.myapplication.data.NotesRepository;
import com.example.kiwi.myapplication.util.FakeImageFileImpl;
import com.example.kiwi.myapplication.util.ImageFile;

/**
 * Enables injection of mock implementations for {@link ImageFile} and
 * {@link NotesRepository} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static NotesRepository provideNotesRepository() {
        return NoteRepositories.getInMemoryRepoInstance(new FakeNotesServiceApiImpl());
    }

    public static ImageFile provideImageFile() {
        return new FakeImageFileImpl();
    }
}
