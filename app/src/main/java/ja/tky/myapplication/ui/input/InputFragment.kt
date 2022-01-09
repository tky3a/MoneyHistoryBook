package ja.tky.myapplication.ui.input

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ja.tky.myapplication.databinding.FragmentInputBinding

// TODO: 処理の確認
class InputFragment : Fragment() {

    // Activityに通知したいイベントをinterfaceとして定義
    interface OnboardSignUpTermsOfServiceListener {
        fun onClickNext()
    }

    private lateinit var inputViewModel: InputViewModel
    private var _binding: FragmentInputBinding? = null
    private var listener: OnboardSignUpTermsOfServiceListener? = null

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
        val bottomSheetTv: LinearLayout = binding.bottomSheet

        // 背景のレイアウトを取得
        val container: ConstraintLayout = binding.inputContainer
        //
        val layoutParams = bottomSheetTv.layoutParams

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

        // TODO: タップしたらフッターにメニューが表示されるようにしているが、これをモーダルにする
        tvCategoryVal.setOnClickListener {
            println("it ${tvCategoryVal.text}")
            println("${layoutParams.height}")
            if (layoutParams.height > 0) {
                layoutParams.height = 0
                // Activityにアクションを通知
                onClickNext(it)
            } else {
                layoutParams.height = 825
                // Activityにアクションを通知
                onClickNext(it)
            }
            bottomSheetTv.layoutParams = layoutParams
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TAG", "onViewCreated: called")
        super.onViewCreated(view, savedInstanceState)
    }

    // ContextにFragmentがアタッチされた際に呼び出されるメソッド
    // フラグメントのライフサイクルにおいて、最初のメソッド
    // フラグメントとアクティビティ（コンテクスト）が関連付けられた時に呼ばれる
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // OnboardSignUpTermsOfServiceListener のインスタンス化
        listener = context as? OnboardSignUpTermsOfServiceListener
        if (listener == null) {
            throw ClassCastException("$context must implement OnboardSignUpTermsOfServiceListener")
        }
    }

    // フラグメントがアクティビティから切り離されるときに呼ばれるメソッド
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    // onDestroyView: Fragmentが破棄されるタイミングで発火
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Activityに通知するイベントメソッド
    private fun onClickNext(view: View) {
        listener?.onClickNext()
    }

}