@file:Suppress("PackageName")

package com.example.KSHLatio.message.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.FragmentTutorialItemBinding

class TutorialItemFragment : Fragment() {

    private var _binding: FragmentTutorialItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorialItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = arguments?.getString(ARG_TITLE) ?: ""
        val desc = arguments?.getString(ARG_DESC) ?: ""
        val iconRes = arguments?.getInt(ARG_ICON, R.drawable.ic_eco) ?: R.drawable.ic_eco

        binding.tvTutorialTitle.text = title
        binding.tvTutorialDesc.text = desc
        binding.ivTutorialIcon.setImageResource(iconRes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_DESC = "desc"
        private const val ARG_ICON = "icon"

        fun newInstance(title: String, desc: String, iconRes: Int): TutorialItemFragment {
            val fragment = TutorialItemFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESC, desc)
            args.putInt(ARG_ICON, iconRes)
            fragment.arguments = args
            return fragment
        }
    }
}