package com.kit.showkitapp.fargment

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kit.showkitapp.R
import com.kit.showkitapp.activity.HomeActivity
import com.kit.showkitapp.activity.LoginActivity
import com.kit.showkitapp.activity.SearchActivity
import com.kit.showkitapp.adapter.CategoryAdapter
import com.kit.showkitapp.adapter.LanguageAdapter
import com.kit.showkitapp.model.CategoryIntrestModel
import com.kit.showkitapp.model.Data_Category
import com.kit.showkitapp.model.Data_Lang
import com.kit.showkitapp.model.LanguageModel
import com.kit.showkitapp.utils.SessionManager
import com.kit.showkitapp.viewmodel.DashboardVM
import com.legal.smart.util.ProgressBarClass
import com.mindorks.retrofit.coroutines.utils.Status
import kotlinx.android.synthetic.main.dailog_image_select.*
import kotlinx.android.synthetic.main.dailog_language.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mobileapp.voice.util.BaseFragment


class DashboardFragment(var homeActivity: HomeActivity) : BaseFragment() {

    private var recyclerView: RecyclerView? = null
    private var adapter: LanguageAdapter? = null
    internal lateinit var layoutManager: LinearLayoutManager
    var arrayList = ArrayList<Data_Lang>()


    private var recyclerViewCat: RecyclerView? = null
    private var adapterCat: CategoryAdapter? = null
    internal lateinit var layoutManagerCat: LinearLayoutManager
    var arrayListCat = ArrayList<Data_Category>()

    lateinit var sessionManager: SessionManager

    lateinit var dashboardVM: DashboardVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dashboardVM = ViewModelProviders.of(this).get(DashboardVM::class.java)
        sessionManager = SessionManager(homeActivity)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        lifecycleScope.launch {
            sessionManager.setData(SessionManager.IS_LOGGED_IN, "1")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //observer
        getLanguageDataObserver()
        getCategoryDataObserver()


        openFragmentChild(TrendingFragment(homeActivity))

        viewTrending.visibility = View.VISIBLE

        view.tvForYou.setOnClickListener()
        {
            tvForYou.setTextColor(ContextCompat.getColor(homeActivity, R.color.black))
            tvTrending.setTextColor(ContextCompat.getColor(homeActivity, R.color.dark_grey))
            viewForYou.visibility = View.VISIBLE
            viewTrending.visibility = View.GONE
            openFragmentChild(ForYouFragment(homeActivity))
        }
        view.tvTrending.setOnClickListener()
        {
            tvTrending.setTextColor(ContextCompat.getColor(homeActivity, R.color.black))
            tvForYou.setTextColor(ContextCompat.getColor(homeActivity, R.color.dark_grey))
            viewForYou.visibility = View.GONE
            viewTrending.visibility = View.VISIBLE

            openFragmentChild(TrendingFragment(homeActivity))
        }

        view.imgLang.setOnClickListener()
        {
            dashboardVM.getLanguageApi()
//            showLanguageDialog()
        }

        view.imgInterest.setOnClickListener()
        {
            dashboardVM.getCategoryApi()
//            showInterestDialog()
        }

        view.imgSearch.setOnClickListener()
        {
            var intent = Intent(homeActivity, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initViewRecyclerLanguage(dailog: Dialog) {
        recyclerView = dailog.findViewById(R.id.rvLanguage) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        recyclerView?.setNestedScrollingEnabled(false)
        layoutManager = LinearLayoutManager(homeActivity)
        recyclerView?.setLayoutManager(layoutManager)
    }

    private fun initViewRecyclerCategory(dailog: Dialog) {
        recyclerViewCat = dailog.findViewById(R.id.rvLanguage) as RecyclerView
        recyclerViewCat?.setHasFixedSize(true)
        recyclerViewCat?.setNestedScrollingEnabled(false)
        layoutManagerCat = GridLayoutManager(homeActivity, 3)
        recyclerViewCat?.setLayoutManager(layoutManagerCat)
    }

    fun getLanguageDataObserver() {

        dashboardVM.languageLivedata.observe(homeActivity, Observer {
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
                        Toast.makeText(homeActivity, it.message, Toast.LENGTH_LONG).show()
                        Log.e("resperror", it.message.toString())

                    }
                    Status.LOADING -> {
                        ProgressBarClass.progressBarCalling(homeActivity)
                    }
                }
            }
        })
    }


    fun getCategoryDataObserver() {

        dashboardVM.categoryLivedata.observe(homeActivity, Observer {
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
                        Toast.makeText(homeActivity, it.message, Toast.LENGTH_LONG).show()
                        Log.e("resperror", it.message.toString())

                    }
                    Status.LOADING -> {
                        ProgressBarClass.progressBarCalling(homeActivity)
                    }
                }
            }
        })
    }

    fun openFragmentChild(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout_dash, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showLanguageDialog(data: ArrayList<Data_Lang>) {
        val dialog = Dialog(homeActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dailog_language)
        /* dialog.getWindow()
             ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)*/
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true)

        dialog.tvSaveLang.setOnClickListener()
        {
            dialog.dismiss()
            lifecycleScope.launch(Dispatchers.Main) {
                sessionManager.setData(SessionManager.USER_LANGUAGE, "1")
            }
        }


        initViewRecyclerLanguage(dialog)

        arrayList = data
        adapter = LanguageAdapter(arrayList, context as Activity)
        recyclerView?.adapter = adapter

        adapter?.notifyDataSetChanged();
        dialog.show()
    }

    fun showInterestDialog(data: ArrayList<Data_Category>) {
        val dialog = Dialog(homeActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dailog_image_select)
        /*     dialog.getWindow()
                 ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)*/
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        initViewRecyclerCategory(dialog)

        arrayListCat = data
        adapterCat = CategoryAdapter(arrayListCat, activity as Activity)
        recyclerViewCat?.adapter = adapterCat

        dialog.tvSave.setOnClickListener()
        {
            dialog.dismiss()
            lifecycleScope.launch(Dispatchers.Main) {
                sessionManager.setData(SessionManager.USER_INTEREST, "1")
            }
        }

        dialog.setCancelable(true)
        dialog.show()
    }

}