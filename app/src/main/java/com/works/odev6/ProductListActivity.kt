package com.works.odev6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.works.odev6.adapter.customProductsListAdapter
import com.works.odev6.configs.ApiClient
import com.works.odev6.models.DummyProducts
import com.works.odev6.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListActivity : AppCompatActivity() {
    lateinit var dummyService2 : DummyService
    lateinit var productsListView : ListView
    lateinit var editTxtFilter : EditText
    lateinit var btnSearch : Button

    companion object Product
    {
        var selectedProduct : com.works.odev6.models.Product? = null // Listedeki seçili elemanı statik olarak tutuyorum detay sayfasında çağırıcam.
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        productsListView = findViewById(R.id.productsListView)
        editTxtFilter = findViewById(R.id.editTxtFilter)
        btnSearch = findViewById(R.id.btnSearch)


        dummyService2 = ApiClient.getClient().create(DummyService::class.java)
        dummyService2.Products(10).enqueue(object : Callback<DummyProducts> {
            override fun onResponse(call: Call<DummyProducts>, response: Response<DummyProducts>) {
                val datas = response.body()
                val customAdapter = customProductsListAdapter(this@ProductListActivity,datas!!.products)
                productsListView.adapter = customAdapter
            }

            override fun onFailure(call: Call<DummyProducts>, t: Throwable) {
                Log.e("Error",t.toString())
            }

        })
        
        productsListView.setOnItemClickListener { adapterView, view, i, l ->
            val selectedItem = productsListView.getItemAtPosition(i) as com.works.odev6.models.Product
            selectedProduct = selectedItem
            var intent = Intent(this@ProductListActivity,ProductDetailActivity::class.java)
            startActivity(intent)


        }

        btnSearch.setOnClickListener{
            dummyService2.Filter(editTxtFilter.text.toString()).enqueue(object : Callback<DummyProducts>
            {
                override fun onResponse(call: Call<DummyProducts>, response: Response<DummyProducts>) {
                    val searchData = response.body()
                    val myAdapter = customProductsListAdapter(this@ProductListActivity,searchData!!.products)
                    productsListView.adapter =  myAdapter
                }

                override fun onFailure(call: Call<DummyProducts>, t: Throwable) {
                   Log.e("SearchError",t.toString())
                }

            })
        }

    }
}