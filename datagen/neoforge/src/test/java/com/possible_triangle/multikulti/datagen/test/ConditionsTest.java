package com.possible_triangle.multikulti.datagen.test;

import com.possible_triangle.multikulti.datagen.conditions.*;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.tags.ItemTags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ConditionsTest extends SnapshotTest {

    @ParameterizedTest
    @MethodSource("provideConditions")
    public void encodeToFabric(String name, Condition condition) {
        var encoded = Conditions.encodeToFabric(condition);
        matchesSnapshot(encoded, name);
    }

    @ParameterizedTest
    @MethodSource("provideConditions")
    public void encodeToForge(String name, Condition condition) {
        var encoded = Conditions.encodeToForge(condition);
        matchesSnapshot(encoded, name);
    }

    public static Stream<Arguments> provideConditions() {
        return Stream.of(
            Arguments.of("and", new And(True.INSTANCE, False.INSTANCE)),
            Arguments.of("or", new And(True.INSTANCE, False.INSTANCE)),
            Arguments.of("complex_nested", new And(True.INSTANCE, new Or(True.INSTANCE, new And(False.INSTANCE, False.INSTANCE)))),
            Arguments.of("tag_empty", new TagEmpty(ItemTags.IRON_ORES)),
            Arguments.of("tag_populated", new TagPopulated(ItemTags.COPPER_ORES)),
            Arguments.of("one_mod_loaded", new ModLoaded("test-mod")),
            Arguments.of("all_mods_loaded", new ModLoaded(List.of("test-mod", "another-mod"), false)),
            Arguments.of("any_mods_loaded", new ModLoaded(List.of("test-mod", "another-mod"), true)),
            Arguments.of("true", True.INSTANCE),
            Arguments.of("false", False.INSTANCE),
            Arguments.of("inverted_once", new Inverted(True.INSTANCE)),
            Arguments.of("inverted_twice", new Inverted(new Inverted(True.INSTANCE))),
            Arguments.of("inverted_thrice", new Inverted(new Inverted(new Inverted(True.INSTANCE))))
        );
    }

}
