package ja.tky.myapplication.ui.input

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ja.tky.myapplication.databinding.FragmentInputBinding

// TODO: 処理の確認
class InputFragment : Fragment() {

    private lateinit var inputViewModel: InputViewModel
    private var _binding: FragmentInputBinding? = null

    // onCreateView と onDestroyViewでのみ有効
    private val binding get() = _binding!!

    // onCreateView: FragmentのメインコンテンツとなるViewを生成して返す必要があるライフサイクルイベント
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // ViewModelを取得
        inputViewModel = ViewModelProvider(this).get(InputViewModel::class.java)

        // ジェネレート(生成)されたBindingクラスからViewをinflateできる
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        val root: View = binding.root // 生成された階層の親となるオプションのView

        val categoryLabel: TextView = binding.tvInput
        val tvCategoryVal: TextView = binding.tvCategoryVal
        val tvDateVal: TextView = binding.tvDateVal
        // val etInput: TextView = binding.etInput

        // 背景のレイアウトを取得
        val container: ConstraintLayout = binding.inputContainer

        // rootがviewになるので、View(画面)がタップされたら発火する
        root.setOnTouchListener { v, event ->
            println("画面がタップされました")
            // InputMethodManagerを取得
            val inputMethodManager: InputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            // キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(container.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            // 背景にフォーカスを移す
            container.requestFocus()
        }

        // LiveDataの値の変更を監視。変更を受け取ったらcategoryLabelに値をセット。
        inputViewModel.categoryLabel.observe(viewLifecycleOwner, Observer {
            categoryLabel.text = it
        })
        // LiveDataの値の変更を監視。変更を受け取ったらcategoryValに値をセット。
        inputViewModel.categoryVal.observe(viewLifecycleOwner, Observer {
            tvCategoryVal.text = it
        })
        // LiveDataの値の変更を監視。変更を受け取ったらcategoryValに値をセット。
        inputViewModel.dateVal.observe(viewLifecycleOwner, Observer {
            tvDateVal.text = it
        })

        return root

    }

    // onDestroyView: Fragmentが破棄されるタイミングで発火
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}