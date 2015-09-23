### SimpleRouter

SimpleRouter is a powerful yet simplistic URL router specifically designed for Android app development. It allows you to
predefine URL routes, attach code blocks to these URL routes or even Android activities. It has full support for URL parameters
and makes it super quick and easy to get the information you need, without a hassle.

You can check out the Javadocs here, http://ryanw-se.github.io/SimpleRouter/!

### Issue Reporting

* Verify that the issue hasn't already been reported, if it has add onto that issue rather than creating a new one.
* Do not add your own tags like [BUG], [IMPORTANT], tags will be added to your issue is it is valid.
* If your bug is complex, tell us how to reproduce it, either step by step or with a short video.
* Be short and to the point, explain exactly what is happening, what you expected and anything else you feel is important.
* If your bug is invalid, just close it. Do not rename your ticket, or edit it. Just close it.

### Contributing

* When creating commit messages, always use present tense. If you have commits that aren't present tense, they won't be accepted.
* Always use a proper IDE, never edit code in browser, get a proper development environment (IDE, Java, Maven).
* Test your code, its always good to run several tests before creating a pull request.
* If your pull request is a work in progress, then simply mention that in your pull request description.
* Do not add tags to your pull requests, such as WIP, DONE, a collaborator will add tags where they are needed.

### Dependency Usage

Adding SimpleRouter to your Android project is super quick and easy, it should take less than a minute. Use this snippet of code
as a template for adding this API to your project. Chances are, most of this code is already present and you only need to make a 
few small edits to get everything up and running!

```gradle
buildscript {
    repositories {
        mavenCentral()
        maven {
            name = "repo.ryan-w.me"
            url = "http://repo.ryan-w.me/nexus/content/repositories/snapshots/"
        }
    }
    dependencies {
        classpath 'me.ryanw.simplerouter:SimpleRouter:1.0-SNAPSHOT'
    }
}
```

### Getting started

One of the main benefits of using SimpleRouter, is its super quick and easy setup. In most usage cases you can use the statically
defined instance of SimpleRouter, which is accessible throughout your entire project. This is the recommended usage. Calling the
statically defined instance of SimpleRouter, is super easy and can be done with the following code block.

```java 
SimpleRouter.getSharedRouter();
```

All of the code snippets in this documentation will use this code block and its highly recommended when manipulating the router.

SimpleRouter also supports opening Android activities via URL, this requires you to specify context which will be used to open 
these activities, if you do not specify context, you will not be able to open Android activities and a NullPointerException will
be thrown. Here is how you can set the context to be used by the router.

```java 
SimpleRouter.setContext(context);
```

### Routing URLs

Routing URLs is also super quick and easy. Once you have a URL pattern, example; ```/user/ryanw-se/projects/15```, you can simply 
replace where you expect variables with a colon followed by the name you want to reference that variable by. This is what that
URL would look like, with variable placeholders; ```/user/:userId/projects/:projectId```. That means, if a URL was called and I 
wanted to get the ```ryanw-se``` part of my URL, I could simply look for the parameter ```userId```.

Another key part in the route process, is specifying what you want to do when the route is called. Currently, there are two
methods implemented for this. One of them is a RouteCallback, which allows you to specify a code block to be run when a route is
called, the other allows you to specify an Android activity to open when a route URL is called.

In the case of the Android activity, when opened the variable parameters will be passed through along with its value, in the activities
extras. Which can be accessed via ```getIntent().getExtras()```. From there you can get any of the values passed through in the URL. Like wise, 
if you are using the RouteCallback function, you can access all of the variable parameters and their values inside the Route object, 
within the RouteCallback function. In this case, you can reference those parameters via ```route.getParameters()```.

Here is what the code would look like, if you were to map a route to a RouteCallback function. 

```java
SimpleRouter.getSharedRouter().route("/users/:userId/projects/:projectId", new RouteCallback<Route>() {
   @Override
   public void call(Route result) {
      // Run code block and interact with the variables passed through the route.
   }
});
```

Here is what the code would look like, if you were to map a route to an Android activity.

```java
SimpleRouter.getSharedRouter().route("/users/:userId/projects/:projectId", MyActivity.class);
```

You can also specify routes where you call both an Android activity and a RouteCallback at the same time. But that is
pretty straight forward and won't be explained on this page.

### Calling URLs

So we've learned how to Route URLs, now lets figure out how to call them. Calling URLs is quick and easy and all of the hard
work is done by the API. Simply call your URL, the API will determine which URL you are calling (if it is indeed valid), get
the parameters for the URL, put everything together and initialize the RouteCallback or Android activity (or both).

This is how calling a URL would look like, in a nut shell.

```java
SimpleRouter.getSharedRouter().call("/users/ryanw-se/projects/10");
```

You can also call external URLs, like Youtube videos and open them in a new Android activity, here is what that would look like.

```java
SimpleRouter.getSharedRouter().callExt("https://www.youtube.com/watch?v=8To-6VIJZRE");
```





