package jjo.practice.todo.presentation.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jjo.practice.todo.R
import jjo.practice.todo.databinding.FragmentTodoBinding
import jjo.practice.todo.presentation.MainActivity
import jjo.practice.todo.presentation.dialog.AddTodoDialogFragment

class TodoFragment : Fragment() {
    private var _binding : FragmentTodoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTodoAdd.setOnClickListener {
            AddTodoDialogFragment().show((activity as MainActivity).supportFragmentManager,null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
