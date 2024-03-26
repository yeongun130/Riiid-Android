package com.example.presentation.exception

import com.example.domain.exception.*

fun Throwable.exceptionHandling(
    badRequestAction: () -> Unit = {},
    forbiddenAction: () -> Unit = {},
    notFoundAction: () -> Unit = {},
    timeOutAction: () -> Unit = {},
    conflictAction: () -> Unit = {},
    serverAction: () -> Unit = {},
    unknownAction: () -> Unit = {},
) {
    when (this) {
        is BadRequestException -> badRequestAction()
        is ForbiddenException -> forbiddenAction()
        is NotFoundException -> notFoundAction()
        is TimeOutException -> timeOutAction()
        is ConflictException -> conflictAction()
        is ServerException -> serverAction()
        else -> unknownAction()
    }
}