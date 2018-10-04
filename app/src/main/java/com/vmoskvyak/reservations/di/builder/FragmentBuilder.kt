package com.vmoskvyak.githubreposearch.di.builder

import com.vmoskvyak.reservations.ui.fragments.CustomerListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributeCustomerListFragment(): CustomerListFragment

}
