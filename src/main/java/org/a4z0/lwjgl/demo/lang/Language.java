package org.a4z0.lwjgl.demo.lang;

import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;

import java.util.Map;
import java.util.Optional;

public final class Language {

    private static final Key FALLBACK_LANGUAGE = Key.of("en_us");

    private static Language INSTANCE;

    private final Key name;
    private final Map<String, String> messages;

    /**
    * Construct a {@link Language}.
    *
    * @param name Name.
    * @param messages Messages.
    */

    Language(Key name, Map<String, String> messages) {
        this.name = name;
        this.messages = messages;
    }

    /**
    * @return the Name.
    */

    public Key getName() {
        return this.name;
    }

    /**
    * Retrieves a Message.
    *
    * @param messageLocation Location.
    *
    * @return a {@link String} if it exists, null otherwise.
    */

    public String get(String messageLocation) {
        return this.messages.get(messageLocation);
    }

    /**
    * Retrieves a Message.
    *
    * @param messageLocation Location.
    *
    * @return a {@link String}.
    */

    public String getOrThrow(String messageLocation) {
        return Optional.ofNullable(this.get(messageLocation)).orElseThrow(() -> new RuntimeException("Unable to locate message \"" + messageLocation + "\"."));
    }

    /**
    * Retrieves a Message.
    *
    * @param messageLocation Message Location.
    * @param defaultMessage Default Message.
    *
    * @return a {@link String} if it exists, default otherwise.
    */

    public String getOrDefault(String messageLocation, String defaultMessage) {
        return this.messages.getOrDefault(messageLocation, defaultMessage);
    }

    public String getOrFallback(String messageLocation) {
        return this.getOrDefault(messageLocation, Registries.LANGUAGE.get(FALLBACK_LANGUAGE).getOrDefault(messageLocation, messageLocation));
    }

    /**
    * @return the Language Instance.
    */

    public static Language getInstance() {
        return Language.INSTANCE;
    }

    /**
    * Sets the Language Instance.
    *
    * @param instance Language.
    */

    public static void setInstance(Language instance) {
        Language.INSTANCE = instance;
    }
}