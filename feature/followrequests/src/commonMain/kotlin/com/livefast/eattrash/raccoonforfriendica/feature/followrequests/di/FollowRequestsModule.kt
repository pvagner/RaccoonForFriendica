package com.livefast.eattrash.raccoonforfriendica.feature.followrequests.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

@Module
@ComponentScan("com.livefast.eattrash.raccoonforfriendica.feature.followrequests")
internal class FollowRequestsModule

val featureFollowRequestsModule = FollowRequestsModule().module
