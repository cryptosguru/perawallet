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
 */

package com.algorand.android.ui.accountselection.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.algorand.android.databinding.ItemAccountErrorSimpleBinding
import com.algorand.android.models.BaseAccountSelectionListItem

class AccountSelectionAccountErrorItemViewHolder(
    private val binding: ItemAccountErrorSimpleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BaseAccountSelectionListItem.BaseAccountItem.AccountErrorItem) {
        with(binding) {
            accountDisplayNameTextView.text = item.displayName
            errorIconImageView.isVisible = item.isErrorIconVisible
            accountIconImageView.setAccountIcon(item.accountIcon)
        }
    }

    companion object {
        fun create(parent: ViewGroup): AccountSelectionAccountErrorItemViewHolder {
            val binding = ItemAccountErrorSimpleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AccountSelectionAccountErrorItemViewHolder(binding)
        }
    }
}
