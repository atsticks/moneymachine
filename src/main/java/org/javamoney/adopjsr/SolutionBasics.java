/*
 * Copyright (c) 2015 Credit Suisse. Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.javamoney.adopjsr;

import org.javamoney.moneta.CurrencyUnitBuilder;

import javax.money.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Locale;

/**
 * Solution to the Basics class
 * 
 * Created by CLO JUG www.clojug.org
 */
public class SolutionBasics{

    /**
     * Get a {@link javax.money.CurrencyUnit} using a currency code.
     *
     * @param currencyCode the currency code
     * @return the corresponding CurrencyUnit instance.
     * @see javax.money.MonetaryCurrencies
     */
    public CurrencyUnit getProvidedCurrency(String currencyCode){
        // throw new UnsupportedOperationException();
        return MonetaryCurrencies.getCurrency(currencyCode);
    }

    /**
     * Get a {@link javax.money.CurrencyUnit} using a Locale, modeling a country.
     *
     * @param locale The country locale.
     * @return the corresponding CurrencyUnit instance.
     * @see javax.money.MonetaryCurrencies
     * @see java.util.Locale
     */
    public CurrencyUnit getProvidedCurrency(Locale locale){
        if(MonetaryCurrencies.isCurrencyAvailable(locale))
        {
            return MonetaryCurrencies.getCurrency(locale);
        }

        return buildCustomCurrency(locale.getCountry(), 0, 2);
    }

    /**
     * Create a custom {@link javax.money.CurrencyUnit}.
     *
     * @param currencyCode         the currency code
     * @param numericCode          the numeric code.
     * @param defaultFractionUnits the default fraction units.
     * @return the new currency unit instance created.
     * @see org.javamoney.moneta.BuildableCurrencyUnit
     */
    public CurrencyUnit buildCustomCurrency(String currencyCode, int numericCode, int defaultFractionUnits){
        return CurrencyUnitBuilder.of(currencyCode, "default")
                                  .setNumericCode(numericCode)
                                  .setDefaultFractionDigits(defaultFractionUnits)
                                  .build();
    }

    /**
     * Build and register a {@link javax.money.CurrencyUnit}.
     *
     * @param currencyCode         the currency code (non null).
     * @param numericCode          the numeric code.
     * @param defaultFractionUnits the default fraction units.
     * @return the CurrencyUnit. Additionally the unit should be registered,
     * so it accessible from {@link }MonetaryCurrencies}.
     * @see org.javamoney.moneta.BuildableCurrencyUnit
     * @see javax.money.MonetaryCurrencies
     */
    public CurrencyUnit buildAndRegisterCustomCurrency(String currencyCode, int numericCode, int defaultFractionUnits){
        return CurrencyUnitBuilder.of(currencyCode, "default")
                                  .setNumericCode(numericCode)
                                  .setDefaultFractionDigits(defaultFractionUnits)
                                  .build(true);
    }

    /**
     * Evaluate the current registered implementation {@link javax.money.MonetaryAmount} types for amounts.
     *
     * @return the current amount types.
     * @see javax.money.MonetaryAmounts
     */
    public Collection<Class<? extends MonetaryAmount>> getMonetaryAmountTypes(){
        return MonetaryAmounts.getAmountTypes();
    }

    /**
     * Evaluate the current registered default implementation {@link javax.money.MonetaryAmount} type for amounts.
     *
     * @return the current default amount type.
     * @see javax.money.MonetaryAmounts
     */
    public Class<? extends MonetaryAmount> getDefaultMonetaryAmountType(){
        return MonetaryAmounts.getDefaultAmountType();
    }

    /**
     * Lookup the a corresponding factory for creating {@link javax.money.MonetaryAmount} instances of a certain type.
     *
     * @param moneyType the monetary amount's implementation class.
     * @return the corresponding factory, not null.
     * @see javax.money.MonetaryAmounts
     */
    public <T extends MonetaryAmount> MonetaryAmountFactory<T> getMoneyFactory(Class<T> moneyType){
        return MonetaryAmounts.getAmountFactory(moneyType);
    }

