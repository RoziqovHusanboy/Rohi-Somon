package tj.tajsoft.loyalrsn.presintation.gasstation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import tj.tajsoft.loyalrsn.presintation.main.BottomNavigationVisibilityListener
import tj.tajsoft.loyalrsn.R
import tj.tajsoft.loyalrsn.data.remote.model.branches.Data
import tj.tajsoft.loyalrsn.data.remote.model.branches.ResponseBranches
import tj.tajsoft.loyalrsn.databinding.FragmentGasstationBinding


@AndroidEntryPoint
class GasstationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentGasstationBinding
    private lateinit var myMap: GoogleMap
    private val viewModel by viewModels<GasstationViewModel>()
    private var bottomNavigationVisibilityListener: BottomNavigationVisibilityListener? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation: Location? = null
    private var locationLongitute: Double? = null
    private var locationLattitute: Double? = null
    private var mLocationPermissionGranted: Boolean = false
    private lateinit var recyclerViewAdapter: GasstationAdapter


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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

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
                SearchLayout.isVisible = false
                SearchLayoutOnClick.isVisible = true
            }

            textViewCancel.setOnClickListener {
                SearchLayoutOnClick.isVisible = false
                SearchLayout.isVisible = true
            }
            viewModel.branches.observe(viewLifecycleOwner) {
                recyclerViewAdapter = GasstationAdapter(it, ::onClick)
                binding.recyclerview.adapter = recyclerViewAdapter
            }
            binding.searchBarOnClick.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    recyclerViewAdapter.getFilter(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {}
            })

        }
    }

    private fun onClick(data: Data) {
        findNavController().navigate(
            GasstationFragmentDirections.actionGasstationFragmentToDetailGasstationFragment(
                data.title
            )
        )
    }

    @SuppressLint("PotentialBehaviorOverride", "MissingPermission", "UseCompatLoadingForDrawables")
    override fun onMapReady(map: GoogleMap) {
        myMap = map

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            mLocationPermissionGranted = true
            setupMapWithLocation()

        } else {
            // Request location permission
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }


        viewModel.branches.observe(viewLifecycleOwner) { data ->
            data.data.forEach {
                val latLng = LatLng(it.latitude.toDouble(), it.longitude.toDouble())
                val marker = myMap.addMarker(MarkerOptions().position(latLng))

                //adding icon to marker
                val vectorDrawable: Drawable? =
                    ContextCompat.getDrawable(requireContext(), R.drawable.icon_marker)
                vectorDrawable?.let {
                    val bitmap = Bitmap.createBitmap(
                        it.intrinsicWidth,
                        it.intrinsicHeight,
                        Bitmap.Config.ARGB_8888
                    )
                    val canvas = Canvas(bitmap)
                    it.setBounds(0, 0, canvas.width, canvas.height)
                    it.draw(canvas)
                    BitmapDescriptorFactory.fromBitmap(bitmap)?.let { customMarker ->
                        marker?.setIcon(customMarker)

                    }
                }
                //// //// //

                marker.title = it.title
                marker.snippet = it.description
                val moveCamera = CameraUpdateFactory.newLatLngZoom(latLng, 10f)
                myMap.animateCamera(moveCamera)
                marker.tag = data
            }

            if (mLocationPermissionGranted == true) {
                setupMapWithLocation()
            }

            myMap.setOnMarkerClickListener { clickedMarker ->
                setupMapWithLocation()
                val clickedBranch = clickedMarker.tag as ResponseBranches
                clickedBranch.data.forEach {
                    if (clickedMarker.title == it.title) {
                        openDialog(it.title, it.description, clickedMarker)
                    }
                }
                true
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun setupMapWithLocation() {
        myMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                locationLongitute = location.longitude
                locationLattitute = location.latitude
                currentLocation = location
                myMap.isMyLocationEnabled = true
            }
        }
    }

    private fun openDialog(newTitle: String, newDesc: String, marker: Marker) {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.item_bottom_sheet)
        val title = dialog.findViewById<TextView>(R.id.textViewTitle)
        val desc = dialog.findViewById<TextView>(R.id.textViewAdress)
        val textViewDistance = dialog.findViewById<TextView>(R.id.textViewDistance)
        val button = dialog.findViewById<Button>(R.id.button)
        title?.text = newTitle
        desc?.text = newDesc
        //find marker position and find distance between marker and user location
        val markerPosition = marker.position
        val distance = currentLocation?.let {
            it.distanceTo(Location(LocationManager.GPS_PROVIDER).apply {
                latitude = markerPosition.latitude
                longitude = markerPosition.longitude
            })
        }
        val distanceToKilloMetr = distance?.let { it / 1000 }
        val distanceText = String.format("%.2f", distanceToKilloMetr)
        textViewDistance?.text =
            requireContext().getString(R.string.item_bottom_sheet_text_distance, distanceText)


        button?.setOnClickListener {
            currentLocation?.let {
                drawPolyline(it, markerPosition)
            }
        }
        dialog.show()
    }

    private fun drawPolyline(startLocation: Location, endLatLng: LatLng) {
//        val polylineOptions = PolylineOptions()
//            .add(LatLng(startLocation.latitude, startLocation.longitude), endLatLng)
//            .color(Color.RED)
//            .width(5f)
//        myMap.addPolyline(polylineOptions)


        val gmmIntentUri =
            Uri.parse("https://www.google.com/maps/dir/?api=1&origin=${startLocation.latitude},${startLocation.longitude}&destination=${endLatLng.latitude},${endLatLng.longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupMapWithLocation()
                mLocationPermissionGranted = true
            } else {
                mLocationPermissionGranted = false
                // Permission denied
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }


}