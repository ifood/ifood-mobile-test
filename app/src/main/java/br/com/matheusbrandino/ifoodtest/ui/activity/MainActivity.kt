package br.com.matheusbrandino.ifoodtest.ui.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.matheusbrandino.ifoodtest.R
import br.com.matheusbrandino.ifoodtest.data.viewmodel.TweetViewModel
import br.com.matheusbrandino.ifoodtest.ui.fragment.ErrorFragment
import br.com.matheusbrandino.ifoodtest.ui.fragment.TweetListFragment
import br.com.matheusbrandino.ifoodtest.ui.fragment.UsernameFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: TweetViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show(UsernameFragment())
        viewModel.tweets().observe(this, Observer { show(TweetListFragment(), true) })
        viewModel.error().observe(this, Observer { show(ErrorFragment(), true) })

    }

    private fun show(fragment: Fragment, stacked: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.mainFrame, fragment)

        if (stacked) transaction.addToBackStack(null)

        transaction.commit()
    }
}
