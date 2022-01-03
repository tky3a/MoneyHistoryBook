package ja.tky.myapplication.ui.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        inputViewModel = ViewModelProvider(this).get(InputViewModel::class.java)

        _binding = FragmentInputBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvInput
        inputViewModel.text.observe(viewLifecycleOwner, Observer {
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