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
import javax.money.convert.ConversionContext;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRate;
import javax.money.convert.MonetaryConversions;
import java.util.Currency;

/**
 * API test class that allos to test out the API for currency conversion. Hint the available providers can be
 * evaluated by calling {@link javax.money.convert.MonetaryConversions#getProviderNames()}, by default the moneta
 * RO comes with the following providers:
 * <ul>
 *     <li>IMF - International Monetary Fonds (current day rates).</li>
 *     <li>ECB - European Central Bank (current day rates)</li>
 *     <li>ECB-HIST90 - European Central Bank (historic rates, back to 1990).</li>
 * </ul>
 * Created by Anatole on 21.03.14.
 * @see javax.money.convert.MonetaryConversions
 */
public class Conversions{

    /**
     * Get the an exchange rate provided by the International Monetary Fonds.
     * @return
     */
    public ExchangeRate getExchangeRateFromIMF(CurrencyUnit src, CurrencyUnit tgt){
        throw new UnsupportedOperationException();
    }

    /**
     * Get the an exchange rate provided by the International Monetary Fonds.
     * @return
     */
    public ExchangeRate getExchangeRateWithTime(CurrencyUnit src, CurrencyUnit tgt, long timestamp){
        throw new UnsupportedOperationException();
    }

    /**
     * Converts an amount to the given target currency, based on the given timestamp.
     * @param tgt The target currency
     * @param amount the amount to be converted
     * @param timestamp the target timestamp
     * @return the converted amount, not null.
     */
    public MonetaryAmount convertAmount(CurrencyUnit tgt, MonetaryAmount amount, long timestamp){
        throw new UnsupportedOperationException();
    }
}
