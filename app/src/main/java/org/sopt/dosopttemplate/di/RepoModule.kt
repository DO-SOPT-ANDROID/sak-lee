package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.local.repo.AuthRepoImpl
import org.sopt.dosopttemplate.domain.repo.AuthRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Singleton
    @Binds
    abstract fun providesAuthRepository(repoImpl: AuthRepoImpl): AuthRepo
}