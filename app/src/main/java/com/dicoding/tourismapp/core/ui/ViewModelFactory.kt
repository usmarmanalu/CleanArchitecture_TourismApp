package com.dicoding.tourismapp.core.ui

import android.content.*
import androidx.lifecycle.*
import com.dicoding.tourismapp.core.di.*
import com.dicoding.tourismapp.core.domain.usecase.*
import com.dicoding.tourismapp.detail.*
import com.dicoding.tourismapp.favorite.*
import com.dicoding.tourismapp.home.*

class ViewModelFactory private constructor(private val tourismUseCase: TourismUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideTourismUseCase(
                    context
                )
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(tourismUseCase) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(tourismUseCase) as T
            }

            modelClass.isAssignableFrom(DetailTourismViewModel::class.java) -> {
                DetailTourismViewModel(tourismUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class:" + modelClass.name)
        }
}