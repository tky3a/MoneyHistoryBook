package ja.tky.myapplication.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ja.tky.myapplication.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {
    private lateinit var calendarViewModel: CalendarViewModel // ViewModelの変数を定義
    private var _binding: FragmentCalendarBinding? = null // FragmentCalendarBindingが存在していない場合はnull

    // binding変数を定義
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // CalendarViewModelを変数に代入　
        calendarViewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)
        // 生成されたBindingクラスからViewをinflate
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        // 生成された階層の親となるオプションのビュー
        var root: View = _binding!!.root
        // fragment_calendar.xmlのtvCalendarを変数に代入
        val textView: TextView = binding.tvCalendar

        // [重要] LiveDataの値の変更を監視。変更を受け取ったらTextViewに値をセット。
        calendarViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    // 初期化？
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}