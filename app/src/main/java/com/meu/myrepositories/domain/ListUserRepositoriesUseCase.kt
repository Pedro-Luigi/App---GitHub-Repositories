package com.meu.myrepositories.domain

import com.meu.myrepositories.core.UseCase
import com.meu.myrepositories.data.model.Repo
import com.meu.myrepositories.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase(private val repository: RepoRepository) : UseCase<String, List<Repo>>() {
    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}