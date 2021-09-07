package id.byandev.hanifahapp.presentation.list_coin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.byandev.hanifahapp.R
import id.byandev.hanifahapp.adapter.AdapterCoins
import id.byandev.hanifahapp.common.Resource
import id.byandev.hanifahapp.databinding.FragmentListCoinsBinding
import id.byandev.hanifahapp.domain.model.Coin
import java.util.ArrayList
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class FragmentListCoins : Fragment() {

    private val viewModel: CoinsViewModel by viewModels()
    private var _binding: FragmentListCoinsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterCoins: AdapterCoins
    private var listCoins: MutableList<Coin> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListCoinsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterCoins = AdapterCoins()
        binding.rvListCoins.apply {
            adapter = adapterCoins
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.ls.observe(viewLifecycleOwner, {
            it.data?.let { it1 -> adapterCoins.setData(it1) }
        })

        viewModel.state.observe(viewLifecycleOwner, {
            if (it.isLoading) {
                Log.e("TAG", "onViewCreated state loading: ${it.isLoading}")
                binding.pbLoad.visibility = View.VISIBLE
                Toast.makeText(context, "loading", Toast.LENGTH_LONG).show()
            }

            if (!it.coins.isNullOrEmpty()) {
                Log.e("TAG", "onViewCreated state coins: ${it.coins}")
                adapterCoins.setData(it.coins)
                binding.pbLoad.visibility = View.INVISIBLE
                Toast.makeText(context, "coins ${it.coins}", Toast.LENGTH_LONG).show()
            }

            if (it.error.isNotBlank()) {
                binding.pbLoad.visibility = View.INVISIBLE
                Log.e("TAG", "onViewCreated state error: ${it.error}")
                Toast.makeText(context, "error ${it.error}", Toast.LENGTH_LONG).show()
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}