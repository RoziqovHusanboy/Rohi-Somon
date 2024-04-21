package tj.tajsoft.loyalrsn.presintation.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.databinding.FragmentQrcodeBinding

@AndroidEntryPoint
class QRCodeFragment:Fragment() {

    private lateinit var binding: FragmentQrcodeBinding
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
        binding.button.setOnClickListener {
            generateQRCode()
        }
        binding.iconBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun generateQRCode() {
        val text = binding.editTextNameQrCode.text.toString().trim()
        val write = MultiFormatWriter()
        val matrix =write.encode(text,BarcodeFormat.CODE_128,800,200)
        val encoder = BarcodeEncoder()
        val bitmap = encoder.createBitmap(matrix)
        binding.imageViewQRCode.setImageBitmap(bitmap)
    }

}