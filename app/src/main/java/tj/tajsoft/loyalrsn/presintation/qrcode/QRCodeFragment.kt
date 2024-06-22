package tj.tajsoft.loyalrsn.presintation.qrcode

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.databinding.FragmentQrcodeBinding

@AndroidEntryPoint
class QRCodeFragment:Fragment() {

    private lateinit var binding: FragmentQrcodeBinding
     private val viewModel by viewModels<QRCodeViewModel>()
    var barcode = String()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments.let {
            barcode = it?.getString("barcode")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQrcodeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val text = barcode
            val digitalText =text
            val write = MultiFormatWriter()
            val matrix =write.encode(text,BarcodeFormat.CODE_128,900,230)
            val encoder = BarcodeEncoder()
            val bitmap = encoder.createBitmap(matrix)
            binding.imageViewQRCode.setImageBitmap(bitmap)
            binding.textQR.text = digitalText

        binding.iconBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}