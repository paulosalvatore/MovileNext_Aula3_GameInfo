package br.com.paulosalvatore.movilenext_aula3_gameinfo.ui.gameadd

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import br.com.paulosalvatore.movilenext_aula3_gameinfo.R
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Flowables
import kotlinx.android.synthetic.main.activity_game_add.*
import java.util.concurrent.TimeUnit

class GameAddActivity : AppCompatActivity() {

	private lateinit var disposable: Disposable

	override fun onDestroy() {
		super.onDestroy()
		disposable.dispose()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_game_add)

		val nameChangeObservable = RxTextView
				.textChanges(etName)
				.skip(1)
				.debounce(800, TimeUnit.MILLISECONDS)
				.observeOn(AndroidSchedulers.mainThread())
				.toFlowable(BackpressureStrategy.LATEST)

		val yearChangeObservable = RxTextView
				.textChanges(etYear)
				.skip(1)
				.debounce(800, TimeUnit.MILLISECONDS)
				.observeOn(AndroidSchedulers.mainThread())
				.toFlowable(BackpressureStrategy.LATEST)

		disposable = Flowables.combineLatest(
				nameChangeObservable,
				yearChangeObservable
		) { newName: CharSequence,
			newYear: CharSequence ->

			val nameValid = newName.length > 3
			if (!nameValid) {
				etName.error = "Invalid name"
			}

			val yearValid = newYear.length == 4
			if (!yearValid) {
				etYear.error = "Invalid year"
			}

			nameValid && yearValid
		}.subscribe { formValid ->
			btAdd.setBackgroundColor(
					ContextCompat.getColor(
							applicationContext,
							if (formValid)
								R.color.colorPrimaryDark
							else
								R.color.gray
					)
			)
		}
	}
}
