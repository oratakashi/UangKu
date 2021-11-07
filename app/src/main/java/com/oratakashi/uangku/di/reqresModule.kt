package com.oratakashi.uangku.di

import com.oratakashi.uangku.data.CategoryDataSource
import com.oratakashi.uangku.data.CategoryRepository
import com.oratakashi.uangku.domain.CategoryInteractor
import com.oratakashi.uangku.domain.CategoryUsecase
import com.oratakashi.uangku.ui.menu.settings.category.CategoryViewModel
import com.oratakashi.uangku.ui.menu.settings.category.create.CreateCategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reqresModule = module {
    single<CategoryRepository> { CategoryDataSource(get()) }
    single<CategoryUsecase> { CategoryInteractor(get()) }

    viewModel { CreateCategoryViewModel(get(), get()) }
    viewModel { CategoryViewModel(get(), get()) }
}