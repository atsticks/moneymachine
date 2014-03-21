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

import javax.money.MonetaryAmount;
import javax.money.MonetaryOperator;
import javax.money.MonetaryRoundings;

/**
 * Access roundings based on currency, as well as using customized rounding.
 *
 * @see MonetaryRoundings
 * Created by Anatole on 08.03.14.
 */
public class Rounding{

    /**
     * Round Amounts with the default rounding.
     *
     * @return
     */
    public MonetaryAmount roundWithDefaultRounding(MonetaryAmount amount){
        // TODO implement this
        return null;
    }

    /**
     * Round Amounts with a default rounding for the given currency.
     *
     * @return
     */
    public MonetaryAmount roundBasedOnCurrency(MonetaryAmount amount){
        // TODO implement this
        return null;
    }

    /**
     * Round Amounts with a cash rounding for the given currency.
     *
     * @return
     */
    public MonetaryAmount roundForCash(MonetaryAmount amount){
        // TODO implement this
        return null;
    }

    /**
     * Round Amounts with a mathematical rounding:
     * <ul>
     *     <li>Use {@link java.math.RoundingMode#HALF_UP}</li>
     *     <li>Use a scale of 3</li>
     * </ul>
     * @see javax.money.MonetaryContext
     * @return
     */
    public MonetaryAmount roundMathematical(MonetaryAmount amount){
        // TODO implement this
        return null;
    }
}
