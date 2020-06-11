package com.example.movieappmvvm.ui.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappmvvm.data.model.MovieResponse
import com.example.movieappmvvm.data.model.ResultsItem
import com.example.movieappmvvm.R
import com.example.movieappmvvm.databinding.ActivityMovieBinding
import com.example.movieappmvvm.factory.GenreViewModelFactory
import com.example.movieappmvvm.factory.MovieViewModelFactory
import com.example.movieappmvvm.ui.detail.DetailActivity
import com.example.movieappmvvm.ui.genre.GenreActivity
import com.example.movieappmvvm.ui.genre.GenreViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import kotlinx.android.synthetic.main.activity_movie.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var movieViewModel: MovieViewModel

    private var page by Delegates.notNull<Int>()
    private var totalPage by Delegates.notNull<Int>()
    private var isLoading by Delegates.notNull<Boolean>()

    private lateinit var linearLayoutManager: LinearLayoutManager
    var genreId = 0
    var genreName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        linearLayoutManager = LinearLayoutManager(this)
        DaggerMovieComponent.create().inject(this)
        rvMovie.layoutManager = linearLayoutManager
        rvMovie.hasFixedSize()
        genreId = intent.getIntExtra("genreId", 1)
        genreName = intent.getStringExtra("genreName")
        page = 1
        totalPage = 0
        init()
        rvMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                Log.e("meesage", "Scrolled")
                val linearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                val countItem = linearLayoutManager?.itemCount
                val lastVisiblePosition =
                    linearLayoutManager?.findLastCompletelyVisibleItemPosition()
                val isLastPosition = countItem.minus(1) == lastVisiblePosition
                if (!isLoading && isLastPosition && page < 50) {
                    showLoading(true)
                    page = page.let { it.plus(1) }
                    init()
                }
            }
        })
    }

    fun init() {
        showLoading(true)
        movieViewModel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)
        movieViewModel.getMovieList(genreId, page)
            ?.observe(this, Observer<MovieResponse> { movieList ->
                hideLoading()
                Log.e("message","movieList $movieList")
                rvMovie.adapter = MovieAdapter(
                    this,
                    movieList.results as List<ResultsItem>,
                    object :
                        MovieAdapter.OnItemClickListener {
                        override fun onClick(view: View, itemView: ResultsItem) {
                            openDetailMovie(itemView.id)
                        }
                    })

            })
    }

    fun openDetailMovie(movieId: Int?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movieId", movieId)
        startActivity(intent)
    }

    private fun showLoading(isRefresh: Boolean) {
        isLoading = true
        progressbarMovie.visibility = View.VISIBLE
        rvMovie.visibility.let {
            if (isRefresh) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun hideLoading() {
        isLoading = false
        progressbarMovie.visibility = View.GONE
        rvMovie.visibility = View.VISIBLE
    }
}

@Component(modules = [MyModule::class])
interface MovieComponent {
    fun inject(activity: MovieActivity)
}


@Module
abstract class MyModule {

    @Binds
    abstract fun bindmovieviewmodelFactory(factory: MovieViewModelFactory): ViewModelProvider.Factory

}