package com.multiplatform.webview.web

import platform.WebKit.WKFrameInfo
import platform.WebKit.WKMediaCaptureType
import platform.WebKit.WKPermissionDecision
import platform.WebKit.WKSecurityOrigin
import platform.WebKit.WKUIDelegateProtocol
import platform.WebKit.WKWebView
import platform.darwin.NSObject

class WKPermissionHandler(
    private val handler: PermissionHandler?,
    private val locationPermissionHandler: LocationPermissionHandler?,
) : NSObject(), WKUIDelegateProtocol {
    // in ios>=15
    override fun webView(
        webView: WKWebView,
        requestMediaCapturePermissionForOrigin: WKSecurityOrigin,
        initiatedByFrame: WKFrameInfo,
        type: WKMediaCaptureType,
        decisionHandler: (WKPermissionDecision) -> Unit,
    ) {
        if (handler != null) {
            handler.invoke(
                PermissionRequest(
                    permissions = type.toPermissions(),
                    deny = { decisionHandler(WKPermissionDecision.WKPermissionDecisionDeny) },
                    grant = { decisionHandler(WKPermissionDecision.WKPermissionDecisionGrant) },
                ),
            )
        } else {
            decisionHandler(WKPermissionDecision.WKPermissionDecisionPrompt)
        }
    }
}

private fun WKMediaCaptureType.toPermissions() =
    buildList {
        when (this@toPermissions) {
            WKMediaCaptureType.WKMediaCaptureTypeCamera -> add(PermissionRequest.Permission.VIDEO)
            WKMediaCaptureType.WKMediaCaptureTypeCameraAndMicrophone ->
                addAll(listOf(PermissionRequest.Permission.VIDEO, PermissionRequest.Permission.AUDIO))

            WKMediaCaptureType.WKMediaCaptureTypeMicrophone -> add(PermissionRequest.Permission.AUDIO)
            else -> error("Unknown capture type: $this")
        }
    }
