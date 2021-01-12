package com.kit.showkitapp.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kit.showkitapp.R
import com.kit.showkitapp.adapter.CategoryAdapter
import com.kit.showkitapp.adapter.LanguageAdapter
import com.kit.showkitapp.model.CategoryIntrestModel
import com.kit.showkitapp.model.Data_Category
import com.kit.showkitapp.model.Data_Lang
import com.kit.showkitapp.model.LanguageModel
import com.kit.showkitapp.viewmodel.DashboardVM
import com.legal.smart.util.ProgressBarClass
import com.kit.showkitapp.utils.SessionManager
import com.mindorks.retrofit.coroutines.utils.Status
import com.shokh.sample.BaseActivity
import kotlinx.android.synthetic.main.activity_gender_birth.*
import kotlinx.android.synthetic.main.dailog_image_select.*
import kotlinx.android.synthetic.main.dailog_language.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SplashActivity : BaseActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapter: LanguageAdapter? = null
    internal lateinit var layoutManager: LinearLayoutManager
    var arrayList = ArrayList<Data_Lang>()


    private var recyclerViewCat: RecyclerView? = null
    private var adapterCat: CategoryAdapter? = null
    internal lateinit var layoutManagerCat: LinearLayoutManager
    var arrayListCat = ArrayList<Data_Category>()

    lateinit var dashboardVM: DashboardVM
    lateinit var dialogInterest: Dialog
    lateinit var dialog: Dialog

    var interest_selected = ""
    var language_selected = ""
    var isLoggedIn = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        dashboardVM = ViewModelProviders.of(this).get(DashboardVM::class.java)
        dialogInterest = Dialog(this)
        val dialog = Dialog(this)


        lifecycleScope.launch(Dispatchers.Main) {

            var isLogged = sessionManager.getData(SessionManager.IS_LOGGED_IN)
            var interest = sessionManager.getData(SessionManager.USER_INTEREST)
            var language = sessionManager.getData(SessionManager.USER_LANGUAGE)


            /*    isLogged.collect { value ->
                    isLoggedIn = value
                    Log.e("33", "" + isLoggedIn)

                    if (value == "1") {
                        val i = Intent(this@SplashActivity, HomeActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                }
                */

            isLogged.asLiveData().observe(this@SplashActivity)
            { value ->
                isLoggedIn = value
                Log.e("33", "" + isLoggedIn)

                if (value == "1") {
                    val i = Intent(this@SplashActivity, HomeActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }

            interest.asLiveData().observe(this@SplashActivity)
            { data ->
                interest_selected = data
                Log.e("44", "" + interest_selected)
            }

            language.asLiveData().observe(this@SplashActivity)
            { data ->
                if (isLoggedIn != "1") {
                    language_selected = data
                    Log.e("45", "" + language_selected)
                    if (interest_selected.isNullOrEmpty() == true && language_selected.isNullOrEmpty() == true) {
                        dashboardVM.getLanguageApi()
                    } else if (interest_selected.isNullOrEmpty() == false && language_selected.isNullOrEmpty() == false) {
                        Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                            val i = Intent(this@SplashActivity, LoginActivity::class.java)
                            startActivity(i)
                            finish()
                        }, 2000)
                    }
                }
            }
        }


        //observer
        getLanguageDataObserver()
        getCategoryDataObserver()


    }

    private fun initViewRecyclerLanguage(dailog: Dialog) {
        recyclerView = dailog.findViewById(R.id.rvLanguage) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        recyclerView?.setNestedScrollingEnabled(false)
        layoutManager = LinearLayoutManager(this)
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                recyclerView?.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView?.setLayoutManager(layoutManager)

    }

    private fun initViewRecyclerCategory(dailog: Dialog) {
        recyclerViewCat = dailog.findViewById(R.id.rvLanguage) as RecyclerView
        recyclerViewCat?.setHasFixedSize(true)
        recyclerViewCat?.setNestedScrollingEnabled(false)
        layoutManagerCat = GridLayoutManager(this, 3)
        recyclerViewCat?.setLayoutManager(layoutManagerCat)
    }

    fun getLanguageDataObserver() {

        dashboardVM.languageLivedata.observe(this, Observer {
            it?.let { resource ->
                Log.e("resp", resource.status.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        ProgressBarClass.dialog.dismiss()
                        var modelObj = resource.data as LanguageModel
                        Log.e("resp44", "" + modelObj.toString())
                        if (modelObj.status == 1) {
                            showLanguageDialog(modelObj.data)

//                            Toast.makeText(homeActivity, modelObj.message, Toast.LENGTH_LONG).show()
                        } else {
//                            Toast.makeText(homeActivity, modelObj.message, Toast.LENGTH_LONG).show()
                        }
                    }
                    Status.ERROR -> {
                        ProgressBarClass.dialog.dismiss()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        Log.e("resperror", it.message.toString())

                    }
                    Status.LOADING -> {
                        ProgressBarClass.progressBarCalling(this)
                    }
                }
            }
        })
    }


    fun getCategoryDataObserver() {

        dashboardVM.categoryLivedata.observe(this, Observer {
            it?.let { resource ->
                Log.e("resp", resource.status.toString())
                when (resource.status) {
                    Status.SUCCESS -> {
                        ProgressBarClass.dialog.dismiss()
                        var modelObj = resource.data as CategoryIntrestModel
                        Log.e("resp44", "" + modelObj.toString())
                        if (modelObj.status == 1) {
                            showInterestDialog(modelObj.data)

//                            Toast.makeText(homeActivity, modelObj.message, Toast.LENGTH_LONG).show()
                        } else {
//                            Toast.makeText(homeActivity, modelObj.message, Toast.LENGTH_LONG).show()
                        }
                    }
                    Status.ERROR -> {
                        ProgressBarClass.dialog.dismiss()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        Log.e("resperror", it.message.toString())

                    }
                    Status.LOADING -> {
                        ProgressBarClass.progressBarCalling(this)
                    }
                }
            }
        })
    }

    fun showLanguageDialog(data: ArrayList<Data_Lang>) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dailog_language)
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true)

        initViewRecyclerLanguage(dialog)

        arrayList = data
        adapter = LanguageAdapter(arrayList, activity)
        recyclerView?.adapter = adapter

        dialog.tvSaveLang.setOnClickListener()
        {
            dialog.dismiss()
            lifecycleScope.launch(Dispatchers.Main) {
                sessionManager.setData(SessionManager.USER_LANGUAGE, "1")
            }
            dashboardVM.getCategoryApi()
        }


        adapter?.notifyDataSetChanged();
        dialog.show()
    }

    fun showInterestDialog(data: ArrayList<Data_Category>) {
        dialogInterest = Dialog(this)
        dialogInterest.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogInterest.setContentView(R.layout.dailog_image_select)
        dialogInterest.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialogInterest.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        initViewRecyclerCategory(dialogInterest)

        arrayListCat = data
        adapterCat = CategoryAdapter(arrayListCat, activity)
        recyclerViewCat?.adapter = adapterCat

        dialogInterest.tvSave.setOnClickListener()
        {
            dialogInterest.dismiss()
            lifecycleScope.launch(Dispatchers.Main) {
                sessionManager.setData(SessionManager.USER_INTEREST, "1")
            }
            val i = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        dialogInterest.setCancelable(true)
        dialogInterest.show()
    }
}