/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE
 * CONDITION THAT YOU ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT.
 * PLEASE READ THE TERMS AND CONDITIONS OF THIS AGREEMENT CAREFULLY. BY
 * DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF THE
 * AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE"
 * BUTTON AT THE BOTTOM OF THIS PAGE. Specification: JSR-354 Money and Currency
 * API ("Specification") Copyright (c) 2012-2013, Credit Suisse All rights
 * reserved.
 */

package org.javamoney.adopjsr;

import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.MonetaryAmount;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anatole on 08.03.14.
 */
public class MoneyAlgorithmicsTest{

    private MoneyAlgorithmics alg = new MoneyAlgorithmics();

    @Test
    public void testAddAll() throws Exception{
        MonetaryAmount amt = Money.of("CHF", 10);
        assertEquals(Money.of("CHF", 20), alg.addAll(Money.of("CHF", 10), Money.of("CHF", 10)));
        assertEquals(Money.of("CHF", 20.8), alg.addAll(Money.of("CHF", 10.5), Money.of("CHF", 10.3)));
        assertEquals(Money.of("CHF", -90), alg.addAll(Money.of("CHF", -100.3), Money.of("CHF", 10.3)));
        assertEquals(Money.of("CHF", 0), alg.addAll(Money.of("CHF", 0), Money.of("CHF", 0)));
        assertEquals(Money.of("CHF", 283976.34), alg.addAll(Money.of("CHF", 283976.34), Money.of("CHF", 0)));
    }

    @Test
    public void testMultiply() throws Exception{
        assertEquals(Money.of("CHF", 100), alg.multiply(Money.of("CHF", 10), 10));
        assertEquals(Money.of("CHF", 101), alg.multiply(Money.of("CHF", 10.1), 10));
        assertEquals(Money.of("CHF", 21323120), alg.multiply(Money.of("CHF", 2132312), 10));
        assertEquals(Money.of("CHF", 100), alg.multiply(Money.of("CHF", -100), -1));
    }

    @Test
    public void testSubtract() throws Exception{
       assertEquals(Money.of("CHF", 0), alg.multiply(Money.of("CHF", 10), 10));
        assertEquals(Money.of("CHF", -10), alg.multiply(Money.of("CHF", 10), 120));
        assertEquals(Money.of("CHF", -2.5), alg.multiply(Money.of("CHF", 10), 12.5));
        assertEquals(Money.of("CHF", 0.06), alg.multiply(Money.of("CHF", 10.56), 10.5));
    }

    @Test
    public void testDivide() throws Exception{
        assertEquals(Money.of("CHF", 1), alg.multiply(Money.of("CHF", 10), 10));
        assertEquals(Money.of("CHF", 1), alg.multiply(Money.of("CHF", 10.345), 10.345));
        assertEquals(Money.of("CHF", 10.345), alg.multiply(Money.of("CHF", 10.345), 1));
        assertEquals(Money.of("CHF", 0.0837991089509923), alg.multiply(Money.of("CHF", 10.345), 123.45));
    }

    @Test
    public void testScaleByTen() throws Exception{
        assertEquals(Money.of("CHF", 100), alg.scaleByTen(Money.of("CHF", 10), 1));
        assertEquals(Money.of("CHF", 123234405.6), alg.scaleByTen(Money.of("CHF", 123234.4056), 3));
    }

    @Test
    public void testCompareAmounts() throws Exception{
        // TODO write test
    }

    @Test
    public void testQuerySumOf() throws Exception{
        // TODO write test
    }

    @Test
    public void testCalculateReciprocal() throws Exception{
        // TODO write test
    }

    @Test
    public void testCalculatePercent() throws Exception{
        // TODO write test
    }

    @Test
    public void testCalculatePermil() throws Exception{
        // TODO write test
    }

    @Test
    public void testGetMajorPart() throws Exception{
        // TODO write test
    }

    @Test
    public void testGetCompoundInterest() throws Exception{
        // TODO write test
    }

    @Test
    public void testMultiplyAdvanced() throws Exception{
        // TODO write test
    }

    @Test
    public void testSubtractAdvanced() throws Exception{
        // TODO write test
    }

    @Test
    public void testDivideAdvanced() throws Exception{
        // TODO write test
    }
}
