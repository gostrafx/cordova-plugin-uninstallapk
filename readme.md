Cordova plugin Uninstall APK 
======================

Platforms
---------

* Android

Installation
------------

Install with `cordova plugin` or `plugman`. The javascript module will be injected automatically.
```
cordova plugin add cordova-plugin-uninstallapk
plugman install --platform android --project platforms/android --plugin ..\cordova-plugin-uninstallapk
```
Example
-------

```js
UninstallApk.Uninstall('com.example.myapp').then(function (res) {
    console.log(res);
}).catch(function(error){
   console.log(error);
});

UninstallApk.AppIsInstalled('com.example.myapp').then(function (res) {
     console.log(res);
}).catch(function(error){
    console.log(error);
});
           
```

## Screenshots

| Uninstall                           | AppIsInstalled                           |
| ---------------------------------------- | ---------------------------------------------- |
| ![ScreenShot][Uninstall] | ![ScreenShot][AppIsInstalled] |

[Uninstall]: screenshots/Screenshot_1.jpg
[AppIsInstalled]: screenshots/Screenshot_2.jpg

License 
-------

Apache 2.0
