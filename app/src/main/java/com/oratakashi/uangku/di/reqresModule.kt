package com.oratakashi.uangku.di

import com.oratakashi.uangku.data.CategoryDataSource
import com.oratakashi.uangku.data.CategoryRepository
import com.oratakashi.uangku.data.TransactionDataSource
import com.oratakashi.uangku.data.TransactionRepository
import com.oratakashi.uangku.domain.CategoryInteractor
import com.oratakashi.uangku.domain.CategoryUsecase
import com.oratakashi.uangku.domain.TransactionInteractor
import com.oratakashi.uangku.domain.TransactionUsecase
import com.oratakashi.uangku.ui.menu.settings.category.CategoryViewModel
import com.oratakashi.uangku.ui.menu.settings.category.create.CreateCategoryViewModel
import com.oratakashi.uangku.ui.menu.transaction.form.FormTransactionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reqresModule = module {
    single<CategoryRepository> { CategoryDataSource(get()) }
    single<TransactionRepository> { TransactionDataSource(get()) }
    single<CategoryUsecase> { CategoryInteractor(get()) }
    single<TransactionUsecase> { TransactionInteractor(get()) }

    viewModel { CreateCategoryViewModel(get(), get()) }
    viewModel { CategoryViewModel(get(), get()) }
    viewModel { FormTransactionViewModel(get(), get()) }
}