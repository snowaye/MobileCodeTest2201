package com.codigo.mobilecodetest.codivie.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.codigo.mobilecodetest.codivie.databinding.FragmentDetailBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.newInstance

class DetailFragment : Fragment(), KodeinAware {

    override val kodein by kodein()


    private val factory: DetailsViewModelFactory by instance{ requireArguments().getString("movieId")}

    private val viewModel:DetailViewModel by instance()

//    private val viewModelFactory: DetailsViewModelFactory by kodein.newInstance { DetailsViewModelFactory(instance(), args.movieId) }
//
//    private lateinit var viewModel: DetailViewModel

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        context ?: return binding.root
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}