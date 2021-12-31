package com.example.topmarket.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmarket.R
import com.example.topmarket.activity.detailActivity
import com.example.topmarket.adapter.Adapter_search
import com.example.topmarket.databinding.FragmentSearchBinding
import com.example.topmarket.etc.AlertDialogLoad
import com.example.topmarket.mvvm.viewmodel.MainViewModel
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.dsl.module.applicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class searchFragment : Fragment(), Adapter_search.searchsend {

    lateinit var mainViewModel: MainViewModel
    lateinit var adapterSearch: Adapter_search
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        adapterSearch = Adapter_search(this)

//        val classdialog = AlertDialogLoad(requireActivity())

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerviewSearch.adapter = adapterSearch
        binding.recyclerviewSearch.layoutManager = LinearLayoutManager(activity)


        var job: Job? = null
        binding.editTextSearch.addTextChangedListener {
            job?.cancel()
            job = MainScope().launch {
                delay(500L)

                it.let {
                    if (it.toString().isNotEmpty()) {
                        mainViewModel.searchProducts(it.toString())
                            .observe(viewLifecycleOwner, { data ->
                                if (data != null) {
                                    adapterSearch.dataclass.submitList(data.data)

                                }

                            })
                    }
                }
            }
        }


        /*imageView_search.setOnClickListener {


            if (editText_search.text.isEmpty()) {
                editText_search.error = "لطفا ابتدا اسم محصول را وارد کنید"
                editText_search.requestFocus()
                return@setOnClickListener
            }
            classdialog.start()

            ApiService().getRetrofit().searchProducts(0, editText_search.text.toString(), 100)
                .enqueue(object : Callback<DataClassSearch> {
                    override fun onResponse(
                        call: Call<DataClassSearch>,
                        response: Response<DataClassSearch>
                    ) {
                        val data = response.body()
                        if (data != null) {

                            recyclerviewSearch.adapter = Adapter_search(data, this@searchFragment)
                            classdialog.stop()
                        } else {
                            classdialog.stop()

                            Toast.makeText(
                                activity,
                                "متاسفانه چیزی یافت نشد",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    }

                    override fun onFailure(call: Call<DataClassSearch>, t: Throwable) {
                        classdialog.stop()
                        Toast.makeText(
                            activity,
                            "لطفا از اتصال خود به اینترنت مطمئن شوید",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }*/


        // Inflate the layout for this fragment
        return binding.root
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