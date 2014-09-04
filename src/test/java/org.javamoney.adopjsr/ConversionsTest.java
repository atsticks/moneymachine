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

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.MonetaryCurrencies;
import javax.money.convert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Anatole on 21.03.14.
 */
public class ConversionsTest{

    private Conversions conv = new Conversions();
    private static final long ONEYEAR = 3600000L * 24 * 365;

    @Test
    public void testGetExchangeRateFromIMF() throws Exception{
        CurrencyUnit[] units =
                new CurrencyUnit[]{MonetaryCurrencies.getCurrency("CHF"), MonetaryCurrencies.getCurrency("EUR"),
                        MonetaryCurrencies.getCurrency("USD")};
        for(CurrencyUnit u1 : units){
            for(CurrencyUnit u2 : units){
                if(u1.equals(u2)){
                    continue;
                }
                boolean exception = false;
                ExchangeRate expected = null;
                try{
                    expected = MonetaryConversions.getExchangeRateProvider("IMF").getExchangeRate(u1, u2);
                }
                catch(Exception e){
                    exception = true;
                }
                ExchangeRate r = conv.getExchangeRateFromIMF(u1, u2);
                try{
                    assertEquals(expected, r);
                }
                catch(Exception e){
                    if(!exception){
                        throw e;
                    }
                }
            }
        }
    }

    @Test
    public void testGetExchangeRatewithTime() throws Exception{
        long now = System.currentTimeMillis();
        long[] times = new long[]{now, now - ONEYEAR, now - (2 * ONEYEAR), now - (3 * ONEYEAR)};
        CurrencyUnit[] units =
                new CurrencyUnit[]{MonetaryCurrencies.getCurrency("CHF"), MonetaryCurrencies.getCurrency("EUR"),
                        MonetaryCurrencies.getCurrency("USD")};
        for(CurrencyUnit u1 : units){
            for(CurrencyUnit u2 : units){
                if(u1.equals(u2)){
                    continue;
                }
                for(long time : times){
                    boolean exception = false;
                    ExchangeRate expected = null;
                    try{
                        expected = MonetaryConversions.getExchangeRateProvider("IMF").getExchangeRate(
                                ConversionQueryBuilder.of().setTimestampMillis(time).setBaseCurrency(u1)
                                        .setTermCurrency(u2).build());
                    }
                    catch(Exception e){
                        exception = true;
                    }
                    ExchangeRate r = conv.getExchangeRateWithTime(u1, u2, time);
                    try{
                        assertEquals(expected, r);
                    }
                    catch(Exception e){
                        if(!exception){
                            throw e;
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testConvertAmount() throws Exception{
        CurrencyUnit[] units =
                new CurrencyUnit[]{MonetaryCurrencies.getCurrency("CHF"), MonetaryCurrencies.getCurrency("EUR"),
                        MonetaryCurrencies.getCurrency("USD"), MonetaryCurrencies.getCurrency("JPY"),
                        MonetaryCurrencies.getCurrency("INR"),};
        Money[] moneys = new Money[]{Money.of(10, "CHF"), Money.of(123.34, "USD"), Money.of(2300.30, "INR")};
        long now = System.currentTimeMillis();
        long[] times = new long[]{now, now - ONEYEAR, now - (2 * ONEYEAR), now - (3 * ONEYEAR)};
        for(CurrencyUnit u1 : units){
            for(Money m : moneys){
                for(long time : times){
                    boolean exception = false;
                    MonetaryAmount expected = null;
                    try{
                        expected = m.with(MonetaryConversions.getConversion(
                                ConversionQueryBuilder.of().setTimestampMillis(time).setTermCurrency(u1).build()));
                    }
                    catch(Exception e){
                        exception = true;
                    }
                    MonetaryAmount res = conv.convertAmount(u1, m, time);
                    try{
                        assertEquals(expected, res);
                    }
                    catch(Exception e){
                        if(!exception){
                            throw e;
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testConvertAmountDefault() throws Exception{
        CurrencyUnit[] units =
                new CurrencyUnit[]{MonetaryCurrencies.getCurrency("CHF"), MonetaryCurrencies.getCurrency("EUR"),
                        MonetaryCurrencies.getCurrency("USD"), MonetaryCurrencies.getCurrency("JPY"),
                        MonetaryCurrencies.getCurrency("INR"),};
        Money[] moneys = new Money[]{Money.of(10, "CHF"), Money.of(123.34, "USD"), Money.of(2300.30, "INR")};
        for(CurrencyUnit u1 : units){
            for(Money m : moneys){
                boolean exception = false;
                MonetaryAmount expected = null;
                try{
                    expected = m.with(MonetaryConversions
                                              .getConversion(ConversionQueryBuilder.of().setTermCurrency(u1).build()));
                }
                catch(Exception e){
                    exception = true;
                }
                MonetaryAmount res = conv.convertAmountDefault(u1, m);
                try{
                    assertEquals(expected, res);
                }
                catch(Exception e){
                    if(!exception){
                        throw e;
                    }
                }
            }
        }
    }

    @Test
    public void testGetDefaultConversionContext(){
        ConversionContext ctx = conv.getDefaultConversionContext(MonetaryCurrencies.getCurrency("CHF"),
                                                                 MonetaryCurrencies.getCurrency("EUR"));
        assertNotNull(ctx);
        assertEquals("ECB", ctx.getProvider());
        ctx = conv.getDefaultConversionContext(MonetaryCurrencies.getCurrency("CHF"),
                                               MonetaryCurrencies.getCurrency("INR"));
        assertNotNull(ctx);
        assertEquals("IMF", ctx.getProvider());
    }

    @Test
    public void testGetIMFProviderContext(){
        ProviderContext ctx = conv.getIMFProviderContext();
        assertNotNull(ctx);
        assertEquals("IMF", ctx.getProvider());
    }

    @Test
    public void testGetECBProviderContext(){
        ProviderContext ctx = conv.getECBProviderContext();
        assertNotNull(ctx);
        assertEquals("ECB", ctx.getProvider());
    }

    @Test
    public void testGetDefaultProviderContext(){
        ProviderContext ctx = conv.getDefaultProviderContext();
        assertNotNull(ctx);
        assertEquals(MonetaryConversions.getExchangeRateProvider().getProviderContext().getProvider(),
                     ctx.getProvider());
    }

    @Test
    public void testGetDefaultProviderChain(){
        assertEquals(MonetaryConversions.getDefaultProviderChain(), conv.getDefaultProviderChain());
    }

    @Test
    public void testCustomProvider(){
        String provName = conv.getNewProviderName();
        ExchangeRateProvider prov = MonetaryConversions.getExchangeRateProvider(provName);
        assertNotNull(prov);
    }
}
