package com.example.controlefinanceiro.di

import android.content.Context
import androidx.room.Room
import com.example.controlefinanceiro.data.database.AppDatabase
import com.example.controlefinanceiro.data.dao.CartaoDao
import com.example.controlefinanceiro.data.dao.ContaDao
import com.example.controlefinanceiro.data.dao.ReceitaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "controle_financeiro_db"
        ).build()
    }

    @Provides
    fun provideReceitaDao(database: AppDatabase): ReceitaDao = database.receitaDao()

    @Provides
    fun provideContaDao(database: AppDatabase): ContaDao = database.contaDao()

    @Provides
    fun provideCartaoDao(database: AppDatabase): CartaoDao = database.cartaoDao()
}
