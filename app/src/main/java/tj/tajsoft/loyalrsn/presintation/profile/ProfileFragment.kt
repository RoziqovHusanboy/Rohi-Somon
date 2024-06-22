package tj.tajsoft.loyalrsn.presintation.profile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
    private val viewModels by viewModels<ProfileViewModel>()

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

        viewModels.user.observe(viewLifecycleOwner) {
                binding.numberPhone.text = it.data.first().phoneNumber
                binding.nameFio.text = it.data.first().name
                binding.nameLocation.text = it.data.first().city
                binding.nameCar.text = it.data.first().carNumber
                val birth = it.data.first().birthday.toString()
                val birthDataOnly = birth.toString().substring(14, 24)
                binding.nameBirth.text = birthDataOnly

        }

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
        val carnumber = dialog.findViewById<EditText>(R.id.editTextCar)
        val button = dialog.findViewById<Button>(R.id.buttonCarNumber)

        carnumber?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 7 || s?.length ==8) {
                        button?.setOnClickListener {
                            viewModels.updateCarNumber(s.toString())
                            viewModels.responseCarNumber.observe(viewLifecycleOwner){ Toast.makeText(requireContext(), "${it.data}", Toast.LENGTH_SHORT).show() }
                            dialog.dismiss()
                            viewModels.getUser()
                        }
                    }
                }

            })
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
             Log.d("TAG", "onActivityResult: $e")
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