package tj.tajsoft.loyalrsn.presintation.gasstation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.PixelCopy.Request
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.common.Constant
import tj.tajsoft.loyalrsn.databinding.FragmentGasstationDetailBinding

@AndroidEntryPoint
class DetailGasstationFragment:Fragment() {
    private lateinit var binding:FragmentGasstationDetailBinding
    private val viewmodel by viewModels<GasstationDetailViewModel>()
    private var title: String? = null
     private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation: Location? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGasstationDetailBinding.inflate(inflater)
        return binding.root
    }


    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            title = it.getString("title")
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            currentLocation = location

        }



        viewmodel.loading.observe(viewLifecycleOwner){
            binding.scroll.isVisible = !it
            binding.loading.root.isVisible = it
        }

        viewmodel.branches.observe(viewLifecycleOwner){
            if (it == null){
                binding.scroll.isVisible = false
                return@observe
            }

            it.data.forEach {
                if (title == it.title){
                    binding.textViewName.text = it.title
                    binding.textViewDesc.text = it.description
                    val putImage = "${Constant.IMAGE_URL}${it.img}"
                    Glide.with(binding.root).load(putImage).listener(object:RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressCard.isVisible = false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.progressCard.isVisible = false
                            return false
                        }

                    }).into(binding.imageview)
                    binding.recyclerviewServices.adapter = DetailGasstationServicesAdapter(it.services)

                    val distance = currentLocation!!.distanceTo(Location(LocationManager.GPS_PROVIDER).apply {
                        latitude = it.latitude.toDouble()
                        longitude = it.longitude.toDouble()
                    })
                    val distanceToKilloMetr = distance / 1000
                    val distanceText = String.format("%.2f", distanceToKilloMetr)

                    binding.textViewDistance.text = requireContext().getString(R.string.fragment_gasstation_detail_textview_distance,distanceText)

                  binding.button.setOnClickListener {view->
                      currentLocation?.let {location->
                          drawPolyline(location,it.latitude,it.longitude )
                      }
                  }

                }

            }

            viewmodel.fuel.observe(viewLifecycleOwner){
                binding.recyclerviewFuel.adapter =DetailGasstationAdapter(it)
            }
        }
    }
    private fun drawPolyline(startLocation: Location,serverLatitude:String,serverLongitude:String) {
        val gmmIntentUri =
            Uri.parse("https://www.google.com/maps/dir/?api=1&origin=${startLocation.latitude},${startLocation.longitude}&destination=${serverLatitude},${serverLongitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

}