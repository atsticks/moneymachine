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
        assertEquals(Money.of( 20,"CHF"), alg.addAll(Money.of( 10,"CHF"), Money.of( 10,"CHF")));
        assertEquals(Money.of( 20.8,"CHF"), alg.addAll(Money.of( 10.5,"CHF"), Money.of( 10.3,"CHF")));
        assertEquals(Money.of( -90,"CHF"), alg.addAll(Money.of( -100.3,"CHF"), Money.of( 10.3,"CHF")));
        assertEquals(Money.of( 0,"CHF"), alg.addAll(Money.of( 0,"CHF"), Money.of( 0,"CHF")));
        assertEquals(Money.of( 283976.34,"CHF"), alg.addAll(Money.of( 283976.34,"CHF"), Money.of( 0,"CHF")));
    }

    @Test
    public void testMultiply() throws Exception{
        assertEquals(Money.of( 100,"CHF"), alg.multiply(Money.of( 10,"CHF"), 10));
        assertEquals(Money.of( 101,"CHF"), alg.multiply(Money.of( 10.1,"CHF"), 10));
        assertEquals(Money.of( 21323120,"CHF"), alg.multiply(Money.of( 2132312,"CHF"), 10));
        assertEquals(Money.of( 100,"CHF"), alg.multiply(Money.of( -100,"CHF"), -1));
    }

    @Test
    public void testSubtract() throws Exception{
        assertEquals(Money.of( 0,"CHF"), alg.multiply(Money.of( 10,"CHF"), 10));
        assertEquals(Money.of( -10,"CHF"), alg.multiply(Money.of( 10,"CHF"), 120));
        assertEquals(Money.of( -2.5,"CHF"), alg.multiply(Money.of( 10,"CHF"), 12.5));
        assertEquals(Money.of( 0.06,"CHF"), alg.multiply(Money.of( 10.56,"CHF"), 10.5));
    }

    @Test
    public void testDivide() throws Exception{
        assertEquals(Money.of( 1,"CHF"), alg.multiply(Money.of( 10,"CHF"), 10));
        assertEquals(Money.of( 1,"CHF"), alg.multiply(Money.of( 10.345,"CHF"), 10.345));
        assertEquals(Money.of( 10.345,"CHF"), alg.multiply(Money.of( 10.345,"CHF"), 1));
        assertEquals(Money.of( 0.0837991089509923,"CHF"), alg.multiply(Money.of( 10.345,"CHF"), 123.45));
    }

    @Test
    public void testScaleByTen() throws Exception{
        assertEquals(Money.of( 100,"CHF"), alg.scaleByTen(Money.of( 10,"CHF"), 1));
        assertEquals(Money.of( 123234405.6,"CHF"), alg.scaleByTen(Money.of( 123234.4056,"CHF"), 3));
    }

    @Test
    public void testSortAmounts() throws Exception{
        List<MonetaryAmount> amounts =
                alg.sortAmounts(Money.of( 0,"CHF"), FastMoney.of( 1,"CHF"), Money.of( -200,"CHF"),
                                FastMoney.of(210,"USD"));
        List<MonetaryAmount> sortedAmounts = new ArrayList<>();
        sortedAmounts.add(Money.of( 0,"CHF"));
        sortedAmounts.add(FastMoney.of( 1,"CHF"));
        sortedAmounts.add(Money.of( -200,"CHF"));
        sortedAmounts.add(FastMoney.of(210,"USD"));
        Collections.sort(sortedAmounts);
    }

    @Test
    public void testQuerySumOf() throws Exception{
        MonetaryAmount amt =
                alg.querySumOf(MonetaryCurrencies.getCurrency("CHF"), FastMoney.of( 10,"CHF"), Money.of( 0,"CHF"),
                               FastMoney.of( 1,"USD"), Money.of( 200.45,"CHF"), FastMoney.of(210,"USD"));
        assertTrue(Money.of( 210.45,"CHF").isEqualTo(amt));
    }

    @Test
    public void testCalculateReciprocal() throws Exception{
        MonetaryAmount amt = alg.calculateReciprocal(Money.of( 10,"CHF"));
        assertTrue(Money.of( 210.45,"CHF").isEqualTo(amt));
    }

    @Test
    public void testCalculatePercent() throws Exception{
        MonetaryAmount amt = alg.calculatePercent(FastMoney.of(100,"EUR"), 20.5);
        assertTrue(Money.of(20.5,"EUR").isEqualTo(amt));
        amt = alg.calculatePercent(Money.of(10.45,"EUR"), 1.345);
        assertTrue(Money.of(0.1405525,"EUR").isEqualTo(amt));
    }

    @Test
    public void testCalculatePermil() throws Exception{
        MonetaryAmount amt = alg.calculatePermil(FastMoney.of(100,"EUR"), 20.5);
        assertTrue(Money.of(2.05,"EUR").isEqualTo(amt));
        amt = alg.calculatePermil(Money.of(10.45,"EUR"), 1.345);
        assertTrue(Money.of(0.01405525,"EUR").isEqualTo(amt));
    }

    @Test
    public void testGetCompoundInterest() throws Exception{
        MonetaryAmount amt1 = alg.getCompoundInterest(FastMoney.of(100,"EUR"), 10.5, 1);
        assertTrue(Money.of(110.5,"EUR").isEqualTo(amt1));
        MonetaryAmount amt2 = alg.getCompoundInterest(FastMoney.of(100,"EUR"), 10.5, 1);
        assertTrue(amt1.multiply(0.105).isEqualTo(amt2));
        MonetaryAmount amt3 = alg.getCompoundInterest(FastMoney.of(100,"EUR"), 10.5, 1);
        assertTrue(amt2.multiply(0.105).isEqualTo(amt3));
        MonetaryAmount amt4 = alg.getCompoundInterest(FastMoney.of(100,"EUR"), 10.5, 1);
        assertTrue(amt3.multiply(0.105).isEqualTo(amt4));
        MonetaryAmount amt5 = alg.getCompoundInterest(FastMoney.of(100,"EUR"), 10.5, 1);
        assertTrue(amt4.multiply(0.105).isEqualTo(amt5));
    }

    @Test
    public void testMultiplyAdvanced() throws Exception{
        MonetaryAmount amt1 = alg.multiplyAdvanced(FastMoney.of(100,"EUR"), BigDecimal.valueOf(Long.MAX_VALUE));
        assertTrue(Money.of(100,"EUR", new MonetaryContext.Builder().setObject(MathContext.UNLIMITED).build())
                           .multiply(BigDecimal.valueOf(Long.MAX_VALUE)).isEqualTo(amt1));
    }

    @Test
    public void testSubtractAdvanced() throws Exception{
        MonetaryAmount amt1 = alg.subtractAdvanced(FastMoney.of(100,"EUR"),
                                                   Money.of(new BigDecimal("0.0000000000000000000001"),"EUR"));
        assertTrue(Money.of(100,"EUR", new MonetaryContext.Builder().setObject(MathContext.UNLIMITED).build())
                           .subtract(Money.of(new BigDecimal("0.0000000000000000000001"),"EUR")).isEqualTo(amt1));
    }

    @Test
    public void testDivideAdvanced() throws Exception{
        MonetaryAmount amt1 = alg.divideAdvanced(FastMoney.of(100,"EUR"), new BigDecimal("0.0000000000000000000001"));
        assertTrue(Money.of(100,"EUR", new MonetaryContext.Builder().setObject(MathContext.UNLIMITED).build())
                           .divide(new BigDecimal("0.0000000000000000000001")).isEqualTo(amt1));
    }
}
