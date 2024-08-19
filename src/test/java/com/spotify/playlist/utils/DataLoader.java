package com.spotify.playlist.utils;

import java.util.Objects;
import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getDataLoaderInstance(){
        if(Objects.isNull(dataLoader))
        {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getDataPropertyValue(String key)
    {
        return PropertyUtils.getPropertyValue(properties,key);
    }
}
