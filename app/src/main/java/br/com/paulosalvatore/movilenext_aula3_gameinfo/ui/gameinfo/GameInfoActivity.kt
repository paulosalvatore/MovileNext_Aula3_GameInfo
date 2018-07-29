package br.com.paulosalvatore.movilenext_aula3_gameinfo.ui.gameinfo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.paulosalvatore.movilenext_aula3_gameinfo.R
import br.com.paulosalvatore.movilenext_aula3_gameinfo.databinding.ActivityGameInfoBinding
import br.com.paulosalvatore.movilenext_aula3_gameinfo.model.Game
import br.com.paulosalvatore.movilenext_aula3_gameinfo.ui.gameadd.GameAddActivity
import br.com.paulosalvatore.movilenext_aula3_gameinfo.utils.contentView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_game_info.*

class GameInfoActivity : AppCompatActivity() {

	/*val binding: ActivityGameInfoBinding by lazy {
		DataBindingUtil.setContentView<ActivityGameInfoBinding>(
				this,
				R.layout.activity_game_info
		)
	}*/

	private val binding: ActivityGameInfoBinding by contentView(R.layout.activity_game_info)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val game = Game(
				"Donkey Kong",
				1981,
				"http://1.bp.blogspot.com/-sFKHwpLH77I/UtdLMO4XLZI/AAAAAAAAH1A/K79ZfRUUMn0/s1600/Donkey+Kong.png",
				4.3
		)
		binding.game = game

		tvRating.setOnClickListener {
			game.rating = 4.5
		}

//		testesRxJava()

		fabAdd.setOnClickListener {
			val intent = Intent(this, GameAddActivity::class.java)
			startActivity(intent)
		}
	}

	fun testesRxJava() {
		val tag = "RxJava"
		Observable.just(1, 2, 3)
				.map { it * 2 }
				.filter { it < 6 }
				.subscribe { Log.i(tag, "$it") }

		/*val observable = Observable.just(1, 2, 3)


		observable.subscribe {
			Log.i(tag, "accept: $it")
		}*/

		/*val observer = object : Observer<Int> {
			override fun onComplete() {
				Log.i(tag, "onComplete")
			}

			override fun onSubscribe(d: Disposable) {
				Log.i(tag, "onSubscribe")
			}

			override fun onNext(t: Int) {
				Log.i(tag, "onNext $t")
			}

			override fun onError(e: Throwable) {
				Log.e(tag, "onError", e)
			}
		}

		observable.subscribe(observer)*/
	}
}
