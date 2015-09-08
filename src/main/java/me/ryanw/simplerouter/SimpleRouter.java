package me.ryanw.simplerouter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import me.ryanw.simplerouter.exception.RouteNotFoundException;
import me.ryanw.simplerouter.object.Route;
import me.ryanw.simplerouter.util.RouteCallback;

import java.util.HashMap;
import java.util.Map;
/*
 * This file is part of SimpleRouter, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015 Ryan W
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
public class SimpleRouter {

    /**
     * sharedRouter is a global statically defined instance of SimpleRouter, standard usage.
     * predefinedRoutes are routes defined by {@link #route} methods, that don't contain parameters.
     * {@link Context} is required in order to launch new activities via {@link Intent}s.
     */
    private static final SimpleRouter sharedRouter = new SimpleRouter();
    private final Map<String, Route> predefinedRoutes = new HashMap<String, Route>();
    private Context context;

    /**
     * Routes a URL to a callback function code block, with code to be executed when the URL is called.
     * @param routeUrl The URL to route to the callback function code block.
     * @param routeCallback The code block to execute when the URL is called.
     */
    public void route(String routeUrl, RouteCallback<Route> routeCallback) {
        this.route(routeUrl, null, routeCallback);
    }

    /**
     * Routes a URL to a Android {@link Activity} to be opened when the URL is called.
     * @param routeUrl The URL to route to the Android {@link Activity}.
     * @param targetClass The Android {@link Activity} to open when the URL is called.
     */
    public void route(String routeUrl, Class<? extends Activity> targetClass) {
        this.route(routeUrl, targetClass, null);
    }

    /**
     * Routes a URL to a callback function code block and to a Android {@link Activity} to be opened when the URL is called.
     * @param routeUrl The URL to route to the callback function code block and Android {@link Activity}.
     * @param targetClass The Android {@link Activity} to open when the URL is called.
     * @param routeCallback THe code block to execute when the URL is called.
     */
    public void route(String routeUrl, Class<? extends Activity> targetClass, RouteCallback<Route> routeCallback) {
        String cleanedRouteUrl = cleanRouteUrl(routeUrl);
        Route route = new Route(cleanedRouteUrl);
        if (targetClass != null) route.setTargetClass(targetClass);
        if (routeCallback != null) route.setCallback(routeCallback);
        predefinedRoutes.put(cleanedRouteUrl, route);
    }

    /**
     * Calls a route URL without parameters, typically used for simple things like "/logout".
     * @param routeUrl The route URL to call.
     */
    public void call(String routeUrl) {
        this.call(routeUrl, null);
    }

    /**
     * Calls a route URL with parameters, typically used for more complex things like "/user/ryanw-se/project/1".
     * @param routeUrl The route URL to call.
     * @param parameters The parameters to be passed on to the new {@link Activity}.
     */
    public void call(String routeUrl, Bundle parameters) {
        String cleanedRouteUrl = cleanRouteUrl(routeUrl);
        if (!predefinedRoutes.containsKey(cleanedRouteUrl)) throw new RouteNotFoundException("No route found at the following address, " + cleanedRouteUrl);
        // Take the routeUrl, replace the :params with each of the parameters in the bundle. Verify that the amount of expected params meets the provided param amount.
    }

    /**
     * Calls an external URL without parameters, typically used for simple things like "https://www.youtube.com/watch?v=8To-6VIJZRE".
     * @param url The external URL to launch in a new activity.
     */
    public void callExt(String url) {
        this.callExt(url, null);
    }

    /**
     * Calls an external URL with parameters, typically used for passing additional information, along with a simple external URL.
     * @param url The external URL to launch in a new {@link Activity}.
     * @param parameters The extra parameters to pass along in the new {@link Activity}.
     */
    public void callExt(String url, Bundle parameters) {
        if (context == null) throw new NullPointerException("In order to start activities, you need to provide the router with context.");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (parameters != null) intent.putExtras(parameters);
        context.startActivity(intent);
    }

    /**
     * Gets a list of all predefined routes, using the {@link #route} methods.
     * @return List of predefined routes without parameters.
     */
    public Map<String, Route> getPredefinedRoutes() {
        return predefinedRoutes;
    }

    /**
     * Gets a global statically defined router, accessible from everywhere within the project.
     * @return Global statically defined instance of this router.
     */
    public static SimpleRouter getSharedRouter() {
        return sharedRouter;
    }

    /**
     * Sets the {@link Context} to be used when using {@link Intent}s to launch new {@link Activity}s.
     * @param context The context to be used when launching new {@link Activity}s via {@link Intent}s.
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Gets the {@link Context} to be used when using {@link Intent}s to launch new {@link Activity}s.
     * @return The context to be used when launching new {@link Activity}s via {@link Intent}s.
     */
    public Context getContext() {
        return context;
    }

    /**
     * Removes the starting forward slash from the URL.
     */
    private String cleanRouteUrl(String routeUrl) {
        if (routeUrl.startsWith("/")) return routeUrl.substring(1, routeUrl.length());
        return routeUrl;
    }
}
