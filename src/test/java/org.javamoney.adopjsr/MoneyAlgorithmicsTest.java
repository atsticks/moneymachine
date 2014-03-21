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

import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.MonetaryAmount;
import javax.money.MonetaryContext;
import javax.money.MonetaryCurrencies;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void testSortAmounts() throws Exception{
        List<MonetaryAmount> amounts =
                alg.sortAmounts(Money.of("CHF", 0), FastMoney.of("CHF", 1), Money.of("CHF", -200),
                                FastMoney.of("USD", 210));
        List<MonetaryAmount> sortedAmounts = new ArrayList<>();
        sortedAmounts.add(Money.of("CHF", 0));
        sortedAmounts.add(FastMoney.of("CHF", 1));
        sortedAmounts.add(Money.of("CHF", -200));
        sortedAmounts.add(FastMoney.of("USD", 210));
        Collections.sort(sortedAmounts);
    }

    @Test
    public void testQuerySumOf() throws Exception{
        MonetaryAmount amt =
                alg.querySumOf(MonetaryCurrencies.getCurrency("CHF"), FastMoney.of("CHF", 10), Money.of("CHF", 0),
                               FastMoney.of("USD", 1), Money.of("CHF", 200.45), FastMoney.of("USD", 210));
        assertTrue(Money.of("CHF", 210.45).isEqualTo(amt));
    }

    @Test
    public void testCalculateReciprocal() throws Exception{
        MonetaryAmount amt = alg.calculateReciprocal(Money.of("CHF", 10));
        assertTrue(Money.of("CHF", 210.45).isEqualTo(amt));
    }

    @Test
    public void testCalculatePercent() throws Exception{
        MonetaryAmount amt = alg.calculatePercent(FastMoney.of("EUR", 100), 20.5);
        assertTrue(Money.of("EUR", 20.5).isEqualTo(amt));
        amt = alg.calculatePercent(Money.of("EUR", 10.45), 1.345);
        assertTrue(Money.of("EUR", 0.1405525).isEqualTo(amt));
    }

    @Test
    public void testCalculatePermil() throws Exception{
        MonetaryAmount amt = alg.calculatePermil(FastMoney.of("EUR", 100), 20.5);
        assertTrue(Money.of("EUR", 2.05).isEqualTo(amt));
        amt = alg.calculatePermil(Money.of("EUR", 10.45), 1.345);
        assertTrue(Money.of("EUR", 0.01405525).isEqualTo(amt));
    }

    @Test
    public void testGetCompoundInterest() throws Exception{
        MonetaryAmount amt1 = alg.getCompoundInterest(FastMoney.of("EUR", 100), 10.5, 1);
        assertTrue(Money.of("EUR", 110.5).isEqualTo(amt1));
        MonetaryAmount amt2 = alg.getCompoundInterest(FastMoney.of("EUR", 100), 10.5, 1);
        assertTrue(amt1.multiply(0.105).isEqualTo(amt2));
        MonetaryAmount amt3 = alg.getCompoundInterest(FastMoney.of("EUR", 100), 10.5, 1);
        assertTrue(amt2.multiply(0.105).isEqualTo(amt3));
        MonetaryAmount amt4 = alg.getCompoundInterest(FastMoney.of("EUR", 100), 10.5, 1);
        assertTrue(amt3.multiply(0.105).isEqualTo(amt4));
        MonetaryAmount amt5 = alg.getCompoundInterest(FastMoney.of("EUR", 100), 10.5, 1);
        assertTrue(amt4.multiply(0.105).isEqualTo(amt5));
    }

    @Test
    public void testMultiplyAdvanced() throws Exception{
        MonetaryAmount amt1 = alg.multiplyAdvanced(FastMoney.of("EUR", 100), BigDecimal.valueOf(Long.MAX_VALUE));
        assertTrue(Money.of("EUR", 100, new MonetaryContext.Builder().set(MathContext.UNLIMITED).create())
                           .multiply(BigDecimal.valueOf(Long.MAX_VALUE)).isEqualTo(amt1));
    }

    @Test
    public void testSubtractAdvanced() throws Exception{
        MonetaryAmount amt1 = alg.subtractAdvanced(FastMoney.of("EUR", 100),
                                                   Money.of("EUR", new BigDecimal("0.0000000000000000000001")));
        assertTrue(Money.of("EUR", 100, new MonetaryContext.Builder().set(MathContext.UNLIMITED).create())
                           .subtract(Money.of("EUR", new BigDecimal("0.0000000000000000000001"))).isEqualTo(amt1));
    }

    @Test
    public void testDivideAdvanced() throws Exception{
        MonetaryAmount amt1 = alg.divideAdvanced(FastMoney.of("EUR", 100), new BigDecimal("0.0000000000000000000001"));
        assertTrue(Money.of("EUR", 100, new MonetaryContext.Builder().set(MathContext.UNLIMITED).create())
                           .divide(new BigDecimal("0.0000000000000000000001")).isEqualTo(amt1));
    }
}
