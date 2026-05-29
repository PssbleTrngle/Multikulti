package com.possible_triangle.multikulti.datagen.test;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.skyscreamer.jsonassert.JSONAssert;

public abstract class SnapshotTest {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final Set<Snapshot> MISSING = new HashSet<>();

    private TestInfo testInfo;


    @BeforeEach
    public void init(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    @AfterAll
    public static void saveSnapshots() {
        var directory = Path.of("../../src/test/resources");

        try {
            for (var snapshot : MISSING) {
                var file = directory.resolve(snapshot.path());
                Files.createDirectories(file.getParent());
                var json = GSON.toJson(snapshot.actual());
                Files.writeString(file.toAbsolutePath(), json);
                System.out.printf("Writing to %s", file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void matchesSnapshot(Object object) {
        matchesSnapshot(object, null);
    }

    protected void matchesSnapshot(Object object, @Nullable String suffix) {
        var testClass = testInfo.getTestClass().orElseThrow();
        var testName = testClass.getSimpleName();
        var testMethod = testInfo.getTestMethod().orElseThrow().getName();
        var parts = ImmutableList.<String>builder();
        parts.add("__snapshots__", testName, testMethod);
        if (suffix != null) parts.add(suffix);
        var path = String.join(File.separator, parts.build()) + ".snap";

        var snapshot = new Snapshot(path, object);

        var actual = GSON.toJson(object);

        try (var stream = testClass.getClassLoader().getResourceAsStream(snapshot.path())) {
            if (stream == null) {
                MISSING.add(snapshot);
                return;
            }

            var expected = IOUtils.toString(stream, Charset.defaultCharset());
            JSONAssert.assertEquals(expected, actual, true);
        } catch (IOException e) {
            throw new RuntimeException("error reading file: " + path);
        }
    }

}
