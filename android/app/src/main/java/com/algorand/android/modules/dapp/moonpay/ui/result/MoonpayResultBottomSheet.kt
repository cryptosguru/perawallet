/*
 *  Copyright 2022 Pera Wallet, LDA
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.algorand.android.modules.dapp.moonpay.ui.result

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.algorand.android.R
import com.algorand.android.core.DaggerBaseBottomSheet
import com.algorand.android.databinding.BottomSheetMoonpayResultBinding
import com.algorand.android.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoonpayResultBottomSheet : DaggerBaseBottomSheet(R.layout.bottom_sheet_moonpay_result, false, null) {

    private val binding by viewBinding(BottomSheetMoonpayResultBinding::bind)

    private val args by navArgs<MoonpayResultBottomSheetArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        loadData()
    }

    private fun initUi() {
        binding.positiveButton.setOnClickListener {
            navBack()
        }
    }

    private fun loadData() {
        val status = args.transactionStatus
        with(binding) {
            context?.let {
                resultImageView.setImageDrawable(ContextCompat.getDrawable(it, status.iconRes))
                resultImageView.setColorFilter(ContextCompat.getColor(it, status.iconTintRes))
            }
            resultTitleTextView.setText(status.titleRes)
            resultDescriptionTextView.setText(status.descriptionRes)
            algorandUserView.setAddress(args.walletAddress, showAddButton = false)
        }
    }
}
