package movile.marcus.com.br.moviletest.setup

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnit4
import android.support.test.runner.AndroidJUnitRunner
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}