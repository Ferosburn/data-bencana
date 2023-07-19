package com.ferosburn.databencana

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ferosburn.databencana.databinding.FragmentRecentDisasterBinding
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class RecentDisasterFragment : Fragment() {
    private var _binding: FragmentRecentDisasterBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: MapView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecentDisasterBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // mapView binding
        map = binding.mapViewRecent
        // set map style/type/source
        map.apply {
            setTileSource(TileSourceFactory.OpenTopo)
            setMultiTouchControls(true)
            @Suppress("DEPRECATION")
            setBuiltInZoomControls(false)
            val mapController = controller
            // set initial zoom,
            // bigger number means zooms in, max ideal 15.0
            mapController.setZoom(17.0)
            // set initial position
            val startPoint = GeoPoint(-7.792563, 110.365813)
            mapController.setCenter(startPoint)
        }

        // create pin
        val marker = Marker(map)
        marker.position = GeoPoint(-7.792563, 110.365813)
        marker.icon = context?.getDrawable(R.drawable.ic_disaster)
        marker.title = "Test Marker"
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)

        // add pin on map
        map.apply {
            overlays.add(marker)
            invalidate()
        }
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDetach() {
        super.onDetach()
        map.onDetach()
    }
}