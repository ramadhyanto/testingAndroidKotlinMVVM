package com.example.movieappmvvm.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.ActivityDetailBinding
import com.example.movieappmvvm.factory.DetailViewModelFactory
import com.example.movieappmvvm.factory.MovieViewModelFactory
import com.example.movieappmvvm.ui.genre.GenreActivity
import com.example.movieappmvvm.ui.genre.GenreViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var detailViewModel: DetailViewModel
    var movieId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        DaggerDetailComponent.create().inject(this)
        movieId = intent.getIntExtra("movieId", 1)
        detailViewModel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
        binding.detail = detailViewModel
        init(movieId)
    }

    fun init(movieId: Int) {
        detailViewModel.getDetailList(movieId)
    }
}

@Component(modules = [MyModule::class])
interface DetailComponent {
    fun inject(activity: DetailActivity)
}


@Module
abstract class MyModule {

    @Binds
    abstract fun binddetailviewmodelFactory(factory: DetailViewModelFactory): ViewModelProvider.Factory

}