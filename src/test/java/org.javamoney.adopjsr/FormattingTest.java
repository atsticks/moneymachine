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
import javax.money.MonetaryOperator;
import javax.money.format.AmountStyle;
import javax.money.format.CurrencyStyle;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.text.DecimalFormat;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the formatting API.
 * Created by Anatole on 21.03.14.
 */
public class FormattingTest{

    private Formatting formatting = new Formatting();

    @Test
    public void testGetAmountFormat() throws Exception{
        for(Locale loc: DecimalFormat.getAvailableLocales()){
            MonetaryAmountFormat fmt = formatting.getAmountFormat(AmountStyle.of(loc));
            assertEquals(fmt.getAmountStyle(), AmountStyle.of(loc));
        }
    }

    @Test
    public void testGetAmountFormat1() throws Exception{
        for(Locale loc: DecimalFormat.getAvailableLocales()){
            MonetaryAmountFormat fmt = formatting.getAmountFormat(loc);
            assertEquals(fmt, MonetaryFormats.getAmountFormat(loc));
        }
    }

    @Test
    public void testCreateCustomFormat() throws Exception{
        MonetaryOperator outOp = new MonetaryOperator(){
            @Override
            public <T extends MonetaryAmount> T apply(T value){
                return (T) value.divide(1000000);
            }
        };
        MonetaryOperator inOp = new MonetaryOperator(){
            @Override
            public <T extends MonetaryAmount> T apply(T value){
                return (T) value.multiply(1000000);
            }
        };
        MonetaryAmountFormat fmt = MonetaryFormats.getAmountFormat(
                new AmountStyle.Builder(Locale.ENGLISH).setCurrencyStyle(CurrencyStyle.SYMBOL).setGroupingSizes(3, 2)
                        .setPattern(" ##0.00  ¤ Mio;[##0.00] ¤ Mio").setDisplayConversion(outOp)
                        .setParseConversion(inOp).create());
        Money m = Money.of("CHF", 2323233223232424.23);
        MonetaryAmountFormat toTest = formatting.createCustomFormat();
        assertEquals(fmt.format(m), toTest.format(m));
        m = Money.of("CHF", -2323233223232424.23);
        assertEquals(fmt.format(m), toTest.format(m));
    }
}
