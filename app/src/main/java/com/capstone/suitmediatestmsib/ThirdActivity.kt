package com.capstone.suitmediatestmsib

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.suitmediatestmsib.databinding.ActivityThirdBinding
import com.capstone.suitmediatestmsib.response.DataItem

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var thirdViewModel: ThirdViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.ivBack.setOnClickListener {
            finish()
        }

        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rv.layoutManager = layoutManager
        binding.rv.addItemDecoration(itemDecoration)

        thirdViewModel = helpViewModel(this@ThirdActivity)
        thirdViewModel.userResponse.observe(this){ userReponse -> setUserData(userReponse.data) }
       thirdViewModel.Loadingld.observe(this){ showLoading(it) }
    }
    private fun setUserData(user : List<DataItem>){
        val listUser = getUserGithub(user)
        val adapter = UserAdapter(listUser)
        binding.rv.adapter = adapter
    }
    private fun getUserGithub(userGithub: List<DataItem>): ArrayList<User> {
        val listUser = ArrayList<User>()
        for(userData in userGithub){
            val user = User(userData.firstName , userData.avatar, userData.lastName, userData.email)
            listUser.add(user)
        }
        return listUser
    }
    private fun helpViewModel(activity: AppCompatActivity): ThirdViewModel {
        val vmhelp = VmHelper.getInstance(activity.application)
        return ViewModelProvider(activity, vmhelp).get(ThirdViewModel::class.java)
    }
    //help to get viewModel for implement in main class
    class VmHelper private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
        companion object {
            private var getinstance: VmHelper? = null
            fun getInstance(application: Application): VmHelper {
                if (getinstance == null) {
                    synchronized(VmHelper::class.java) {
                        getinstance = VmHelper(application)
                    }
                }
                return getinstance as VmHelper
            }
        }
        override fun <Test : ViewModel> create(modelClass: Class<Test>): Test {
            if (modelClass.isAssignableFrom(ThirdViewModel::class.java)) {
                return ThirdViewModel(mApplication) as Test
            } else {
                throw IllegalArgumentException("ViewModel class not found: ${modelClass.name}")
            }
        }
    }
    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}