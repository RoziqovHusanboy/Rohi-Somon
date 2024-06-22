package tj.tajsoft.loyalrsn.presintation.more.multi_card_all_users

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentAllUsersTransactionsBinding

@AndroidEntryPoint
class AllUserFragment:Fragment(),ItemUsersOnclick {
    private lateinit var binding:FragmentAllUsersTransactionsBinding
    private val viewmodel by viewModels<AllUserViewModel>()
    private lateinit var allUserAdapter:AllUserAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllUsersTransactionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().navigate(AllUserFragmentDirections.actionAllUserFragmentToMoreFragment())
        }

         allUserAdapter = AllUserAdapter(this,requireContext())

        binding.recyclerview.adapter = allUserAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)

        viewmodel.parent.observe(viewLifecycleOwner){


                it.forEach {
                    Log.d("filter", "onViewCreated: ${it.parentUserTransaction?.toList()}")
                    allUserAdapter.updateData(it)
                }
        }


        binding.buttonReport.setOnClickListener {
            openDialog()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun openDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet_multi_card_filter)
        val back = dialog.findViewById<ImageView>(R.id.back)
        val dateOne  = dialog.findViewById<TextView>(R.id.dataOne)
        val dateTwo  = dialog.findViewById<TextView>(R.id.dateTwo)
        val buttonApply  = dialog.findViewById<Button>(R.id.buttonApply)
        var firstDate = false
        var secondDate = false



        dateOne?.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { view, year, _month, dayOfMonth ->
                        val month = if ((_month+1).toString().length==1) "0${_month+1}" else _month+1
                        val day = if (dayOfMonth.toString().length == 1) "0$dayOfMonth" else dayOfMonth
                        val dat = ("$year-$month-$day")
                       dateOne.text = dat
                        dateOne.setTextColor(R.color.black)
                        firstDate = true
                        updateButtonState(firstDate,secondDate,buttonApply)
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.show()

        }
        dateTwo?.let {
                it.setOnClickListener {

                    val calendar = Calendar.getInstance()
                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH)
                    val day = calendar.get(Calendar.DAY_OF_MONTH)

                    val datePickerDialog = DatePickerDialog(
                        requireContext(),
                        { view, year, _month, dayOfMonth ->
                            val month = if ((_month+1).toString().length==1) "0${_month+1}" else _month+1
                            val day = if (dayOfMonth.toString().length == 1) "0$dayOfMonth" else dayOfMonth
                            val dat = ("$year-$month-$day")
                            dateTwo.text = dat
                            dateTwo.setTextColor(R.color.black)
                            secondDate = true
                            updateButtonState(firstDate,secondDate,buttonApply)
                        },
                        year,
                        month,
                        day
                    )
                    datePickerDialog.show()
            }
        }

        back?.setOnClickListener {
            dialog.dismiss()
        }

        buttonApply?.setOnClickListener {
            viewmodel.getTransactionCorparate(dateOne?.text.toString(),dateTwo?.text.toString())
        }

        Log.d("filter", "openDialog: ${R.color.black}")

        dialog.show()

    }

    private fun updateButtonState(firstDate: Boolean, secondDate: Boolean, buttonApply: Button?) {

        if (firstDate && secondDate) {
            buttonApply?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filter_button_checked))
            buttonApply?.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
          }
        }

    override fun onClick(id: Int) {
        Log.d("onClick", "onClick: $id")
        viewmodel.getParentUsersTransaction(id)
    }
}
