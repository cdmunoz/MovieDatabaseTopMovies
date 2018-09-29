package co.cdmunoz.kotlinmoviedb.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.cdmunoz.kotlinmoviedb.R
import co.cdmunoz.kotlinmoviedb.data.MovieItem
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    companion object {
        val TAG = ListActivity::class.java.name
    }

    lateinit var moviesDbViewModel: MoviesDbViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        moviesDbViewModel = ViewModelProviders.of(this).get(MoviesDbViewModel::class.java)
        moviesDbViewModel.loadMovies()

        initObservers()
    }

    private fun initObservers() {
        moviesDbViewModel.getMoviesResult().observe(this, Observer<List<MovieItem>>{
            if (it != null){
                hello_text_view.text = "Items :::: ${it.toString()}"
            }
        })
    }
}
