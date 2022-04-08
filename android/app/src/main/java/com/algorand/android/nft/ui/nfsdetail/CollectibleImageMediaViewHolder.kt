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

package com.algorand.android.nft.ui.nfsdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import com.algorand.android.databinding.ItemCollectibleImageMediaBinding
import com.algorand.android.nft.ui.model.BaseCollectibleMediaItem
import com.algorand.android.utils.loadImage

class CollectibleImageMediaViewHolder(
    private val binding: ItemCollectibleImageMediaBinding
) : BaseCollectibleMediaViewHolder(binding.root) {

    override fun bind(item: BaseCollectibleMediaItem) {
        if (item !is BaseCollectibleMediaItem.ImageCollectibleMediaItem) return
        with(binding.collectibleImageCollectibleImageView) {
            doOnLayout {
                context.loadImage(
                    createPrismPreviewImageUrl(item.previewUrl, measuredWidth),
                    onResourceReady = { showImage(it, !item.isOwnedByTheUser) },
                    onLoadFailed = { showText(item.errorText) }
                )
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): CollectibleImageMediaViewHolder {
            val binding = ItemCollectibleImageMediaBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return CollectibleImageMediaViewHolder(binding)
        }
    }
}
