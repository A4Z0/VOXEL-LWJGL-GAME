package org.a4z0.lwjgl.demo.resource.language;

import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.ResourceKey;

import java.util.Map;

public final class Language {

    private static final Key FALLBACK_LANGUAGE = Key.of("en_us");

    private static ResourceKey<Language> INSTANCE;

    private final Key name;
    private final Map<String, String> messages;

    /**
    * Construct a {@link Language}.
    *
    * @param name Name.
    * @param messages Messages.
    */

    public Language(Key name, Map<String, String> messages) {
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
    * ...
    *
    * @param messageLocation Message Path.
    *
    * @return true if contains the Message Path, false otherwise.
    */

    public boolean has(String messageLocation) {
        return this.messages.containsKey(messageLocation);
    }

    /**
    * Retrieves a Message.
    *
    * @param messageLocation Message Path.
    *
    * @return a Message if it exists, null otherwise.
    */

    public String get(String messageLocation) {
        return this.messages.get(messageLocation);
    }

    /**
    * Retrieves a Message.
    *
    * @param messageLocation Message Path.
    * @param defaultMessage Returned if the Message Path doesn't exist.
    *
    * @return a Message.
    */

    public String getOrDefault(String messageLocation, String defaultMessage) {
        return this.messages.getOrDefault(messageLocation, defaultMessage);
    }

    /**
    * Retrieves a Message.
    *
    * @param messageLocation Message Path.
    *
    * @return a Message.
    */

    public String getOrFallback(String messageLocation) {
        return this.getOrDefault(messageLocation, Registries.LANGUAGE.getOrThrow(FALLBACK_LANGUAGE).getOrDefault(messageLocation, messageLocation));
    }

    /**
    * @return the Language Instance.
    */

    public static Language getInstance() {
        if(Language.INSTANCE != null) {
            return Registries.LANGUAGE.getOrThrow(Language.INSTANCE);
        } else {
            return Registries.LANGUAGE.getOrThrow(Language.FALLBACK_LANGUAGE);
        }
    }

    /**
    * Sets the Language Instance.
    *
    * @param instance Language Instance to be set.
    */

    public static void setInstance(ResourceKey<Language> instance) {
        Language.INSTANCE = instance;
    }
}