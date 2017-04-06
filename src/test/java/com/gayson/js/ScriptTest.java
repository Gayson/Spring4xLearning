package com.gayson.js;

import org.testng.annotations.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptTest {

    @Test
    public void simpleTest() {
        try {

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            String scriptText = "function sum(a, b) {return a+b;}";
            engine.eval(scriptText);
            Invocable invocable = (Invocable) engine;
            Object result = invocable.invokeFunction("sum", 100, 99);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
