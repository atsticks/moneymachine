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

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;

/**
 * Class to do algorithmic calculations on amounts.
 * Created by Anatole on 07.03.14.
 */
public class MoneyAlgorithmics{

    /**
     * Add all amounts.
     *
     * @param amounts the amounts (only in one single currency).
     * @return the sum of all amounts
     */
    public MonetaryAmount addAll(MonetaryAmount... amounts){
        throw new UnsupportedOperationException();
    }

    /**
     * Multiply amount with the given factor.
     *
     * @param amount the amount
     * @param factor the the factor
     * @return the multiplied amount
     */
    public MonetaryAmount multiply(MonetaryAmount amount, Number factor){
        throw new UnsupportedOperationException();
    }

    /**
     * subtract two amounts.
     *
     * @param amount the amount
     * @param amt2   the amoun to be
     * @return
     */
    public MonetaryAmount subtract(MonetaryAmount amount, MonetaryAmount amt2){
        throw new UnsupportedOperationException();
    }

    /**
     * Divei an amount.
     *
     * @param amount the amount
     * @param factor the factor
     * @return
     */
    public MonetaryAmount divide(MonetaryAmount amount, BigDecimal factor){
        throw new UnsupportedOperationException();
    }

    /**
     * Scale an mount.
     *
     * @param amount the amount
     * @param scale  the scale factor
     * @return
     */
    public MonetaryAmount scaleByTen(MonetaryAmount amount, int scale){
        throw new UnsupportedOperationException();
    }

    /**
     * compare amounts for their numeric value (currency will always be identical).
     *
     * @param amt  the amount
     * @param amt2 the other aamount
     * @return
     */
    public int compareAmounts(MonetaryAmount amt, MonetaryAmount amt2){
        throw new UnsupportedOperationException();
    }

    /**
     * Query the sum of only thos amounts, that are of the given currency.
     *
     * @param targetCurrency the target currency
     * @param amounts        the amounts
     * @return
     */
    public MonetaryAmount querySumOf(CurrencyUnit targetCurrency, MonetaryAmount... amounts){
        throw new UnsupportedOperationException();
    }

    /**
     * Calculate the reciprocal (1/amount).
     *
     * @param amount the amount
     * @return
     */
    public MonetaryAmount calculateReciprocal(MonetaryAmount amount){
        throw new UnsupportedOperationException();
    }

    /**
     * Calulculate the given percentage.
     *
     * @param amt     the amount
     * @param percent the percentage
     * @return
     */
    public MonetaryAmount calculatePercent(MonetaryAmount amt, double percent){
        throw new UnsupportedOperationException();
    }

    /**
     * Calulculate the given permil.
     *
     * @param amt    the amount
     * @param permil the percentage
     * @return
     */
    public MonetaryAmount calculatePermil(MonetaryAmount amt, int permil){
        throw new UnsupportedOperationException();
    }

    /**
     * Get the major part only.
     *
     * @param amt the amount
     * @return
     */
    public MonetaryAmount getMajorPart(MonetaryAmount amt){
        throw new UnsupportedOperationException();
    }

    /**
     * Calculate a compund interest, defined as base * (1+interest)^n
     *
     * @param base the base amount
     * @param rate the interest rate
     * @param n    the periods
     * @return
     */
    public MonetaryAmount getCompoundInterest(MonetaryAmount base, double rate, int n){
        throw new UnsupportedOperationException();
    }


    /**
     * Multiply amount with the given factor. Hint: the factor may exceed the numeric capabilities of the
     * amount implementation given.
     *
     * @param amount the amount
     * @param factor the factor
     * @return the correct result
     */
    public MonetaryAmount multiplyAdvanced(MonetaryAmount amount, BigDecimal factor){
        throw new UnsupportedOperationException();
    }

    /**
     * subtract two amounts. Hint: the result may exceed the numeric capabilities of the
     * amount implementation given.
     *
     * @param amount the amount
     * @param amt2   the amoun to be
     * @return
     */
    public MonetaryAmount subtractAdvanced(MonetaryAmount amount, MonetaryAmount amt2){
        throw new UnsupportedOperationException();
    }

    /**
     * Divide an amount. Hint: the result may exceed the numeric capabilities of the
     * amount implementation given.
     *
     * @param amount the amount
     * @param factor the factor
     * @return
     */
    public MonetaryAmount divideAdvanced(MonetaryAmount amount, BigDecimal factor){
        throw new UnsupportedOperationException();
    }
}
