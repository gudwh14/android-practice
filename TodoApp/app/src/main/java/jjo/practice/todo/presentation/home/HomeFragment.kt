package jjo.practice.todo.presentation.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import jjo.practice.todo.R
import jjo.practice.todo.data.model.UserEntity
import jjo.practice.todo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    /* ViewBinding 이용하기
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!*/

    private lateinit var dataBinding : FragmentHomeBinding
    private lateinit var user : UserEntity

    // startActivityForResult 는 deprecated 되었기 때문에 AndroidX , fragment 에서는 이렇게 사용한다.
    val requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        it->
        if(it.resultCode == Activity.RESULT_OK) {
            val intent = it.data
            // null 체크를 해줘야한다.
            if(intent != null) {
                user.userName = intent.getStringExtra("userName")!!
                dataBinding.user = user
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        _binding = FragmentHomeBinding.inflate(inflater, container,false)
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        val view = dataBinding.root
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewModel = this
        onFetchUser()
    }

    private fun onFetchUser() {
        user = UserEntity("JJo")
        /* ViewBinding 을 이용해서 UI Update
        binding.tvHomeUser.text = user.userName */
        dataBinding.user = user
    }

    fun onClickEditButton(view : View) {
        val intent = Intent(context,UserEditActivity::class.java)
        intent.putExtra("userName",user.userName)
        // activity 를 부를때는 launch 를 사용함.
        requestActivity.launch(intent)
    }

}