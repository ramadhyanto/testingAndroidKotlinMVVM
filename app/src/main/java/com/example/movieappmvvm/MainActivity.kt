package com.example.movieappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.movieappmvvm.factory.DaggerViewModelFactory
import com.example.movieappmvvm.factory.GenreViewModelFactory
import com.example.movieappmvvm.factory.MovieViewModelFactory
import com.example.movieappmvvm.ui.detail.DetailViewModel
import com.example.movieappmvvm.ui.genre.GenreActivity
import com.example.movieappmvvm.ui.genre.GenreViewModel
import com.example.movieappmvvm.ui.movie.MovieActivity
import com.example.movieappmvvm.ui.movie.MovieViewModel
import com.example.movieappmvvm.viewmodels.MyViewModel
import dagger.Binds
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

//@Component(modules = [MultiBindModule::class])
//interface MyComponent {
//        fun inject(activity: GenreActivity)
//        fun inject(activity: MovieActivity)
//}
//
//
//@Module abstract class MultiBindModule {
//    @Binds
//    abstract fun bindsViewModelFactory(factory: DaggerViewModelFactory):ViewModelProvider.Factory
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(GenreViewModel::class)
//    abstract fun bindgenreiewmodelFactory(viewModel: GenreViewModel):ViewModelProvider.Factory
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MovieViewModel::class)
//    abstract fun bindmovieiewmodelFactory(viewModel: MovieViewModel):ViewModelProvider.Factory
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailViewModel::class)
//    abstract fun binddetailiewmodelFactory(viewModel: DetailViewModel):ViewModelProvider.Factory
//}
//
//
//
//@MustBeDocumented
//@Target(AnnotationTarget.FUNCTION)
//@Retention(AnnotationRetention.RUNTIME)
//@MapKey
//internal annotation class ViewModelKey( val value :KClass<out ViewModel>)

//@Component (modules = [MyModule::class])
//interface MyComponent {
//    fun inject(activity: GenreActivity)
//    fun inject(activity: MovieActivity)
//}
//
//
//@Module
//abstract class MyModule {
//
//    @Binds
//    abstract fun bindgenreviewmodelFactory(factory: GenreViewModelFactory):ViewModelProvider.Factory
//
//    @Binds
//    abstract fun bindmovieviewmodelFactory(factory: MovieViewModelFactory):ViewModelProvider.Factory
//
//    @Binds
//    abstract fun binddetailviewmodelFactory(factory: MovieViewModelFactory):ViewModelProvider.Factory
//
//}