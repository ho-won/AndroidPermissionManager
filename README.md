Android Permission Manager
=====

I have summarized how to easily and simply request permissions in Android.


Download
--------

Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency
```
dependencies {
	implementation 'com.github.ho-won:AndroidPermissionManager:1.0.0'
}
```
  
  
How do I use it?
-------------------

```
    private lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

	permissionManager = PermissionManager(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), // required permissions
            arrayOf(Manifest.permission.POST_NOTIFICATIONS), // optional permissions
            onGranted = {
                // required permissions complete
            },
            onDenied = {
                // required permissions denied
            })
    }
    
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
```


License
--------

    Copyright 2023 ho-won.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
