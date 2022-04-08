/*
 * Copyright 2022 Pera Wallet, LDA
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.algorand.android.usecase

import com.algorand.android.mapper.AccountAssetDataMapper
import com.algorand.android.models.AssetDetail
import com.algorand.android.models.AssetHolding
import com.algorand.android.models.BaseAccountAssetData
import com.algorand.android.models.BaseAssetDetail
import com.algorand.android.utils.DEFAULT_ASSET_DECIMAL
import com.algorand.android.utils.formatAmount
import com.algorand.android.utils.formatAsCurrency
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import javax.inject.Inject

class AccountAssetAmountUseCase @Inject constructor(
    private val algoPriceUseCase: AlgoPriceUseCase,
    private val accountAssetDataMapper: AccountAssetDataMapper
) {

    // TODO: 16.03.2022 Find a better name since it returns OwnedAssetData but not amount
    fun getAssetAmount(
        assetHolding: AssetHolding,
        assetItem: AssetDetail
    ): BaseAccountAssetData.BaseOwnedAssetData.OwnedAssetData {
        val selectedCurrencySymbol = algoPriceUseCase.getSelectedCurrencySymbolOrEmpty()
        val safeDecimal = assetItem.fractionDecimals ?: DEFAULT_ASSET_DECIMAL
        val assetAmountInSelectedCurrency = getAssetAmountInSelectedCurrency(assetHolding, assetItem)
        return accountAssetDataMapper.mapToOwnedAssetData(
            assetDetail = assetItem,
            amount = assetHolding.amount,
            formattedAmount = assetHolding.amount.formatAmount(safeDecimal),
            amountInSelectedCurrency = assetAmountInSelectedCurrency,
            formattedSelectedCurrencyValue = assetAmountInSelectedCurrency.formatAsCurrency(selectedCurrencySymbol),
        )
    }

    fun getAssetAmountInSelectedCurrency(
        assetHolding: AssetHolding,
        assetItem: BaseAssetDetail
    ): BigDecimal {
        val selectedCurrencyUsdConversionRate = algoPriceUseCase.getUsdToSelectedCurrencyConversionRate()
        val safeAssetUsdValue = assetItem.usdValue ?: ZERO
        val safeDecimal = assetItem.fractionDecimals ?: DEFAULT_ASSET_DECIMAL
        return assetHolding.amount.toBigDecimal().movePointLeft(safeDecimal)
            .multiply(selectedCurrencyUsdConversionRate)
            .multiply(safeAssetUsdValue)
    }
}
