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

import org.javamoney.moneta.BuildableCurrencyUnit;
import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.MonetaryContext;
import javax.money.MonetaryRoundings;
import javax.money.RoundingContext;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anatole on 21.03.14.
 */
public class RoundingTest{

    private Rounding rnd = new Rounding();
    private Money[] moneys =
            new Money[]{org.javamoney.moneta.Money.of(200.12345678, "CHF"), Money.of(100.1234567, "JPY"),
                    Money.of(100.1234567, new BuildableCurrencyUnit.Builder("API-TEST").setNumericCode(1234)
                                     .setDefaultFractionDigits(5).build()
                    )};

    @Test
    public void testRoundWithDefaultRounding() throws Exception{
        for(Money m : moneys){
            assertEquals(m.with(MonetaryRoundings.getRounding()), rnd.roundWithDefaultRounding(m));
        }
    }

    @Test
    public void testRoundBasedOnCurrency() throws Exception{
        for(Money m : moneys){
            assertEquals(m.with(MonetaryRoundings.getRounding(m.getCurrency())), rnd.roundBasedOnCurrency(m));
        }
    }

    @Test
    public void testRoundForCash() throws Exception{
        for(Money m : moneys){
            assertEquals(m.with(MonetaryRoundings.getRounding(new RoundingContext.Builder().setCurrencyUnit(m.getCurrency()).setBoolean("cashRounding", true).build())), rnd.roundForCash(m));
        }
    }

    @Test
    public void testRoundMathematical() throws Exception{
        RoundingContext ctx = new RoundingContext.Builder().setObject(RoundingMode.HALF_UP).setInt("maxScale", 3).build();
        for(Money m : moneys){
            assertEquals(m.with(MonetaryRoundings.getRounding(ctx)), rnd.roundMathematical(m));
        }
    }
}