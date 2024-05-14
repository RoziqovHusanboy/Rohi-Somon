package tj.tajsoft.loyalrsn.presintation.discount

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.common.Constant
import tj.tajsoft.loyalrsn.databinding.FragmentDetailDiscountBinding

@AndroidEntryPoint
class DetailDiscountFragment:Fragment() {
    private lateinit var binding: FragmentDetailDiscountBinding
    private val viewmodel by viewModels<DiscountViewModel>()
    private var id: Int? = null
    private var idSale: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailDiscountBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            id = it.getInt("id")
        }
        arguments?.let {
            idSale = it.getInt("saleInt")
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.desc.movementMethod = ScrollingMovementMethod()

        viewmodel.loading.observe(viewLifecycleOwner){
                binding.loading.root.isVisible = it
            binding.layout.isVisible = !it
         }
        viewmodel.sale.observe(viewLifecycleOwner){
            it.data.forEach {
                if (it.id ==id){
                    val constImage = Constant.IMAGE_URL
                    val image = "$constImage${it.img}"
                    Glide.with(binding.root).load(image).listener(object :RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.isVisible = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.isVisible = false
                            return false
                        }

                    }).into(binding.image)
                    binding.desc.text = it.description
                }
            }
        }

        viewmodel.sale.observe(viewLifecycleOwner){
            it.data.forEach {
                if (it.id ==idSale){
                    val constImage = Constant.IMAGE_URL
                    val image = "$constImage${it.img}"
                    Glide.with(binding.root).load(image).listener(object :RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.isVisible = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressBar.isVisible = false
                            return false
                        }

                    }).into(binding.image)
                    binding.desc.text = it.description
                }
            }
        }

    }
}