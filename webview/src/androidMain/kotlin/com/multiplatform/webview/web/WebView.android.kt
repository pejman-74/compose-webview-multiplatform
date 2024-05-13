package com.multiplatform.webview.web

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.multiplatform.webview.jsbridge.WebViewJsBridge

/**
 * Android WebView implementation.
 */
@Composable
actual fun ActualWebView(
    state: WebViewState,
    modifier: Modifier,
    captureBackPresses: Boolean,
    navigator: WebViewNavigator,
    permissionHandler: PermissionHandler?,
    locationPermissionHandler: LocationPermissionHandler?,
    webViewJsBridge: WebViewJsBridge?,
    onCreated: () -> Unit,
    onDispose: () -> Unit,
) {
    AccompanistWebView(
        state = state,
        modifier = modifier,
        captureBackPresses = captureBackPresses,
        navigator = navigator,
        permissionHandler = permissionHandler,
        locationPermissionHandler = locationPermissionHandler,
        webViewJsBridge = webViewJsBridge,
        onCreated = { _ -> onCreated() },
        onDispose = { _ -> onDispose() },
    )
}
