package tj.tajsoft.loyalrsn.presintation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.presintation.home.adapter.HomeAksiyaAdapter
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentHomeBinding
import tj.tajsoft.loyalrsn.presintation.home.adapter.HomeCardAdapter
import tj.tajsoft.loyalrsn.presintation.home.adapter.HomeFuelAdapter

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private var counter_gaz = 0
     private var counter_dt = 0
     private var counter_petrol = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
        UI()
    }

    @SuppressLint("StringFormatMatches")
    private fun subscribeToLiveData() = with(binding) {

        swipeRefreshLayout.setOnRefreshListener {
            counter_gaz = 0
            counter_dt = 0
            counter_petrol = 0
            viewModel.refreshingAllLiveData{
                swipeRefreshLayout.isRefreshing = false
                Log.d("LiveData", "subscribeToLiveData:swipeRefreshLayout : False ")
            }
        }


        viewModel.user.observe(viewLifecycleOwner) { user ->
            var count = 0
            user.forEach { userEntity ->
               val pushBadge =  userEntity.pushBadge
                pushBadge?.let {
                    count +=it.toInt()
                }
                imageNotification.badgeValue = count
                Log.d("TAG", "subscribeToLiveData: $count")
            }

            binding.viewPagerCard.adapter = HomeCardAdapter(user, ::onClickQR)
            binding.indecator.setupWithViewPager(binding.viewPagerCard)
            binding.viewPagerCard.offscreenPageLimit = 1
            binding.indecator.apply {
            setPageSize(user.size)
                notifyDataChanged()
            }
        }



        viewModel.fuel.observe(viewLifecycleOwner) {
            binding.ScrollView.isVisible = it != null
            it ?: return@observe
            recyclerFuel.adapter = HomeFuelAdapter(it)

        }


        viewModel.transaction.observe(viewLifecycleOwner) {
            binding.ScrollView.isVisible = it != null
            it ?: return@observe
            it.forEach { response ->
                with(binding) {
                    dataTransaction.text = response.createAdd
                    cityTransaction.text = response.address
                    summaTransaction.text = requireContext().getString(
                        R.string.fragment_home_summa_transaction,
                        response.summa
                    )
                    keshbek.text = requireContext().getString(
                        R.string.fragment_home_keshbek,
                        response.cashback
                    )
                    vidToplivoTransaction.text = requireContext().getString(
                        R.string.fragment_home_vid_toplivo,
                        response.name
                    )
                    binding.countLitrTransaction.text = requireContext().getString(
                        R.string.fragment_home_count_litr,
                        response.count
                    )


                }
            }
        }

        viewModel.sale.observe(viewLifecycleOwner) { sale ->
            binding.recyclerviewDiscount.adapter =
                HomeAksiyaAdapter(sale, ::onClickSale)
        }

        viewModel.transactionV2Local.observe(viewLifecycleOwner){transaction->
            transaction.status.forEach {
                if (it.name == "AИ"){
                    binding.countLitr.text = it.count.toString()
                    binding.textAfzun.text = requireContext().getString(R.string.home_fragment_title_do_afzun,it.currentMax)
                    binding.progressAI.max = it.currentMax.toInt()
                    binding.progressAI.progress = it.count.toInt()
                }

                if (it.name =="DT"){
                    binding.countLitrDT.text = it.count.toString()
                    binding.textAfzunDT.text = requireContext().getString(R.string.home_fragment_title_do_afzun,it.currentMax)
                    binding.progressDT.max = it.currentMax .toInt()
                    binding.progressDT.progress = it.count .toInt()
                }

                if (it.name =="GAZ"){
                    binding.countLitrGaz.text = it.count.toString()
                    binding.textAfzunGaz.text = requireContext().getString(R.string.home_fragment_title_do_afzun,it.currentMax)
                    binding.progressGAZ.max = it.currentMax.toInt()
                    binding.progressGAZ.progress = it.count.toInt()
                }

            }
        }


        viewModel.error.observe(viewLifecycleOwner) {
            binding.error.root.isVisible = it
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.ScrollView.isVisible = !it
            binding.loading.root.isVisible = it
        }

    }

    private fun onClickSale(id: Int) {
        findNavController().navigate(HomeFragmentDirections.toDetailDiscountFragment(id))
    }
    private fun onClickQR(barcode:String){
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQRCodeFragment(barcode))
    }


    private fun UI() {

        binding.imageNotification.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toNotification())
        }

        binding.showAll.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toTransactionFragment())
        }

        binding.indecator.apply {
            val normalColor = ContextCompat.getColor(requireContext(), R.color.indicator_uncheked)
            val checkedColor = ContextCompat.getColor(requireContext(), R.color.indicator_cheked)
            setSliderColor(normalColor, checkedColor)
            setSliderWidth(resources.getDimension(R.dimen.dp_10))
            setSliderHeight(resources.getDimension(R.dimen.dp_2))
            setSlideMode(IndicatorSlideMode.WORM)
            setIndicatorStyle(IndicatorStyle.CIRCLE)
            notifyDataChanged()
        }

    }


    override fun onStop() {
        super.onStop()
       binding.progressGAZ.progress = 0
       binding.progressAI.progress = 0
       binding.progressDT.progress = 0
    }

}