package com.spotify.playlist.tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("Method name: " + m.getName());
        System.out.println("Thread id: " + Thread.currentThread().threadId());
    }
}
