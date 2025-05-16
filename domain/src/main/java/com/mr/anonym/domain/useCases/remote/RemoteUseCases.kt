package com.mr.anonym.domain.useCases.remote

import com.mr.anonym.domain.useCases.remote.party.AddEventUseCase
import com.mr.anonym.domain.useCases.remote.party.DeleteEventRemoteUseCase
import com.mr.anonym.domain.useCases.remote.party.GetAllPartyUseCase
import com.mr.anonym.domain.useCases.remote.party.GetPartyByIdUseCase
import com.mr.anonym.domain.useCases.remote.user.GetUserByIdUseCase
import com.mr.anonym.domain.useCases.remote.user.LoginUserUseCase
import com.mr.anonym.domain.useCases.remote.user.RegisterUserUseCase
import com.mr.anonym.domain.useCases.remote.user.SearchUserUseCase
import com.mr.anonym.domain.useCases.remote.user.UpdateUserUseCase

data class RemoteUseCases(
//    User use cases
    val loginUserUseCase: LoginUserUseCase,
    val registerUserUseCase: RegisterUserUseCase,
    val getUserByIdUseCase: GetUserByIdUseCase,
    val updateUserUseCase: UpdateUserUseCase,
    val searchUserUseCase: SearchUserUseCase,
//    Party use cases
    val addEventUseCase: AddEventUseCase,
    val getAllPartyUseCase: GetAllPartyUseCase,
    val getPartyByIdUseCase: GetPartyByIdUseCase,
    val deleteEventUseCase: DeleteEventRemoteUseCase
)