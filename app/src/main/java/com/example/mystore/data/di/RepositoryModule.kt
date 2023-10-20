package com.example.mystore.data.di

import android.content.Context
import com.example.mystore.MyApplication
import com.example.mystore.data.repository.OnBoardingOperationImpl
import com.example.mystore.data.repository.Repository
import com.example.mystore.domain.repository.OnBoardingOperations
import com.example.mystore.domain.usecase.UseCase
import com.example.mystore.domain.usecase.deletecart.DeleteCartUseCase
import com.example.mystore.domain.usecase.deletefavouritecart.DeleteFavouriteCartUseCase
import com.example.mystore.domain.usecase.getallproduct.GetAllProductUseCase
import com.example.mystore.domain.usecase.getallproduct.ReadOnBoardingUseCase
import com.example.mystore.domain.usecase.getallproduct.SaveOnBoardingUseCase
import com.example.mystore.domain.usecase.getallproductcart.GetAllProductCartUseCase
import com.example.mystore.domain.usecase.getselectedproduct.GetSelectedProductUseCase
import com.example.mystore.domain.usecase.readfavouritecart.GetFavouriteCartUseCase
import com.example.mystore.domain.usecase.savecartproduct.SaveCartProductUseCase
import com.example.mystore.domain.usecase.saveproduct.InsertProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataStoreOperation(
        @ApplicationContext context: Context,
    ): OnBoardingOperations = OnBoardingOperationImpl(context = context)

    @Provides
    @Singleton
    fun provideUseCase(repository: Repository): UseCase {
        return UseCase(
            getAllProductUseCase = GetAllProductUseCase(repository),
            insertProductsUseCase = InsertProductUseCase(repository),
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            insertCartUseCase = SaveCartProductUseCase(repository),
            readCartUseCase = GetAllProductCartUseCase(repository),
            readFavouriteCart = GetFavouriteCartUseCase(repository),
            deleteFavouriteCart = DeleteFavouriteCartUseCase(repository),
            deleteCart = DeleteCartUseCase(repository),
            readSelectedProductUseCase = GetSelectedProductUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideApplicationContext() = MyApplication()
}