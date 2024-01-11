package com.darrenthiores.ecoswap.android.presentation.add_item

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.ecoswap.domain.item.use_cases.AddItem
import com.darrenthiores.ecoswap.presentation.add_item.AddItemEvent
import com.darrenthiores.ecoswap.presentation.add_item.AddItemViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidAddItemViewModel @Inject constructor(
    private val addItem: AddItem
): ViewModel() {
    private val viewModel by lazy {
        AddItemViewModel(
            addItem = addItem,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent

    var photos = mutableStateListOf<Uri>()
        private set

    fun onEvent(event: AddItemEvent) {
        viewModel.onEvent(event)
    }

    fun onSelectPhoto(
        uris: List<Uri>
    ) {
        photos.clear()
        photos.addAll(uris)
    }

    fun onAddPhoto(
        uri: Uri
    ) {
        if (photos.size == 5) {
            return
        }

        if (!photos.contains(uri)) {
            photos.add(uri)
        }
    }

    fun onSelectPhoto(
        uri: Uri
    ) {
        photos
            .removeIf {
                it == uri
            }
    }

    fun upload() {
        viewModel.onEvent(
            AddItemEvent.Upload(
                emptyList()
            )
        )
    }
}