    /**
     * Get an amount with the given amount and currency. THe advanced might try to of an amount that has a
     * {@link javax.money.CurrencyUnit}, which is not registered in {@link javax.money.MonetaryCurrencies}.
     *
     * @param number       the amount
     * @param currencyCode the currency code
     * @return a corresponding amount instance.
     * @see javax.money.MonetaryAmounts
     * @see javax.money.MonetaryAmountFactory
     */
    public MonetaryAmount getMoney(Number number, String currencyCode){
        if(!MonetaryCurrencies.isCurrencyAvailable(currencyCode))
        {
            buildAndRegisterCustomCurrency(currencyCode,0,2);
        }

        return MonetaryAmounts.getDefaultAmountFactory()
                              .setCurrency(currencyCode)
                              .setNumber(number)
                              .create();
    }

    /**
     * Converts the given amount to a new amount with the given BigDecimal value and currencyCode.
     *
     * @param amount       the amount
     * @param bd           the BD number
     * @param currencyCode the currency code
     * @return a corresponding amount instance.
     * @see javax.money.MonetaryAmounts
     * @see javax.money.MonetaryAmountFactory
     */
    public MonetaryAmount convertAmount(MonetaryAmount amount, BigDecimal bd, String currencyCode){
        if(!MonetaryCurrencies.isCurrencyAvailable(currencyCode))
        {
            buildAndRegisterCustomCurrency(currencyCode,0,2);
        }

        return MonetaryAmounts.getDefaultAmountFactory()
                              .setAmount(amount)
                              .setCurrency(currencyCode)
                              .setNumber(bd)
                              .create();
    }

    /**
     * Change the numeric capabilities of the given amount to a new capabilities given.
     *
     * @param amount    the amount
     * @param scale     the maximal scale
     * @param precision the target precision
     * @param context   the MathContext
     * @return a corresponding amount instance.
     * @see javax.money.MonetaryAmounts
     * @see javax.money.MonetaryAmountFactory
     * @see javax.money.MonetaryContext
     */
    public MonetaryAmount convertAmount(MonetaryAmount amount, int scale, int precision, MathContext context){
        throw new UnsupportedOperationException();
    }

    /**
     * Create a {@link javax.money.MonetaryAmount} that covers the capabilities as defined in the {@link javax.money
     * .MonetaryContext}.
     *
     * @param number       the amount
     * @param currencyCode the currency code
     * @param context      the Monetary context
     * @return an according money instance
     * @see javax.money.MonetaryAmounts
     * @see javax.money.MonetaryAmountFactory
     * @see javax.money.MonetaryContext
     */
    public MonetaryAmount getMoneyWithContext(Number number, String currencyCode, MonetaryContext context){
        MonetaryContext monetaryContext = MonetaryContextBuilder.of()
                                                                .set(context)
                                                                .build();

        return MonetaryAmounts.getDefaultAmountFactory()
                              .setCurrency(currencyCode)
                              .setNumber(number)
                              .setContext(monetaryContext)
                              .create();
    }

    /**
     * Create a {@link javax.money.MonetaryAmount} that covers the following capabilities:
     * <ul>
     * <li>It must support a precision of 256.</li>
     * <li>It must support a scale of 128.</li>
     * <li>It must use {@link java.math.RoundingMode#FLOOR}</li>
     * </ul>
     * Within this test you should query a corresponding implementation type and then of the corresponding amount.
     *
     * @param number       the amount
     * @param currencyCode the currency code
     * @return an according money instance
     * @see javax.money.MonetaryAmounts
     * @see javax.money.MonetaryAmountFactory
     * @see javax.money.MonetaryContext
     */
    public MonetaryAmount getMoneyWithSpecificCapabilities(Number number, String currencyCode){
        MonetaryAmountFactoryQuery query =  MonetaryAmountFactoryQueryBuilder.of()
                                                                             .setPrecision(256)
                                                                             .setMaxScale(128)
                                                                             .set(RoundingMode.FLOOR)
                                                                             .build();

        MonetaryContext monetaryContext = MonetaryContextBuilder.of()
                                                                .setPrecision(256)
                                                                .setMaxScale(128)
                                                                .set(RoundingMode.FLOOR)
                                                                .build();

        MonetaryAmountFactory factory = MonetaryAmounts.getAmountFactory(query);

        return factory.setNumber(number)
                      .setCurrency(currencyCode)
                      .setContext(monetaryContext)
                      .create();
    }
}
