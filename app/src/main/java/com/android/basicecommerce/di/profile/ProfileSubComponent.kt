package com.android.basicecommerce.di.profile

import com.android.basicecommerce.presentation.profile.ProfileFragment
import dagger.Subcomponent

@ProfileScope
@Subcomponent(modules = [ProfileModule::class])
interface ProfileSubComponent {

    fun inject(profileActivity: ProfileFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(): ProfileSubComponent

    }

}