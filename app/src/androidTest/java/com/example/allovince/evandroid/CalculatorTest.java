package com.example.allovince.evandroid;

import static org.junit.Assert.*;

/**
 * Created by allovince on 15/8/12.
 */
public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @org.junit.Test
    public void testSum() throws Exception {
        assertEquals(6d, mCalculator.sum(1d, 5d), 0);
    }

    @org.junit.Test
    public void testSubstract() throws Exception {

    }

    @org.junit.Test
    public void testDivide() throws Exception {

    }

    @org.junit.Test
    public void testMultiply() throws Exception {

    }
}