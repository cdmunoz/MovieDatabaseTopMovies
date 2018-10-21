package co.cdmunoz.kotlinmoviedb.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import co.cdmunoz.kotlinmoviedb.BuildConfig
import co.cdmunoz.kotlinmoviedb.R
import co.cdmunoz.kotlinmoviedb.data.MovieItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movieItem = intent?.getSerializableExtra("THE_MOVIE") as MovieItem
        updateUI(movieItem)
    }

    private fun updateUI(movieItem: MovieItem) {
        Picasso.with(this)
                .load(BuildConfig.IMG_BASE_URL + movieItem.backdropPath)
                .into(detail_poster)
        detail_title.text = movieItem.title
        detail_overview.text = movieItem.overview
        detail_release_date.text = Html.fromHtml(
                resources.getString(R.string.detail_release_date, movieItem.releaseDate))
        detail_vote_average.text = Html.fromHtml(
                resources.getString(R.string.detail_vote_average, movieItem.voteAverage.toString()))
    }
}