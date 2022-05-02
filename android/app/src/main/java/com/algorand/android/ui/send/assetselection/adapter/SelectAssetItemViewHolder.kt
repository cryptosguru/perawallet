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

package com.algorand.android.ui.send.assetselection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.algorand.android.databinding.ItemSendAssetSelectionBinding
import com.algorand.android.models.BaseSelectAssetItem

class SelectAssetItemViewHolder(
    private val binding: ItemSendAssetSelectionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BaseSelectAssetItem.SelectAssetItem) {
        with(binding) {
            with(item) {
                assetNameView.setupUIWithId(isVerified, name, id, isAlgo)
                assetCurrencyValueTextView.isVisible = isAmountInSelectedCurrencyVisible
                assetCurrencyValueTextView.text = formattedSelectedCurrencyCompactValue
                assetBalanceTextView.text = formattedCompactAmount
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): SelectAssetItemViewHolder {
            val binding = ItemSendAssetSelectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SelectAssetItemViewHolder(binding)
        }
    }
}
