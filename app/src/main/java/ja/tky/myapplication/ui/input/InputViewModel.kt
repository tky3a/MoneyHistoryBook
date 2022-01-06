package ja.tky.myapplication.ui.input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InputViewModel : ViewModel() {
    // NOTE: TextViewに表示するテキスト用のLiveData.
    // 変更可のためMutableLiveDataで作成する
    private val _text = MutableLiveData<String>().apply {
        value = "This is input Fragment"
    }
    val text: LiveData<String> = _text
}