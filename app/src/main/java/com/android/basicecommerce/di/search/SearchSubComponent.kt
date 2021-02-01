package com.android.basicecommerce.di.search

import com.android.basicecommerce.presentation.search.SearchActivity
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = [SearchModule::class])
interface SearchSubComponent {

    fun inject(searchActivity: SearchActivity)

    @Subcomponent.Factory
    interface Factory {

        fun create(): SearchSubComponent

    }

}