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
import org.javamoney.moneta.RoundedMoney;
import org.junit.Test;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.MonetaryContext;
import javax.money.MonetaryCurrencies;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Test class to ensure the basic core API is well understood.
 * Created by Anatole on 07.03.14.
 */
public class BasicsTest{

    private Basics basics = new Basics();

    @Test
    public void testGetProvidedCurrency() throws Exception{
        for(Currency cur : Currency.getAvailableCurrencies()){
            assertNotNull(basics.getProvidedCurrency(cur.getCurrencyCode()));
        }
    }

    @Test
    public void testGetProvidedCurrency1() throws Exception{
        for(String country : Locale.getISOCountries()){
            assertNotNull(basics.getProvidedCurrency(new Locale("", country)));
        }
    }

    @Test
    public void testBuildCustomCurrency() throws Exception{
        CurrencyUnit cur = basics.buildCustomCurrency("BTC", -1, 10);
        assertEquals("BTC", cur.getCurrencyCode());
        assertEquals(-1, cur.getNumericCode());
        assertEquals(10, cur.getDefaultFractionDigits());
        cur = basics.buildCustomCurrency("BarFoo15-t3", 1234, 3);
        assertEquals("BarFoo15-t3", cur.getCurrencyCode());
        assertEquals(-1, cur.getNumericCode());
        assertEquals(10, cur.getDefaultFractionDigits());
    }

    @Test
    public void testBuildAndRegisterCustomCurrency() throws Exception{
        CurrencyUnit cur = basics.buildCustomCurrency("ABC", 123, 4);
        assertEquals("ABC", cur.getCurrencyCode());
        assertEquals(123, cur.getNumericCode());
        assertEquals(4, cur.getDefaultFractionDigits());
        cur = basics.buildCustomCurrency("BarFoo15", 1234, 3);
        assertEquals("BarFoo15", cur.getCurrencyCode());
        assertEquals(1234, cur.getNumericCode());
        assertEquals(3, cur.getDefaultFractionDigits());
        assertNotNull(MonetaryCurrencies.getCurrency("BarFoo15"));
    }

    @Test
    public void testGetMonetaryAmountTypes() throws Exception{
        Collection<Class<? extends MonetaryAmount>> types = basics.getMonetaryAmountTypes();
        assertNotNull(types);
        assertTrue(types.contains(Money.class));
        assertTrue(types.contains(FastMoney.class));
        assertTrue(types.contains(RoundedMoney.class));
    }

    @Test
    public void testGetDefaultMonetaryAmountType() throws Exception{
        Class<? extends MonetaryAmount> type = basics.getDefaultMonetaryAmountType();
        assertNotNull(type);
        assertEquals(type, Money.class);
    }

    @Test
    public void testGetMoneyFactory() throws Exception{
        assertNotNull(basics.getMoneyFactory(Money.class));
        assertNotNull(basics.getMoneyFactory(FastMoney.class));
    }

    @Test
    public void testGetMoney() throws Exception{
        MonetaryAmount amt = basics.getMoney(10.50d, "CHF");
        assertNotNull(amt);
        assertEquals("CHF", amt.getCurrency());
        assertEquals(10.50d, amt.getNumber().doubleValue(), 0.0d);
        amt = basics.getMoney(Long.MAX_VALUE, "USD");
        assertNotNull(amt);
        assertEquals("USD", amt.getCurrency());
        assertEquals(Long.MAX_VALUE, amt.getNumber().longValue());
    }

    @Test
    public void testGetMoneyWithContext() throws Exception{
        MonetaryContext preciseCtx = new MonetaryContext.Builder().setFlavor(MonetaryContext.AmountFlavor.PRECISION).create();
        MonetaryContext fastCtx = new MonetaryContext.Builder().setFlavor(MonetaryContext.AmountFlavor.PERFORMANCE).create();
        MonetaryAmount amt = basics.getMoneyWithContext(new BigDecimal("10.50792323200000000000236823"), "CHF",
                                                        preciseCtx);
        assertNotNull(amt);
        assertEquals("CHF", amt.getCurrency());
        assertEquals(new BigDecimal("10.50792323200000000000236823"), amt.getNumber().numberValue(BigDecimal.class));
        amt = basics.getMoneyWithContext(6546546464L, "USD", fastCtx);
        assertNotNull(amt);
        assertEquals("USD", amt.getCurrency());
        assertEquals(6546546464L, amt.getNumber().longValueExact());
    }

    @Test
    public void testGetMoneyWithSpecification() throws Exception{
        MonetaryContext preciseCtx = new MonetaryContext.Builder().setFlavor(MonetaryContext.AmountFlavor.PRECISION).create();
        MonetaryContext fastCtx = new MonetaryContext.Builder().setFlavor(MonetaryContext.AmountFlavor.PERFORMANCE).create();
        MonetaryAmount amt = basics.getMoneyWithSpecificCapabilities(new BigDecimal("10.50792323200000000000236823"),
                                                                     "CHF");
        assertNotNull(amt);
        assertEquals("CHF", amt.getCurrency());
        assertEquals(new BigDecimal("10.50792323200000000000236823"), amt.getNumber().numberValue(BigDecimal.class));
        MonetaryContext ctx = amt.getMonetaryContext();
        assertTrue(ctx.getMaxScale()>=128);
        assertTrue(ctx.getPrecision()>=256 || ctx.getPrecision()==0);
        assertEquals(ctx.getAttribute(RoundingMode.class), RoundingMode.FLOOR);
        amt = basics.getMoneyWithSpecificCapabilities(6546546464L, "USD");
        assertNotNull(amt);
        assertEquals("USD", amt.getCurrency());
        assertEquals(6546546464L, amt.getNumber().longValueExact());
        assertTrue(ctx.getMaxScale()>=128);
        assertTrue(ctx.getPrecision()>=256 || ctx.getPrecision()==0);
        assertEquals(ctx.getAttribute(RoundingMode.class), RoundingMode.FLOOR);
    }
}
