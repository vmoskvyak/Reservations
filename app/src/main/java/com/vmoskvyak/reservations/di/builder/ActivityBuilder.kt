package com.vmoskvyak.githubreposearch.di.builder

import com.vmoskvyak.reservations.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}
