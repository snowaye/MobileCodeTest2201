package com.codigo.mobilecodetest.codivie.ui.sports

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codigo.mobilecodetest.codivie.R

class SportsFragment : Fragment() {

    companion object {
        fun newInstance() = SportsFragment()
    }

    private lateinit var viewModel: SportsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sports, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SportsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}