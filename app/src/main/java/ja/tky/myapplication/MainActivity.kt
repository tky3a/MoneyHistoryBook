package ja.tky.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ja.tky.myapplication.databinding.ActivityMainBinding
import ja.tky.myapplication.ui.input.InputFragment

// InputFragmentを継承
class MainActivity : AppCompatActivity(), InputFragment.OnboardSignUpTermsOfServiceListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // インスタンス作成
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BottomNavigationView取得
        val navView: BottomNavigationView = binding.btmNavView
        println("kakunin!!!")
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            // ナビゲーションで設定しているフラグメントの情報を渡す（ここの設定がされていない場合表示がうまくいかない）
            setOf(
                R.id.navigationInput,
                R.id.navigationCalendar
            )
        )
        // 画面表示
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    // MARK: OnboardSignUpTermsOfServiceListener
    // Fragmentで作成したinterfaceのメソッドにアクセス
    override fun onClickNext() {
        val navView: BottomNavigationView = binding.btmNavView
        println("test, ${navView.visibility}")
        if (navView.visibility == View.GONE) {
            navView.visibility = View.VISIBLE
        } else {
            navView.visibility = View.GONE
        }
        println("どのタイミング？")
    }
}
