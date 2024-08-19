package com.spotify.playlist.utils;

import java.util.Objects;
import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getConfigInstance(){
        if(Objects.isNull(configLoader))
        {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getConfigPropertyValue(String key)
    {
        return PropertyUtils.getPropertyValue(properties,key);
    }
}
