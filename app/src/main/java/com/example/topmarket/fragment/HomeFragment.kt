package com.example.topmarket.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.topmarket.DataClass.DataclassTaxfif
import com.example.topmarket.R
import com.example.topmarket.activity.PortaxfifActivity
import com.example.topmarket.activity.detailActivity
import com.example.topmarket.adapter.AdapterViewpager
import com.example.topmarket.adapter.Adapter_taxfif
import com.example.topmarket.etc.AlertDialogLoad
import com.example.topmarket.net.ApiService
import com.example.topmarket.presenter.presenterHomeFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.dsl.module.applicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class HomeFragment : Fragment(), Adapter_taxfif.layoutclick {

    val presenter: presenterHomeFragment by inject()
    lateinit var viewpager2: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewpager2 = view.findViewById<ViewPager2>(R.id.viewpager2)

        /*var recyclerview_catecory = view.findViewById<RecyclerView>(R.id.RecyclerViewCategory)*/
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val classdialog = AlertDialogLoad(requireActivity())
        classdialog.start()

        presenter.onCreate()

        viewpager2.adapter = AdapterViewpager()


        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)
        layoutManager.reverseLayout = true
        recyclerView_taxfifha.layoutManager = layoutManager

        var currentPage = viewpager2.currentItem

        //اسکرول ویو پیجر
        val handler = Handler()
        val update = Runnable {
            if (currentPage === 4) {
                currentPage = 0
            }
            viewpager2.setCurrentItem(currentPage++, true)
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 100, 6000)


        //کال بک رتروفیت پرتخفیف ها
        ApiService().getRetrofit().getTaxfifha(15, 0)
            .enqueue(object : Callback<DataclassTaxfif> {
                override fun onResponse(
                    call: Call<DataclassTaxfif>,
                    response: Response<DataclassTaxfif>
                ) {
                    val data = response.body()
                    if (data != null) {

                        recyclerView_taxfifha.adapter = Adapter_taxfif(data, this@HomeFragment)
                        classdialog.stop()

                    }

                }

                override fun onFailure(call: Call<DataclassTaxfif>, t: Throwable) {
                    classdialog.stop()

                    Toast.makeText(
                        activity,
                        "لطفا از اتصال خود به اینترنت مطمئن شوید",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

        //کلیک روی بیشتر
        textView86.setOnClickListener {

            startActivity(Intent(activity, PortaxfifActivity::class.java))
        }
        /*linearLayout4.setOnClickListener {

            startActivity(Intent(activity, PortaxfifActivity::class.java))
        }

        scroller.layoutDirection = View.LAYOUT_DIRECTION_RTL*/

    }

    override fun onclick(
        name: String,
        image: String,
        price: Int,
        discount: Int,
        decrib: String,
        Uid: String,
        percent: Int
    ) {
        val intent = Intent(activity, detailActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("image", image)
        intent.putExtra("price", price)
        intent.putExtra("priceDis", discount)
        intent.putExtra("dec", decrib)
        intent.putExtra("Uid", Uid)
        intent.putExtra("percent", percent)
        startActivity(intent)

    }


}