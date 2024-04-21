package tj.tajsoft.loyalrsn.presintation.profile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
  import tajsoft.demoproject.myapplication.sharedPreference.SharedPreferences
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentProfileBinding
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = SharedPreferences(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch {
            val storedBitmap = loadImageFromSharedPreferences()

            withContext(Dispatchers.Main) {

                if (storedBitmap != null) {
                    binding.imagePerson.setImageBitmap(storedBitmap)
                } else {
                    binding.cardImage.setOnClickListener {
                        openGallery()
                    }
                }

            }
        }

        binding.imageTitle.setOnClickListener {
            openGallery()
        }

        binding.iconBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.imageViewEditNumber.setOnClickListener {
            openDialogEdit()
        }


    }

    private fun openDialogEdit() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.item_dialog_edit)
        dialog.show()
    }

    private fun openGallery() {
        try {
            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)

        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "selected large image should be not over 5mb",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            val selectedImageUri: Uri = data?.data!!
            binding.imagePerson.setImageURI(selectedImageUri)
            val bitmap = BitmapFactory.decodeStream(
                requireContext().contentResolver.openInputStream(selectedImageUri!!)
            )
            saveImageToSharedPreferences(bitmap)
            Toast.makeText(
                context,
                "Image selected",
                Toast.LENGTH_SHORT
            ).show()

        } catch (e: Exception) {
            android.util.Log.d("TAG", "onActivityResult: $e")
        }
    }

    private fun saveImageToSharedPreferences(bitmap: Bitmap) {
        val imageString = encodeImage(bitmap)
        sharedPreferences.save("image", imageString.toUri().toString())
    }

    private fun encodeImage(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    private suspend fun loadImageFromSharedPreferences(): Bitmap? {
        val imageString = sharedPreferences.get("image")
        return if (imageString != null && imageString.isNotEmpty()) {
            decodeImage(imageString)
        } else {
            null
        }
    }

    private fun decodeImage(imageString: String): Bitmap {
        val byteArray = Base64.decode(imageString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}