package tj.tajsoft.loyalrsn.presintation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.presintation.home.adapter.HomeAksiyaAdapter
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentHomeBinding
import tj.tajsoft.loyalrsn.presintation.home.adapter.HomeFuelAdapter

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private var counter_gaz = 0
    private var pcounter_gaz = 0
    private var counter_dt = 0
    private var pcounter_dt = 0
    private var counter_petrol = 0
    private var pcounter_petrol = 0

    var max_count_gaz: Int = 100
    var max_count_dt: Int = 100
    var max_count_pertol: Int = 100
    var checkdefault = false;

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
        viewModel.saveNumber()
        subscribeToLiveData()
        UI()
    }

    private fun subscribeToLiveData() = with(binding) {
        swipeRefreshLayout.setOnRefreshListener {
            counter_gaz =0
            counter_dt = 0
            counter_petrol=0
            viewModel.refreshingAllLiveData()
            swipeRefreshLayout.isRefreshing = false

        }

        viewModel.user.observe(viewLifecycleOwner) { user ->

            if(user.data.status =="active"){
                    viewModel.getUserWithCard()
                binding.layoutActiveCard.isVisible = true
                binding.layoutDismissCard.isVisible = false
                Log.d("TAG", "subscribeToLiveDataActiveAndDismiss:${user.data.status}")
            }
            name.text = user.data.name


//            addingProgressbar(user.data.card.cardType, user.data.card.name)

//            if (user.data.card.cardType.toLowerCase() != "баракат" && user.data.card.cardType.toLowerCase() != "афзун" && user.data.card.cardType.toLowerCase() != "заррин" && !user.data.card.cardType.toLowerCase().contains("афзун")) {
//                max_count_dt = 1000
//                pcounter_dt = (counter_dt * 100) / max_count_dt
//                max_count_gaz = 1000
//                pcounter_gaz = (counter_gaz * 100) / max_count_gaz
//                max_count_pertol = 1000
//                pcounter_petrol = (counter_petrol * 100) / max_count_pertol
//                checkdefault = card.card_type.toLowerCase() != "баракат" && card.card_type.toLowerCase() != "афзун" && card.card_type.toLowerCase() != "заррин" && !(card.name.toLowerCase().contains("афзун")) && !(card.name.toLowerCase().contains("заррин"))
//            }

        }

        viewModel.userActive.observe(viewLifecycleOwner){
            countTv.text =  it.data.card.balans.toString()
            idName.text = getString(R.string.home_fragment_id_name, it.data.card.id.toString())
            barakatTV.text = it.data.card.cardType
        }


        viewModel.fuel.observe(viewLifecycleOwner) {
            binding.ScrollView.isVisible = it != null
            it ?: return@observe
            recyclerFuel.adapter = HomeFuelAdapter(it)
            Log.d("TAG", "subscribeToLiveData: ${it.data.last().name}")
        }

        binding.countLitrGaz.text = requireContext().getString(R.string.fragment_home_progress_ai_text,"0")
        binding.countLitrDT.text = requireContext().getString(R.string.fragment_home_progress_ai_text,"0")
        binding.countLitr.text = requireContext().getString(R.string.fragment_home_progress_ai_text,"0")


        viewModel.transaction.observe(viewLifecycleOwner) {
            binding.ScrollView.isVisible = it != null
            it ?: return@observe
            it.forEach { response ->
                with(binding) {
                    dataTransaction.text = response.createAdd.date
                    cityTransaction.text = response.address
                    summaTransaction.text = requireContext().getString(
                        R.string.fragment_home_summa_transaction,
                        response.items.last().summa
                    )
                    keshbek.text = requireContext().getString(
                        R.string.fragment_home_keshbek,
                        response.cashback
                    )
                    vidToplivoTransaction.text = requireContext().getString(
                        R.string.fragment_home_vid_toplivo,
                        response.items.last().name
                    )
                    binding.countLitrTransaction.text = requireContext().getString(
                        R.string.fragment_home_count_litr,
                        response.items.last().count
                    )
                    when (response.items.last().name) {
                        "GAZ" -> {
                            counter_gaz += response.items.last().count.toDouble().toInt()
                           binding.progressGAZ.progress = counter_gaz
                           binding.progressGAZ.max = 100
                            binding.countLitrGaz.text = requireContext().getString(R.string.fragment_home_progress_ai_text,counter_gaz.toString())
                            Log.d("TAG", "progressgaz:$counter_gaz ")
                        }

                        "DT","DT E" -> {
                            val responseDT = response.items.last().count.toDouble().toInt()
                             counter_dt += responseDT
                            binding.progressDT.progress = counter_dt
                            binding.progressDT.max = 100
                            binding.countLitrDT.text = requireContext().getString(R.string.fragment_home_progress_ai_text,counter_dt.toString())
                            Log.d("TAG", "progressDT:$counter_dt")
                        }

                        "A 95", "A 92" -> {
                            counter_petrol += response.items.last().count.toDouble().toInt()
                            binding.progressAI.progress = counter_petrol
                            binding.progressAI.max = 100
                            Log.d("TAG", "progress9592:$counter_petrol ")
                            binding.countLitr.text = requireContext().getString(R.string.fragment_home_progress_ai_text,counter_petrol.toString())


                        }
                    }
//                    Toast.makeText(requireContext(), "$counter_gaz", Toast.LENGTH_SHORT).show()

                }
            }
        }


        viewModel.Saleloading.observe(viewLifecycleOwner) { loading ->
            viewModel.sale.observe(viewLifecycleOwner) { sale ->
                binding.recyclerviewDiscount.adapter =
                    HomeAksiyaAdapter(sale, loading, ::onClickSale)
            }
        }


        viewModel.error.observe(viewLifecycleOwner) {
            binding.error.root.isVisible = it
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loading.root.isVisible = it
        }

    }

    private fun onClickSale(id: Int) {
        findNavController().navigate(HomeFragmentDirections.toDetailDiscountFragment(id))
    }


    private fun UI() {
        binding.showAll.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toTransactionFragment())
        }
        binding.QRcode.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQRCodeFragment())
        }

    }

    private fun addingProgressbar(card: String, name: String) {

        if (counter_gaz < 100) {
            if (card == "Афзун") {
                max_count_gaz = 199
                pcounter_gaz = if (counter_gaz == 0) 0 else (counter_gaz * 100 / 199)
                    binding.progressGAZ.progress = pcounter_gaz
                Log.d("addingProgressbar", "addingProgressbar:$pcounter_gaz ")
                    binding.progressGAZ.max = max_count_gaz
            } else {
                max_count_gaz = 100
                pcounter_gaz = if (counter_gaz == 0) 0 else (counter_gaz * 100 / 100)
                binding.progressGAZ.progress = pcounter_gaz
                binding.progressGAZ.max = max_count_gaz

            }
        } else {
            max_count_gaz = 199
            pcounter_gaz = counter_gaz * 100 / 199
            binding.progressGAZ.progress = pcounter_gaz
            binding.progressGAZ.max = max_count_gaz
        }

        if (counter_petrol < 60) {
            if (card == "Афзун") {
                max_count_pertol = 119
                pcounter_petrol = if (counter_petrol == 0) 0 else (counter_petrol * 100 / 119)
                binding.progressAI.progress = pcounter_petrol
                binding.progressAI.max = max_count_pertol
            } else {
                max_count_pertol = 60
                pcounter_petrol = if (counter_petrol == 0) 0 else (counter_petrol * 100 / 60)
                binding.progressAI.progress = pcounter_petrol
                binding.progressAI.max = max_count_pertol
            }
        } else {
            max_count_pertol = 119
            pcounter_petrol = counter_petrol * 100 / 119
            binding.progressAI.progress = pcounter_petrol
            binding.progressAI.max = max_count_pertol
        }

        if (counter_dt < 60) {
            if (card == "Афзун") {
                max_count_dt = 119
                pcounter_dt = if (counter_dt == 0) 0 else (counter_dt * 100 / 119)
                binding.progressDT.progress = pcounter_dt
                binding.progressDT.max = max_count_dt
            } else {
                max_count_dt = 60
                pcounter_dt = if (counter_dt == 0) 0 else (counter_dt * 100 / 60)
                binding.progressDT.progress = pcounter_dt
                binding.progressDT.max = max_count_dt
            }
        } else {
            max_count_dt = 119
            pcounter_dt = counter_dt * 100 / 119
            binding.progressDT.progress = pcounter_dt
            binding.progressDT.max = max_count_dt
        }
        if (counter_dt == 0) {
            max_count_dt = if (card == "Афзун" || name.contains("Афзун")) 119 else 60
            pcounter_dt = 0
        }

        if (counter_gaz == 0) {
            max_count_gaz =
                if (card != "Афзун" || !(name.contains("Афзун"))) 100 else 199
            pcounter_gaz = 0
        }

        if (counter_petrol == 0) {
            max_count_pertol =
                if (card != "Афзун" || !(name.contains("Афзун"))) 60 else 119
            pcounter_petrol = 0
        }

        if (card.toLowerCase() != "баракат" && card.toLowerCase() != "афзун" &&
            card.toLowerCase() != "заррин" && !(name.toLowerCase().contains("афзун"))
        ) {
            max_count_dt = 1000
            pcounter_dt = (counter_dt * 100) / max_count_dt
            max_count_gaz = 1000
            pcounter_gaz = (counter_gaz * 100) / max_count_gaz
            max_count_pertol = 1000
            pcounter_petrol = (counter_petrol * 100) / max_count_pertol
            checkdefault =
                card.toLowerCase() != "баракат" && card.toLowerCase() != "афзун" &&
                        card.toLowerCase() != "заррин" && !(name.toLowerCase()
                    .contains("афзун")) &&
                        !(name.toLowerCase().contains("заррин"))
        }

    }

    override fun onStop() {
        super.onStop()
        counter_gaz =0
        counter_dt = 0
        counter_petrol=0
    }

}