package tj.tajsoft.loyalrsn.presintation.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.databinding.FragmentNotificationBinding

@AndroidEntryPoint
class NotificationFragment:Fragment() {
    private lateinit var binding:FragmentNotificationBinding
    private val viewModel by viewModels<NotificationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = Database.notificationModel
        binding.recyclerview.adapter = NotificationAdapter(list)

        binding.iconBack.setOnClickListener {
            findNavController().popBackStack()
        }

        observViewModel()
    }

    private fun observViewModel() {
        viewModel.updateBudge()
    }
}