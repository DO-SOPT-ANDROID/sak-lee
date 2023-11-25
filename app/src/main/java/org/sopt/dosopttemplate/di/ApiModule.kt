package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.remote.service.HomeApiService
import org.sopt.dosopttemplate.data.remote.service.SoptApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideSignService(@Sopt retrofit: Retrofit): SoptApiService =
        retrofit.create(SoptApiService::class.java)

    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeApiService =
        retrofit.create(HomeApiService::class.java)
}