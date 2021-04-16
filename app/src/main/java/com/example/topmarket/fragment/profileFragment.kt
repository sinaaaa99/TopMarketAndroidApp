package com.example.topmarket.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.topmarket.DataClass.DataClassGetProfile
import com.example.topmarket.R
import com.example.topmarket.activity.AddAdressActivity
import com.example.topmarket.activity.ProfileActivity
import com.example.topmarket.activity.ShopedActivity
import com.example.topmarket.etc.AlertDialogLoad
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class profileFragment : Fragment() {

//    lateinit var sharedprof: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val classdialog = AlertDialogLoad(activity!!)
        classdialog.start()

        ApiService().getRetrofit().getProfile()
            .enqueue(object : Callback<DataClassGetProfile> {
                override fun onResponse(
                    call: Call<DataClassGetProfile>,
                    response: Response<DataClassGetProfile>
                ) {
                    val data = response.body()
                    if (data != null) {

                        textView_nameP.text = data.firstName
                        textView_lastP.text = data.lastName
                        textView_emailP.text = data.email
                        textView_invaitP.text = data.invitationCode
                        textView_tellP.text = data.mobileNumber
                        textView_cardP.text=data.walletValue.toString()

                        classdialog.stop()

                    }

                }

                override fun onFailure(call: Call<DataClassGetProfile>, t: Throwable) {
                    classdialog.stop()

                    Toast.makeText(
                        activity,
                        "لطفا از اتصال خود به اینترنت مطمئن شوید",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

        button_list_myshop.setOnClickListener {
            activity?.startActivity(Intent(activity, ShopedActivity::class.java))
        }
        button_update_user.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
//            sharedprof = activity!!.getSharedPreferences("Profile", Context.MODE_PRIVATE)
//            sharedprof.edit().putBoolean("openAgain", false).apply()
            activity?.startActivity(intent)
        }


    }
}