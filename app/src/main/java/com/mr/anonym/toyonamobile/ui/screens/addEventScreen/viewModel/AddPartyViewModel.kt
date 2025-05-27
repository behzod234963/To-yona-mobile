package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.viewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.state.ListState
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.utils.AddEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPartyViewModel @Inject constructor(
    context: Context,
    savedState: SavedStateHandle,
    sharedPreferences: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases
) : ViewModel() {

    private val _id = mutableIntStateOf(sharedPreferences.getID())

    private val _user = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _user

    private val _party = mutableStateOf(PartysItem())
    val party: State<PartysItem> = _party

    private val _cards = mutableStateOf(ListState().cards)
    val cards: State<List<CardModel>> = _cards
    private val _card = mutableStateOf(CardModel())
    val card: State<CardModel> = _card
    private val _cardValue = mutableStateOf(context.getString(R.string.empty))
    val cardValue: State<String> = _cardValue

    private val _startDate = mutableStateOf("")
    val startDate: State<String> = _startDate
    private val _endDate = mutableStateOf("")
    val endDate: State<String> = _endDate

    private val _titleValue = mutableStateOf( "" )
    val titleValue: State<String> = _titleValue
    private val _otherFieldValue = mutableStateOf( "" )
    val otherFieldValue: State<String> = _otherFieldValue

    private val _selectedEventIndex = mutableIntStateOf(0)
    val selectedEventIndex: State<Int> = _selectedEventIndex
    private val _isPartyAdded = mutableStateOf( true )
    val isPartyAdded: State<Boolean> = _isPartyAdded
    private val _isPartyUpdated = mutableStateOf( true )
    val isPartyUpdated: State<Boolean> = _isPartyUpdated

    init {
        getUserById()
        getUserCards()
        savedState.get<Int>("eventID")?.let { eventID ->
            _id.intValue = eventID
            if (eventID != -1) {
                getPartyByID(eventID)
            }
        }
    }
    fun addParty(partyModel: PartysItem) = viewModelScope.launch {
        remoteUseCases.addPartyUseCase.execute(partyModel).collect {
            _isPartyAdded.value = it.status
        }
    }
    fun updateParty(partyID: Int, partyModel: PartysItem) = viewModelScope.launch {
        remoteUseCases.updatePartyUseCase.execute(partyID,partyModel).collect {
            _isPartyUpdated.value = it
        }
    }
    fun getUserCards() = viewModelScope.launch {
        remoteUseCases.getUserCardsUseCase.execute(_id.intValue).collect {
            _cards.value = it
            _cardValue.value = it[0].number
        }
    }
    fun getPartyByID(id: Int) = viewModelScope.launch {
        remoteUseCases.getPartyByIdUseCase.execute(id).collect {
            _party.value = it
            _titleValue.value = it.name
            _selectedEventIndex.intValue = when(it.type){
                "0" -> 0
                "1" -> 1
                "2" -> 2
                "3" -> 3
                else -> 4
            }
            _otherFieldValue.value = it.type
            _startDate.value = it.startTime
            _endDate.value = it.endTime
        }
    }
    fun getUserById() = viewModelScope.launch {
        remoteUseCases.getUserUseCase.execute(_id.intValue).collect {
            _user.value = it
        }
    }
    fun onEvent(event: AddEventState) {
        when (event) {
            is AddEventState.ChangeCardNumber -> _cardValue.value = event.cardNumber
            is AddEventState.ChangeEndDate -> _endDate.value = event.endDate
            is AddEventState.ChangeEventIndex -> _selectedEventIndex.intValue = event.index
            is AddEventState.ChangeStartDate -> _startDate.value = event.startDate
            is AddEventState.ChangeCardModel -> _card.value = event.card
            is AddEventState.ChangeTitle -> _titleValue.value = event.title
            is AddEventState.ChangeOtherField -> _otherFieldValue.value = event.fieldValue
        }
    }
}