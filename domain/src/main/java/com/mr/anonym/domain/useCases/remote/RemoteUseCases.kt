package com.mr.anonym.domain.useCases.remote

import com.mr.anonym.domain.useCases.remote.party.GetAllPartyUseCase
import com.mr.anonym.domain.useCases.remote.user.GetUserByIdUseCase
import com.mr.anonym.domain.useCases.remote.user.LoginUserUseCase
import com.mr.anonym.domain.useCases.remote.user.RegisterUserUseCase
import com.mr.anonym.domain.useCases.remote.user.UpdateUserUseCase

data class RemoteUseCases(
//    User use cases
    val loginUserUseCase: LoginUserUseCase,
    val registerUserUseCase: RegisterUserUseCase,
    val getUserByIdUseCase: GetUserByIdUseCase,
    val updateUserUseCase: UpdateUserUseCase,
//    Party use cases
    val getAllPartyUseCase: GetAllPartyUseCase
)