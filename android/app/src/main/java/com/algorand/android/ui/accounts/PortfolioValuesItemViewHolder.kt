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

package com.algorand.android.ui.accounts

import android.view.LayoutInflater
import android.view.ViewGroup
import com.algorand.android.databinding.ItemPortfolioValuesBinding
import com.algorand.android.models.BaseViewHolder
import com.algorand.android.ui.common.listhelper.BaseAccountListItem

class PortfolioValuesItemViewHolder(
    override val binding: ItemPortfolioValuesBinding,
    private val portfolioValuesListener: PortfolioValuesListener
) : BasePortfolioValuesItemViewHolder(binding) {

    override fun bindPortfolioItem(item: BaseAccountListItem.BasePortfolioValueItem) {
        if (item !is BaseAccountListItem.BasePortfolioValueItem.PortfolioValuesItem) return
        with(binding) {
            algoHoldingsTextView.text = item.formattedAlgoHoldings
            assetHoldingsTextView.text = item.formattedAssetHoldings
            portfolioValueTextView.text = item.formattedPortfolioValue
            buyAlgoButton.setOnClickListener { portfolioValuesListener.onBuyAlgoClick() }
            portfolioValueTitleTextView.setOnClickListener { portfolioValuesListener.onPortfolioInfoClick(item) }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            portfolioValuesListener: PortfolioValuesListener
        ): BaseViewHolder<BaseAccountListItem> {
            val binding = ItemPortfolioValuesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PortfolioValuesItemViewHolder(binding, portfolioValuesListener)
        }
    }
}
