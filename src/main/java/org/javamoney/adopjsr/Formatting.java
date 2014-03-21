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

import javax.money.format.AmountStyle;
import javax.money.format.CurrencyStyle;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

/**
 * Implement formatting.
 * Created by Anatole on 08.03.14.
 */
public class Formatting{

    /**
     * Access a predefined MonetaryFormat using the given style.
     *
     * @param locale the target locale
     * @return
     */
    public MonetaryAmountFormat getAmountFormat(Locale locale){
        return MonetaryFormats.getAmountFormat(locale);
    }

    /**
     * Access a predefined MonetaryFormat using the given style.
     *
     * @param style
     * @return
     */
    public MonetaryAmountFormat getAmountFormat(AmountStyle style){
        return MonetaryFormats.getAmountFormat(style);
    }

    /**
     * Create a customized MonetaryAmountFormat, with:
     * <ul>
     * <li>Locale.ENGLISH</li>
     * <li>Print the currency Symbol</li>
     * <li>Use an initial grouping size of 3, then go ahead with size 2.</li>
     * <li>Use the following pattern: " ##0.00  ¤ Mio;[##0.00] ¤ Mio"</li>
     * <li>Add a formatting rounding that divides the amount by 1000000.</li>
     * <li>Add a parsing rounding that multiplies the amount by 1000000.</li>
     * </ul>
     *
     * @return
     */
    public MonetaryAmountFormat createCustomFormat(){
        return MonetaryFormats.getAmountFormat(
                new AmountStyle.Builder(Locale.ENGLISH).setCurrencyStyle(CurrencyStyle.SYMBOL).setGroupingSizes(3, 2)
                        .setPattern(" ##0.00  ¤ Mio;[##0.00] ¤ Mio").create());
    }
}
