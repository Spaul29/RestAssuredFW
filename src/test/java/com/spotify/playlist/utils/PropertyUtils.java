package com.spotify.playlist.utils;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class PropertyUtils {

    public static Properties propertyLoader(String filePath) {
        Properties properties = new Properties();
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filePath));
            try{
                properties.load(reader);
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file "+filePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Properties file not found at "+filePath);
        }
        return properties;
    }

    public static String getPropertyValue(Properties properties, String key)
    {
            String propertyValue;
            if(!Objects.isNull(key) && !Objects.isNull(properties.getProperty(key)) &&
                properties.containsKey(key))
            {
                propertyValue = properties.getProperty(key);
                return propertyValue;
            }
            else{
                throw new RuntimeException("Property not found in file");
            }
    }
}
