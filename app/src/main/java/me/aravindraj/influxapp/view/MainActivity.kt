package me.aravindraj.influxapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import me.aravindraj.influxapp.R
import me.aravindraj.influxapp.data.api.ApiHelper
import me.aravindraj.influxapp.data.api.RetrofitBuilder
import me.aravindraj.influxapp.utils.Status
import me.aravindraj.influxapp.viewmodel.MainViewModel
import me.aravindraj.influxapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity(),
    MenuFragment.OnListFragmentInteractionListener {


    private lateinit var adapter: ViewPagerAdapter
    private lateinit var viewModel: MainViewModel
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         adapter = ViewPagerAdapter(supportFragmentManager)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(application, ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)

        viewModel.getFoodData().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        resource.data?.let { data ->
                            if (data.status.Description.equals("OK")) {
                                data.FoodList.forEach {
                                    adapter.addFragment(MenuFragment(), it.TabName)
                                }
                                adapter.notifyDataSetChanged()
                            }
                        }
                        Log.e("Status", "success")
                    }
                    Status.ERROR -> {

                        Log.e("Status", "error")
                    }
                    Status.LOADING -> {
                        Log.e("Status", "Loading")
                    }
                }
            }
        })


    }
}

class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}
