package com.example.movieappmvvm.ui.genre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.ActivityGenreBinding
import com.example.movieappmvvm.data.model.GenreResponse
import com.example.movieappmvvm.data.model.GenresItem
import com.example.movieappmvvm.factory.GenreViewModelFactory
import com.example.movieappmvvm.ui.movie.MovieActivity
import com.example.movieappmvvm.ui.movie.MovieViewModel
import com.example.movieappmvvm.viewmodels.MyViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import kotlinx.android.synthetic.main.activity_genre.*
import javax.inject.Inject

class GenreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenreBinding
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var genreViewModel: GenreViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_genre)
        DaggerGenreComponent.create().inject(this)
        linearLayoutManager = LinearLayoutManager(this)
        rvGenre.layoutManager = linearLayoutManager
        rvGenre.hasFixedSize()
        init()
    }

    fun init() {
        progressbarGenre.visibility = View.VISIBLE
        genreViewModel = ViewModelProviders.of(this,factory).get(GenreViewModel::class.java)
        genreViewModel.getGenreList()?.observe(this, Observer<GenreResponse> {
            genreList ->
            progressbarGenre.visibility = View.GONE
            rvGenre.adapter = GenreAdapter(
                this,
                genreList.genres as List<GenresItem>,
                object :
                    GenreAdapter.OnItemClickListener {
                    override fun onClick(view: View, itemView: GenresItem) {
                        openMovieByGenre(itemView.id, itemView.name)
                    }
                })
        })
    }

    fun openMovieByGenre(id:Int?, name:String?) {
        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra("genreId",id)
        intent.putExtra("genreName", name)
        startActivity(intent)
    }


}

@Component(modules = [MyModule::class])
interface GenreComponent {
    fun inject(activity: GenreActivity)
}


@Module
abstract class MyModule {

    @Binds
    abstract fun bindgenreviewmodelFactory(factory: GenreViewModelFactory):ViewModelProvider.Factory

}