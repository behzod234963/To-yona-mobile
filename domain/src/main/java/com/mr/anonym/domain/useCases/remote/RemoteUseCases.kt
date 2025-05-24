package com.mr.anonym.domain.useCases.remote

import com.mr.anonym.domain.useCases.remote.card.AddCardUseCase
import com.mr.anonym.domain.useCases.remote.card.DeleteCardUseCase
import com.mr.anonym.domain.useCases.remote.card.GetAllCardUseCase
import com.mr.anonym.domain.useCases.remote.card.GetCardByIdUseCase
import com.mr.anonym.domain.useCases.remote.card.GetUserCardsUseCase
import com.mr.anonym.domain.useCases.remote.card.UpdateCardUseCase
import com.mr.anonym.domain.useCases.remote.party.AddPartyUseCase
import com.mr.anonym.domain.useCases.remote.party.DeletePartyRemoteUseCase
import com.mr.anonym.domain.useCases.remote.party.GetAllPartyUseCase
import com.mr.anonym.domain.useCases.remote.party.GetPartyByIdUseCase
import com.mr.anonym.domain.useCases.remote.party.GetUserPartiesUseCase
import com.mr.anonym.domain.useCases.remote.party.UpdatePartyUseCase
import com.mr.anonym.domain.useCases.remote.user.DeleteUserUseCase
import com.mr.anonym.domain.useCases.remote.user.GetUserByIdUseCase
import com.mr.anonym.domain.useCases.remote.user.LoginUserUseCase
import com.mr.anonym.domain.useCases.remote.user.RegisterUserUseCase
import com.mr.anonym.domain.useCases.remote.user.SearchUserUseCase
import com.mr.anonym.domain.useCases.remote.user.UpdateUserUseCase

data class RemoteUseCases(
//    User use cases
    val loginUserUseCase: LoginUserUseCase,
    val registerUserUseCase: RegisterUserUseCase,
    val getUserUseCase: GetUserByIdUseCase,
    val updateUserUseCase: UpdateUserUseCase,
    val searchUserUseCase: SearchUserUseCase,
    val decodeTokenUseCase:DecodeTokenUseCase,
    val deleteUserUseCase: DeleteUserUseCase,
//    Party use cases
    val addPartyUseCase: AddPartyUseCase,
    val updatePartyUseCase: UpdatePartyUseCase,
    val getAllPartyUseCase: GetAllPartyUseCase,
    val getPartyByIdUseCase: GetPartyByIdUseCase,
    val deletePartyUseCase: DeletePartyRemoteUseCase,
    val getUserPartiesUseCase: GetUserPartiesUseCase,
//    Card use cases
    val addCardUseCase: AddCardUseCase,
    val getAllCardUseCase: GetAllCardUseCase,
    val getCardByIdUseCase: GetCardByIdUseCase,
    val updateCardUseCase: UpdateCardUseCase,
    val deleteCardUseCase: DeleteCardUseCase,
    val getUserCardsUseCase: GetUserCardsUseCase,
)