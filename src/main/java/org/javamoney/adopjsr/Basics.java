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
import javax.money.MonetaryAmountFactory;
import javax.money.MonetaryContext;
import java.util.Collection;
import java.util.Locale;

/**
 * This class has to be implemented and helps us giving feedback on the JSR's API. This part of the
 * project deals with basic aspects such as getting currencies and amounts.
 * Created by Anatole on 07.03.14.
 */
public class Basics{

    /**
     * Get a CurrencyUnit using a currency code.
     * @param currencyCode the currency code
     * @return the corresponding CurrencyUnit instance.
     */
    public CurrencyUnit getProvidedCurrency(String currencyCode){
        throw new UnsupportedOperationException();
    }

    /**
     * Get a CurrencyUnit using a Locale, modeling the country.
     *
     * @param locale The country locale.
     * @return the corresponding CurrencyUnit instance.
     */
    public CurrencyUnit getProvidedCurrency(Locale locale){
        throw new UnsupportedOperationException();
    }

    /**
     * Create a custom CurrencyUnit.
     *
     * @param currencyCode         the currency code
     * @param numericCode          the numeric code.
     * @param defaultFractionUnits the default fraction units.
     * @return the new currency unit instance created.
     */
    public CurrencyUnit buildCustomCurrency(String currencyCode, int numericCode, int defaultFractionUnits){
        throw new UnsupportedOperationException();
    }

    /**
     * Build and register a CurrencyUnit.
     *
     * @param currencyCode         the currency code (non null).
     * @param numericCode          the numeric code.
     * @param defaultFractionUnits the default fraction units.
     * @return the CurrencyUnit. Additionally the unit should be registered,
     * so it accessible from {@link }MonetaryCurrencies}.
     */
    public CurrencyUnit buildAndRegisterCustomCurrency(String currencyCode, int numericCode, int defaultFractionUnits){
        throw new UnsupportedOperationException();
    }

    /**
     * Evaluate the current registered implementation types for amounts.
     *
     * @return the current amount types.
     */
    public Collection<Class<? extends MonetaryAmount>> getMonetaryAmountTypes(){
        throw new UnsupportedOperationException();
    }

    /**
     * Evaluate the current registered default implementation type for amounts.
     *
     * @return the current default amount type.
     */
    public Class<? extends MonetaryAmount> getDefaultMonetaryAmountType(){
        throw new UnsupportedOperationException();
    }

    /**
     * Lookup the corresponding factory for creating such amounts.
     *
     * @param moneyType the monetary amount's implementation class.
     * @return the corresponding factory, not null.
     */
    public <T extends MonetaryAmount> MonetaryAmountFactory<T> getMoneyFactory(Class<T> moneyType){
        throw new UnsupportedOperationException();
    }

    /**
     * Get an amount with the given amount and currency.
     *
     * @param number       the amount
     * @param currencyCode the currency code
     * @return a corresponding amount instance.
     */
    public MonetaryAmount getMoney(Number number, String currencyCode){
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a MonetaryAmount that covers the capabilities as defined in the MonetaryContext.
     *
     * @param number       the amount
     * @param currencyCode the currency code
     * @param context      the Monetary context
     * @return an according money instance
     */
    public MonetaryAmount getMoney(Number number, String currencyCode, MonetaryContext context){
        throw new UnsupportedOperationException();
    }
}
