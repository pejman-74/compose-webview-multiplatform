package com.multiplatform.webview.web

import com.multiplatform.webview.web.PermissionRequest.Permission

/**
 * Representation of a permission request.
 * @property permissions a list of [Permissions][Permission] requested
 * @property grant call whenever permissions are granted
 * @property deny call whenever permissions are denied
 */
class PermissionRequest(
    val permissions: List<Permission>,
    val grant: (List<Permission>) -> Unit,
    val deny: () -> Unit,
) {
    /**
     * Different permission types.
     */
    enum class Permission {
        /**
         * Request for microphone access.
         */
        AUDIO,

        /**
         * Request for MIDI access (only on Android).
         */
        MIDI,

        /**
         * Request for media access (only on Android).
         */
        MEDIA,

        /**
         * Request for camera access.
         */
        VIDEO,
    }
}

/**
 * A handler for [PermissionRequests][PermissionRequest].
 */
typealias PermissionHandler = (PermissionRequest) -> Unit

/**
 * Representation of a location permission request.
 * @property grant call whenever permission are granted. isPersist just need for android
 * @property deny call whenever permission are denied
 */
data class GeolocationPermissionRequest(
    val grant: (isPersist: Boolean) -> Unit,
    val deny: () -> Unit,
)

/**
 * A handler for Location permission request
 */
typealias LocationPermissionHandler = (GeolocationPermissionRequest) -> Unit
