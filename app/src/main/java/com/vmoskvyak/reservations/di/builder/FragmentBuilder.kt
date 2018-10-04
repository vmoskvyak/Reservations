package com.vmoskvyak.githubreposearch.di.builder

import com.vmoskvyak.reservations.ui.fragments.customers.CustomerListFragment
import com.vmoskvyak.reservations.ui.fragments.tables.TablesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributeCustomerListFragment(): CustomerListFragment

    @ContributesAndroidInjector
    abstract fun contributeTablesFragment(): TablesFragment

}
