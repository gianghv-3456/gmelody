package com.sun.gmelody.screen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.sun.gmelody.R
import com.sun.gmelody.screen.splash.SplashFragment
import com.sun.gmelody.utils.base.BaseActivity
import com.sun.gmelody.utils.permission.PermissionAsker

class MainActivity : BaseActivity() {
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        supportFragmentManager.beginTransaction().replace(R.id.layoutContainer, SplashFragment.newInstance())
            .disallowAddToBackStack().commit()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun requestPermission() {
        if (!PermissionAsker.checkExternalStoragePermission(this) || !PermissionAsker.checkAudioStorePermission(this)) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_MEDIA_AUDIO),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE,
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1101
    }
}
