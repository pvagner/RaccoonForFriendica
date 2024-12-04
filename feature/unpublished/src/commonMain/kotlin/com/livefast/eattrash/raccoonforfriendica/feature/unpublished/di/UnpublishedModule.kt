package com.livefast.eattrash.raccoonforfriendica.feature.unpublished.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

@Module
@ComponentScan("com.livefast.eattrash.raccoonforfriendica.feature.unpublished")
internal class UnpublishedModule

val featureUnpublishedModule = UnpublishedModule().module
