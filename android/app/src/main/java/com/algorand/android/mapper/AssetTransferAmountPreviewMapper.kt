/*
 * Copyright 2022 Pera Wallet, LDA
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License
 *
 */

package com.algorand.android.mapper

import com.algorand.android.models.AssetTransferAmountPreview
import com.algorand.android.models.BaseAccountAssetData
import com.algorand.android.utils.AssetName
import javax.inject.Inject

class AssetTransferAmountPreviewMapper @Inject constructor() {

    fun mapTo(
        accountAssetData: BaseAccountAssetData.BaseOwnedAssetData,
        enteredAmountSelectedCurrencyValue: String?,
        collectiblePrismUrl: String?,
        isCollectibleOwnedByUser: Boolean
    ): AssetTransferAmountPreview {
        return AssetTransferAmountPreview(
            shortName = AssetName.createShortName(accountAssetData.shortName),
            decimals = accountAssetData.decimals,
            formattedSelectedCurrencyValue = accountAssetData.formattedSelectedCurrencyCompactValue,
            assetId = accountAssetData.id,
            fullName = AssetName.create(accountAssetData.name),
            isAlgo = accountAssetData.isAlgo,
            isVerified = accountAssetData.isVerified,
            formattedAmount = accountAssetData.formattedCompactAmount,
            isAmountInSelectedCurrencyVisible = accountAssetData.isAmountInSelectedCurrencyVisible,
            enteredAmountSelectedCurrencyValue = enteredAmountSelectedCurrencyValue,
            collectiblePrismUrl = collectiblePrismUrl,
            isCollectibleOwnedByUser = isCollectibleOwnedByUser
        )
    }
}