/*
 * Copyright 2019 Algorand, Inc.
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

package com.algorand.android.ui.common.walletconnect

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.algorand.android.R
import com.algorand.android.databinding.CustomWalletConnectTransactionSummaryViewBinding
import com.algorand.android.models.AssetInformation.Companion.ALGORAND_ID
import com.algorand.android.models.BaseAppCallTransaction
import com.algorand.android.models.BaseAssetTransferTransaction
import com.algorand.android.models.BasePaymentTransaction
import com.algorand.android.models.BaseWalletConnectTransaction
import com.algorand.android.utils.toShortenedAddress
import com.algorand.android.utils.viewbinding.viewBinding

class WalletConnectTransactionSummaryCardView(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = viewBinding(CustomWalletConnectTransactionSummaryViewBinding::inflate)

    init {
        initRootLayout()
    }

    fun initTransaction(transaction: BaseWalletConnectTransaction) {
        setTitle(transaction)
        setAmount(transaction)
        setAccountBalance(transaction)
        binding.transactionInfoImageView.isVisible = transaction.shouldShowWarningIndicator
    }

    private fun setTitle(transaction: BaseWalletConnectTransaction) {
        with(transaction) {
            binding.transactionAccountTextView.text = resources.getString(summaryTitleResId, summarySecondaryParameter)
        }
    }

    private fun setAmount(transaction: BaseWalletConnectTransaction) {
        val amount = transaction.transactionAmount ?: run {
            binding.transactionsAmountTextView.visibility = View.GONE
            binding.dotImageView.visibility = View.GONE
            return
        }
        val isAlgoTransaction = transaction is BasePaymentTransaction
        val otherAssetName = (transaction as? BaseAssetTransferTransaction)?.assetParams?.shortName
        binding.transactionsAmountTextView.setAmount(
            amount,
            transaction.assetDecimal,
            isAlgoTransaction,
            otherAssetName
        )
    }

    private fun setAccountBalance(transaction: BaseWalletConnectTransaction) {
        if (transaction.accountCacheData == null || transaction is BaseAppCallTransaction) return
        val transactionAssetId = when (transaction) {
            is BaseAssetTransferTransaction -> transaction.assetId
            is BasePaymentTransaction -> ALGORAND_ID
            else -> null
        }
        with(binding) {
            with(transaction.accountCacheData!!) {
                val accountName = account.name.takeIf { it.isNotBlank() } ?: account.address.toShortenedAddress()
                transactionAccountNameTextView.text = accountName
                transactionAccountTypeImageView.setImageResource(getImageResource())
                assetsInformation.firstOrNull { it.assetId == transactionAssetId }?.run {
                    accountBalanceTextView.setAmount(amount, decimals, isAlgorand(), shortName)
                } ?: run {
                    if (transaction is BaseAssetTransferTransaction) {
                        accountBalanceTextView.setAssetName(transaction.assetParams?.shortName.orEmpty(), false)
                    }
                }
            }
            accountSummaryContainer.visibility = View.VISIBLE
        }
    }

    private fun initRootLayout() {
        setBackgroundResource(R.drawable.bg_small_shadow)
    }
}
