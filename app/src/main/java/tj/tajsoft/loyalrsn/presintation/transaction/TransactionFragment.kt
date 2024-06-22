package tj.tajsoft.loyalrsn.presintation.transaction

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentTransactionBinding

@AndroidEntryPoint
class TransactionFragment:Fragment() {

    private lateinit var binding: FragmentTransactionBinding
    private val viewModel by viewModels<TransactionViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageViewBack.setOnClickListener{
            findNavController().popBackStack()
        }

        UI()
        openCalendar()

    }

    private fun openCalendar() {
        binding.imageViewCalendar.setOnClickListener {
            var date = String()

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, year, month, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "." + (month + 1) + "." + (year))
                    date =  dat
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    private fun UI() {
        viewModel.transaction.observe(viewLifecycleOwner){
            Log.d("getUser", "UI:$it ")

            binding.recyclerViewTransaction.adapter = TransactionAdapter(it)
            binding.recyclerviewReport.adapter = TransactionReportAdapter(it)
        }
        viewModel.loading.observe(viewLifecycleOwner){
            binding.loading.root.isVisible = it
         }
        viewModel.error.observe(viewLifecycleOwner){
            binding.error.root.isVisible = it

        }

    }

}