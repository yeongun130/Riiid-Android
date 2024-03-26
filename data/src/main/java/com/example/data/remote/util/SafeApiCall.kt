package com.example.data.remote.util

import com.example.domain.exception.*
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(call: suspend () -> T): T {
    return try {
        call.invoke()
    } catch (e: HttpException) {
        val message = e.message
        throw when (e.code()) {
            400 -> BadRequestException(message = message)
            401 -> UnauthorizedException(message = message)
            403 -> ForbiddenException(message = message)
            404 -> NotFoundException(message = message)
            409 -> ConflictException(message = message)
            500, 501, 502, 503 -> ServerException(message = message)
            else -> OtherHttpException(
                code = e.code(),
                message = message
            )
        }
    } catch (e: SocketTimeoutException) {
        throw TimeOutException(message = e.message)
    } catch (e: UnknownHostException) {
        throw NoInternetException()
    } catch (e: Exception) {
        throw UnknownException(message = e.message)
    }
}