package com.example.omahperabotan.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.omahperabotan.MainActivity
import com.example.omahperabotan.R
import com.example.omahperabotan.activity.LoginActivity
import com.example.omahperabotan.helper.SharedPref

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var s : SharedPref
    lateinit var btnLogout : TextView
    lateinit var tv_nama : TextView
    lateinit var tv_email : TextView
    lateinit var tv_phone : TextView

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
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_profile, container, false)
        init(view)

        s = SharedPref(requireActivity())
        btnLogout.setOnClickListener{
            s.setStatusLogin(false)
        }

        setData()
        return view
    }

    fun setData(){
        if (s.getUser() == null){
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            return
        }

        val user = s.getUser()!!

        tv_nama.text = user.name
        tv_phone.text = user.phone
        tv_email.text = user.email
    }

    private fun init(view: View) {
        //deklarasi
        btnLogout = view.findViewById(R.id.btn_logout)
        tv_nama = view.findViewById(R.id.tv_nama)
        tv_phone = view.findViewById(R.id.tv_phone)
        tv_email = view.findViewById(R.id.tv_email)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}