package jjo.practice.todo.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jjo.practice.todo.R
import jjo.practice.todo.data.model.UserEntity
import jjo.practice.todo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    // ViewBinding 이용하기
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container,false)
        val view = binding.root
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFetchUser()
    }

    private fun onFetchUser() {
        val user : UserEntity = UserEntity("JJo")
        //ViewBinding 을 이용해서 UI Update
        binding.tvHomeUser.text = user.userName
    }
}