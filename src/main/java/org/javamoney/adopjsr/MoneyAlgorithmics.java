/*
 * Copyright (c) 2012, 2013, Werner Keil, Credit Suisse (Anatole Tresch). Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License. Contributors: Anatole Tresch - initial version.
 */
package org.javamoney.adopjsr;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.List;

/**
 * Class to perform algorithmic calculations and some of the provided external functions on amounts.
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
     * Sort the given amounts.
     *
     * @param amounts the amounts
     * @return
     */
    public List<MonetaryAmount> sortAmounts(MonetaryAmount... amounts){
        throw new UnsupportedOperationException();
    }

    /**
     * Query the sum of only the given amounts, that are of the given currency.
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
     * Calulculate the given percentage of an amount.
     *
     * @param amt     the amount
     * @param percent the percentage
     * @return
     * @see org.javamoney.moneta.function.MonetaryFunctions
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
     * @see org.javamoney.moneta.function.MonetaryFunctions
     */
    public MonetaryAmount calculatePermil(MonetaryAmount amt, double permil){
        throw new UnsupportedOperationException();
    }

    /**
     * Get the major part only.
     *
     * @param amt the amount
     * @return
     * @see org.javamoney.moneta.function.MonetaryFunctions
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
     * @see javax.money.MonetaryAmount#getMonetaryContext()
     * @see javax.money.MonetaryContext
     * @see javax.money.MonetaryAmounts#queryAmountType(javax.money.MonetaryContext)
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
     * @see javax.money.MonetaryAmount#getMonetaryContext()
     * @see javax.money.MonetaryContext
     * @see javax.money.MonetaryAmounts#queryAmountType(javax.money.MonetaryContext)
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
     * @see javax.money.MonetaryAmount#getMonetaryContext()
     * @see javax.money.MonetaryContext
     * @see javax.money.MonetaryAmounts#queryAmountType(javax.money.MonetaryContext)
     */
    public MonetaryAmount divideAdvanced(MonetaryAmount amount, BigDecimal factor){
        throw new UnsupportedOperationException();
    }
}
