package quack.whattowear.feature.main_screen.impl.data

import dev.jordond.compass.Priority
import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.hasPermission
import dev.jordond.compass.permissions.LocationPermissionController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import quack.whattowear.feature.geolocation.domain.model.GeoPoint
import quack.whattowear.feature.main_screen.impl.domain.interactor.GeolocationInteractor

class GeolocationInteractorImpl(
  private val geolocator: Geolocator,
  private val locationPermissionController: LocationPermissionController,
  private val scope: CoroutineScope,
) : GeolocationInteractor {
  private val _isPermissionGrantedFlow: MutableStateFlow<Boolean> = MutableStateFlow(geolocator.hasPermission())
  override val isPermissionGrantedFlow: StateFlow<Boolean> = _isPermissionGrantedFlow

  private var trackingJob: Job? = null

  override val locationFlow: StateFlow<GeoPoint?> = geolocator.locationUpdates.map {
    GeoPoint(
      latitude = it.coordinates.latitude,
      longitude = it.coordinates.longitude
    )
  }.stateIn(
    scope = scope,
    started = SharingStarted.Eagerly,
    initialValue = null
  )

  override fun getPermission() {
    scope.launch {
      if (!_isPermissionGrantedFlow.value) {
        locationPermissionController.requirePermissionFor(Priority.Balanced)
        updatePermissionStatus()
      }

      if (_isPermissionGrantedFlow.value) {
        track()
      }
    }
  }

  override fun track() {
    trackingJob?.cancel()
    trackingJob = scope.launch { geolocator.track() }
  }

  private fun updatePermissionStatus() {
    _isPermissionGrantedFlow.value = geolocator.hasPermission()
  }
}