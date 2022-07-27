package com.meu.myrepositories.data.repositories

import com.meu.myrepositories.core.RemoteException
import com.meu.myrepositories.data.services.GitHubService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoryImpl(private val service: GitHubService): RepoRepository {

    override suspend fun listRepositories(user: String) = flow{
        try {
            val listRepositories = service.listRepositories(user)
            emit(listRepositories)
        } catch (ex:HttpException){
            throw RemoteException("USER NOT FOUND")
        }
    }
}