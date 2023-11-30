package com.darrenthiores.ecoswap.presentation.add_item

import com.darrenthiores.ecoswap.domain.item.use_cases.AddItem
import com.darrenthiores.ecoswap.domain.utils.Resource
import com.darrenthiores.ecoswap.domain.utils.UiEvent
import com.darrenthiores.ecoswap.utils.flow.toCommonFlow
import com.darrenthiores.ecoswap.utils.flow.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddItemViewModel(
    private val addItem: AddItem,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(AddItemState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: AddItemEvent) {
        when (event) {
            is AddItemEvent.OnBrandChange -> {
                _state.update {
                    it.copy(
                        brand = event.brand
                    )
                }
            }
            is AddItemEvent.OnDescriptionChange -> {
                _state.update {
                    it.copy(
                        description = event.description
                    )
                }
            }
            is AddItemEvent.OnLocationChange -> {
                _state.update {
                    it.copy(
                        location = event.location
                    )
                }
            }
            is AddItemEvent.OnNameChange -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }
            is AddItemEvent.OnSelectCategory -> {
                _state.update {
                    it.copy(
                        category = event.category,
                        categoryDropDownOpen = false
                    )
                }
            }
            is AddItemEvent.OnSelectCondition -> {
                _state.update {
                    it.copy(
                        condition = event.condition,
                        conditionDropDownOpen = false
                    )
                }
            }
            is AddItemEvent.OnTotalChange -> {
                _state.update {
                    it.copy(
                        total = event.total
                    )
                }
            }
            is AddItemEvent.Upload -> {
                upload(event.photos)
            }
            AddItemEvent.ToggleCategoryDropDown -> {
                _state.update {
                    it.copy(
                        categoryDropDownOpen = !it.categoryDropDownOpen
                    )
                }
            }
            AddItemEvent.ToggleConditionDropDown -> {
                _state.update {
                    it.copy(
                        conditionDropDownOpen = !it.conditionDropDownOpen
                    )
                }
            }
        }
    }

    private fun upload(
        photos: List<String>
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val stateValue = state.value
            val result = addItem(
                photos = photos,
                name = stateValue.name,
                description = stateValue.description,
                category = stateValue.category ?: return@launch,
                total = stateValue.total.toIntOrNull() ?: 1,
                condition = stateValue.condition ?: return@launch,
                brand = stateValue.brand,
                location = stateValue.location
            )

            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }

                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
        }
    }
}