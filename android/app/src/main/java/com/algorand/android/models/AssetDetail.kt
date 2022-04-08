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

package com.algorand.android.models

import java.math.BigDecimal

data class AssetDetail(
    override val assetId: Long,
    override val fullName: String?,
    override val shortName: String?,
    override val isVerified: Boolean = false,
    override val fractionDecimals: Int?,
    override val usdValue: BigDecimal?,
    override val assetCreator: AssetCreator?
) : BaseAssetDetail(assetId, fullName, shortName, isVerified, fractionDecimals, usdValue, assetCreator) {

    // TODO remove this function after deleting AssetInformation
    fun convertToAssetInformation(): AssetInformation {
        return AssetInformation(
            assetId = assetId,
            isVerified = isVerified,
            creatorPublicKey = assetCreator?.publicKey,
            shortName = shortName,
            fullName = fullName
        )
    }
}
