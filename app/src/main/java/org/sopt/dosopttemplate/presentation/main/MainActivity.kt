package org.sopt.dosopttemplate.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * 빌드 할때 만들어 BindingCLass
         * 큰단위
         * 데이터 바인딩 컴파일
         * 컴파일 조각들이 빌드 속에
         * **/
        binding = ActivityMainBinding.inflate(layoutInflater)
//        binding =  DataBindingUtil.setContentView(this,R.layout.activity_main)
        setContentView(binding.root)
        login()
    }

    private fun login() {
        binding.btnSignIn.setOnClickListener {
            if (binding.etvId.text.length < MAXIMUM_ID_LENGTH) {
                loginSucceed()
            } else {
                loginFailed()
            }
        }
    }

    private fun loginSucceed() {
        Snackbar.make(
            binding.root,
            "로그인 성공",
            Snackbar.LENGTH_SHORT,
        ).show()
    }

    private fun loginFailed() {
        Snackbar.make(
            binding.root,
            "6글자 이하로 써주세요",
            Snackbar.LENGTH_SHORT,
        ).show()
    }

    companion object {
        private const val MAXIMUM_ID_LENGTH = 6
    }

}