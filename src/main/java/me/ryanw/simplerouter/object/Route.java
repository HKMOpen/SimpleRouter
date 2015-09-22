package me.ryanw.simplerouter.object;
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

import android.app.Activity;
import android.os.Bundle;
import me.ryanw.simplerouter.util.RouteCallback;

public class Route {

    private String routeName;
    private Bundle parameters;
    private RouteCallback<Route> callback;
    private Class<? extends Activity> targetClass;

    /**
     * Instantiates a new {@link Route} object and sets the route name, example; "/user/:userId/projects/:projectId".
     * @param routeName The name of the {@link Route} object being instantiated.
     */
    public Route(String routeName) {
        this.routeName = routeName;
    }

    /**
     * Gets the name of the {@link Route} object instance.
     * @return The name of the {@link Route} object instance.
     */
    public String getRouteName() {
        return routeName;
    }

    /**
     * Sets the parameters for this {@link Route} object, by breaking down the passed URL parameters and assigning them
     * to the predefined route parameters, example; "/user/ryanw-se/projects/example" & "/user/:userId/projects/:projectId
     * would assign the parameter userId the value ryanw-se and the projectId to the value example.
     * @param parameters The parameters to be set for this {@link Route} object.
     */
    public void setParameters(Bundle parameters) {
        this.parameters = parameters;
    }

    /**
     * Gets the parameter bundle for this {@link Route} object.
     * @return The parameter bundle for this {@link Route} object.
     */
    public Bundle getParameters() {
        return parameters;
    }

    /**
     * Optionally, You can have a callback attached to a predefined route, which allows you to put code
     * inside to be ran when the route URL is called. This is helpful in cases, like a logout URL where
     * when called, you'd probably want to run a code block to log the user out.
     * @param callback The callback function to trigger when the predefined route is called.
     */
    public void setCallback(RouteCallback<Route> callback) {
        this.callback = callback;
    }

    /**
     * Gets callback function that has been assigned to this {@link Route}.
     * @return The callback function that has been assigned to this {@link Route}.
     */
    public RouteCallback<Route> getCallback() {
        return callback;
    }

    /**
     * Optionally, you can specify an Android activity to open when a predefined route is called. For instance,
     * if you had a URL like "/users/:userId/projects/:projectId", you'd probably want to open the project activity.
     * Within this activity you can access the {@link Bundle} within the {@link Route} object and determine behaviour accordingly.
     * @param targetClass The Android {@link Activity} to open when the predefined route is called.
     */
    public void setTargetClass(Class<? extends Activity> targetClass) {
        this.targetClass = targetClass;
    }

    /**
     * Gets the Android activity that has been assigned to this {@link Route}.
     * @return The Android activity that has been assigned to this {@link Route}.
     */
    public Class<? extends Activity> getTargetClass() {
        return targetClass;
    }
}
