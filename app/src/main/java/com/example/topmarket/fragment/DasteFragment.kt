package com.example.topmarket.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataClassCategory
import com.example.topmarket.R
import com.example.topmarket.activity.subActivity
import com.example.topmarket.adapter.recycler_AllCa
import com.example.topmarket.androidWrapper.App
import com.example.topmarket.etc.AlertDialogLoad
import com.example.topmarket.net.ApiService
import com.example.topmarket.presenter.PresenterDasteFragment
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class DasteFragment : Fragment(), recycler_AllCa.ListenerAllCategory {

    lateinit var RecyclerViewCategory: RecyclerView

    val presenter: PresenterDasteFragment by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_daste, container, false)

        RecyclerViewCategory = view.findViewById(R.id.RecyclerViewCategory)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val classdialog = AlertDialogLoad(activity!!)
        classdialog.start()


        presenter.onCreate()

        RecyclerViewCategory.layoutManager =
            GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)

        ApiService().getRetrofit().getAllCategory()
            .enqueue(object : Callback<DataClassCategory> {
                override fun onResponse(
                    call: Call<DataClassCategory>,
                    response: Response<DataClassCategory>
                ) {

                    val data = response.body()
                    if (data != null) {

                        RecyclerViewCategory.adapter =
                            recycler_AllCa(data, this@DasteFragment)
                        classdialog.stop()
                    } else {
                        Toast.makeText(
                            activity,
                            "لطفا از اتصال خود به اینترنت مطمئن شوید",
                            Toast.LENGTH_LONG
                        ).show()
                        classdialog.stop()
                    }
                }

                override fun onFailure(call: Call<DataClassCategory>, t: Throwable) {
                    classdialog.stop()
                    Toast.makeText(
                        activity,
                        "لطفا از اتصال خود به اینترنت مطمئن شوید",
                        Toast.LENGTH_LONG
                    ).show()

                }

            })
    }


    override fun onClick(pos: String) {
        val intentUid = Intent(context, subActivity::class.java)
        intentUid.putExtra("intenetUid", pos)
        startActivity(intentUid)
    }
}