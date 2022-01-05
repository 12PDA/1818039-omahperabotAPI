package com.example.omahperabotan.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.omahperabotan.R
import com.example.omahperabotan.adapter.AdapterProduk
import com.example.omahperabotan.model.Produk
import com.inyongtisto.tutorial.adapter.AdapterSlider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vpSlider : ViewPager
    lateinit var rv_produk : RecyclerView
    lateinit var rv_laris : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rv_produk = view.findViewById(R.id.rv_produk)
        rv_laris = view.findViewById(R.id.rv_laris)

        val arrSlider = ArrayList<Int>()
        //Isi gambar slider
        arrSlider.add(R.drawable.ic_baseline_chat_24)
        arrSlider.add(R.drawable.ic_notifications_black_24dp)
        arrSlider.add(R.drawable.ic_home_black_24dp)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        rv_produk.adapter= AdapterProduk(arrProduk)
        rv_produk.layoutManager = layoutManager

        rv_laris.adapter= AdapterProduk(arrLaris)
        rv_laris.layoutManager = layoutManager2


        return view
    }

    val arrProduk : ArrayList<Produk> get(){
        val arr =   ArrayList<Produk>()
        val p1 = Produk()
        p1.nama = "Akebonno Grll Pan"
        p1.harga = "Rp 234.900"
        p1.gambar = R.drawable.ic_baseline_chat_24
        //produk 2
        val p2 = Produk()
        p2.nama = "Technoplast Rice Keeper 12 L"
        p2.harga = "Rp 179.900"
        p2.gambar = R.drawable.ic_home_black_24dp
        //produk3
        val p3 = Produk()
        p3.nama = "Technoplast Seasoning Storage"
        p3.harga = "Rp 199.900"
        p3.gambar = R.drawable.ic_notifications_black_24dp

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }

    val arrLaris : ArrayList<Produk> get(){
        val arr =   ArrayList<Produk>()
        val p1 = Produk()
        p1.nama = "Akebonno Grll Pan"
        p1.harga = "Rp 234.900"
        p1.gambar = R.drawable.ic_baseline_chat_24
        //produk 2
        val p2 = Produk()
        p2.nama = "Technoplast Rice Keeper 12 L"
        p2.harga = "Rp 179.900"
        p2.gambar = R.drawable.ic_home_black_24dp
        //produk3
        val p3 = Produk()
        p3.nama = "Technoplast Seasoning Storage"
        p3.harga = "Rp 199.900"
        p3.gambar = R.drawable.ic_notifications_black_24dp

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}