package com.shilpakala.showcase.ui

import androidx.lifecycle.ViewModel
import com.shilpakala.showcase.data.ShowcaseRepository

class ShowcaseViewModel : ViewModel() {
    val repository = ShowcaseRepository()
    val savedProductIds = linkedSetOf<String>()

    fun toggleSaved(productId: String) {
        if (!savedProductIds.add(productId)) {
            savedProductIds.remove(productId)
        }
    }
}
