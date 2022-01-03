package ja.tky.myapplication.ui.calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalendarViewModel: ViewModel() {
    private val text = MutableLiveData<String>().apply {
        value = "This is calendar Fragment"
    }
}