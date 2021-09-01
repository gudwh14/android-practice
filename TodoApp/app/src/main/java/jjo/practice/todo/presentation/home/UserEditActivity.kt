package jjo.practice.todo.presentation.home

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import jjo.practice.todo.R
import jjo.practice.todo.databinding.ActivityUserEditBinding

class UserEditActivity : AppCompatActivity() {
    lateinit var dataBinding : ActivityUserEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_user_edit)
        val view = dataBinding.root
        setContentView(view)

        val intent = intent
        dataBinding.userName = intent.getStringExtra("userName")
        dataBinding.activity = this
    }

    fun onClickEditButton(view : View) {
        val intent = Intent()
        intent.putExtra("userName",dataBinding.etEditUserUser.text.toString())
        // setResult 를 통해서 intent 값 콜백 해주기
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}