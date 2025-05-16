package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.viewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.state.ListState
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.utils.AddEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    context: Context,
    savedState: SavedStateHandle,
    private val localUseCases: LocalUseCases,
    private val remoteUseCases: RemoteUseCases
) : ViewModel() {

    private val _id = mutableIntStateOf(-1)

    private val _user = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _user
    private val _party = mutableStateOf(PartysItem() )
    val party: State<PartysItem> = _party

    private val _cards = mutableStateOf(ListState().cards)
    val cards: State<List<CardModel>> = _cards
    private val _card = mutableStateOf(CardModel())
    val card: State<CardModel> = _card
    private val _cardValue = mutableStateOf(context.getString(R.string.empty))
    val cardValue: State<String> = _cardValue
    private val _cardHolderValue = mutableStateOf("")
    val cardHolderValue: State<String> = _cardHolderValue
    private val _startDate = mutableStateOf("")
    val startDate: State<String> = _startDate
    private val _endDate = mutableStateOf("")
    val endDate: State<String> = _endDate
    private val _time = mutableStateOf("")
    val time: State<String> = _time
    private val _oldEventDateTime = mutableStateOf("")
    val oldEventDateTime: State<String> = _oldEventDateTime

    private val _selectedEventIndex = mutableIntStateOf(0)
    val selectedEventIndex: State<Int> = _selectedEventIndex
    private val _selectedOldEventIndex = mutableIntStateOf(0)
    val selectedOldEventIndex: State<Int> = _selectedOldEventIndex
    private val _selectedOldEventValue = mutableStateOf("")
    val selectedOldEventValue: State<String> = _selectedOldEventValue
    private val _isAddEventState = mutableStateOf( true )
    val isAddEventState: State<Boolean> = _isAddEventState

    init {
        savedState.get<Int>("eventID")?.let { eventID ->
            _id.intValue = eventID
            if (eventID != -1) {
                getCards()
                getPartyByID(eventID)
            } else {
                getCards()
            }
        }
    }
    fun addEvent(userId: Int,partyModel: PartysItem) = viewModelScope.launch {
        remoteUseCases.addEventUseCase.execute(userId, partyModel).collect {
            _isAddEventState.value = it
        }
    }
    fun getPartyByID(id: Int) = viewModelScope.launch {
        remoteUseCases.getPartyByIdUseCase.execute(id).collect {
            _party.value = it
        }
    }
    fun getUserById(id: Int) = viewModelScope.launch {
        remoteUseCases.getUserByIdUseCase.execute(id).collect {
            _user.value = it
        }
    }
    fun getCards() = viewModelScope.launch {
        localUseCases.getCardsUseCase().collect {
            _cards.value = it
            if (
                it.isNotEmpty() &&
                _id.intValue == -1
            ) {
                _cardValue.value = it[0].cardNumber
                _cardHolderValue.value = it[0].cardHolder
                _card.value = it[0]
            }
        }
    }
    fun onEvent(event: AddEventState) {
        when (event) {
            is AddEventState.ChangeCardHolder -> {
                _cardHolderValue.value = event.holder
            }

            is AddEventState.ChangeCardNumber -> {
                _cardValue.value = event.cardNumber
            }

            is AddEventState.ChangeEndDate -> {
                _endDate.value = event.endDate
            }

            is AddEventState.ChangeEventIndex -> {
                _selectedEventIndex.intValue = event.index
            }

            is AddEventState.ChangeStartDate -> {
                _startDate.value = event.startDate
            }

            is AddEventState.ChangeTime -> {
                _time.value = event.time
            }

            is AddEventState.ChangeCardModel -> {
                _card.value = event.card
            }

            is AddEventState.ChangeEventOldIndex -> {
                _selectedOldEventIndex.intValue = event.oldIndex
            }

            is AddEventState.ChangeEventOldValue -> {
                _selectedOldEventValue.value = event.oldValue
            }
        }
    }
}