package tj.tajsoft.loyalrsn.presintation.gasstation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tajsoft.demoproject.myapplication.presintation.main.BottomNavigationVisibilityListener
 import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.databinding.FragmentGasstationBinding

@AndroidEntryPoint
class GasstationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentGasstationBinding
    private lateinit var myMap: GoogleMap
    private val viewModel by viewModels<GasstationViewModel>()
    private var bottomNavigationVisibilityListener: BottomNavigationVisibilityListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGasstationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val mapFragment = childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)
        if (activity is BottomNavigationVisibilityListener) {
            bottomNavigationVisibilityListener = activity as BottomNavigationVisibilityListener
        }

        UI()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun UI() {
        binding.apply {
            searchBar.setOnClickListener {
                SearchLayout.isVisible =false
                SearchLayoutOnClick.isVisible =true
            }

            textViewCancel.setOnClickListener {
                SearchLayoutOnClick.isVisible = false
                SearchLayout.isVisible = true

            }


        }
    }

    @SuppressLint("PotentialBehaviorOverride")
    override fun onMapReady(map: GoogleMap) {
        myMap = map

        GlobalScope.launch{
            withContext(Dispatchers.IO){
                val data = viewModel.insertData()
                data.forEach {
                    val latLng = LatLng(it.lat.toDouble(), it.lan.toDouble())
                    //  val getAdrees=  viewModel.getAdress(latLng.latitude,latLng.longitude,requireContext())
                    withContext(Dispatchers.Main){
                        myMap.addMarker(MarkerOptions().position(latLng))
                        val moveCamera = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
                        myMap.moveCamera(moveCamera)
                    }

                }
            }
        }




        myMap.setOnMarkerClickListener {
            openDialog(it)
            true
        }
       // viewModel.addCluster(map, requireContext())
    }

    private fun openDialog(marker: Marker){
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.item_bottom_sheet)
        dialog.show()
    }

}