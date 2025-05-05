package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.viewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.MyEventsModel
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.toyonamobile.presentation.state.LocalDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.viewModel.MyEventsViewModel

@HiltViewModel
class AddEventViewModel @Inject constructor(
    context: Context,
    savedState: SavedStateHandle,
    private val localUseCases: LocalUseCases
) : ViewModel(){

    private val _id = mutableStateOf( -1 )
    private val _cards = mutableStateOf(LocalDataState().cards)
    val cards: State<List<CardModel>> = _cards
    private val _card = mutableStateOf(CardModel() )
    val card: State<CardModel> = _card
    private val _eventModel = mutableStateOf(MyEventsModel() )
    val eventModel: State<MyEventsModel> = _eventModel
    private val _cardValue = mutableStateOf(
        if (_id.value == -1){
            context.getString(R.string.empty)
        }else{
            _eventModel.value.cardNumber
        }
    )
    val cardValue: State<String> = _cardValue

    init {
        savedState.get<Int>("eventID")?.let { eventID->
            if (eventID != -1){
                getEventByID(eventID)
                _id.value = eventID
            }else{
                getCards()
            }
        }
    }
    fun getCards() = viewModelScope.launch {
        localUseCases.getCardsUseCase().collect {
            _cards.value = it
            if (it.isNotEmpty()) {
                _cardValue.value = it[0].cardNumber
                _card.value = it[0]
            }
        }
    }
    fun getEventByID(id:Int) = viewModelScope.launch {
        localUseCases.getEventByIdUseCase.execute(id).collect {
            _eventModel.value = it
        }
    }
    fun insertEvent(event: MyEventsModel) = viewModelScope.launch {
        localUseCases.insertEventUseCase.execute(event)
    }
    fun changeCardValue(card: String){
        _cardValue.value = card
    }
    fun changeCardModel(card: CardModel) = viewModelScope.launch {
        _card.value = card
    }
}