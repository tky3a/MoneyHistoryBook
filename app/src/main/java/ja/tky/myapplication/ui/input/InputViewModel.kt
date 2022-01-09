package ja.tky.myapplication.ui.input

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class InputViewModel : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    val onlyDate: LocalDate = LocalDate.now()
    // NOTE: TextViewに表示するテキスト用のLiveData.
    // 変更可のためMutableLiveDataで作成する
    private val _categoryLabel = MutableLiveData<String>().apply {
        value = "カテゴリー"
    }
    // カテゴリー値の初期値
    private val _categoryVal = MutableLiveData<String>().apply {
        // TODO: 動的に初期値を決定し取得
        value = "食費"
    }
    // 日付値の初期値
    private val _dateVal = MutableLiveData<String>().apply {
        // カレンダーインスタンス取得
        val c = Calendar.getInstance()
        // 年取得
        val year = c.get(Calendar.YEAR)
        // 月取得
        val month = c.get(Calendar.MONTH)
        // 日取得
        val day = c.get(Calendar.DAY_OF_MONTH)

        value = "${year}/${month+1}/${day}"
    }
    val categoryLabel: LiveData<String> = _categoryLabel
    val categoryVal: LiveData<String> = _categoryVal
    val dateVal: LiveData<String> = _dateVal
}