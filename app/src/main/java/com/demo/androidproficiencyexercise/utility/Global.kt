package com.demo.androidproficiencyexercise.utility

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.Window
import androidx.appcompat.app.AlertDialog
import com.demo.androidproficiencyexercise.AppApplication
import com.demo.androidproficiencyexercise.R

/**
 * Created by Ashish Nair on 07/06/20.
 */
class Global {

    companion object {
        /**
         * Check for internet availability
         * @param context Context
         * @return Boolean
         */
        @Suppress("DEPRECATION")
        fun isInternetAvailable(): Boolean {
            var result = false
            val cm =
                AppApplication.instance.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cm?.run {
                    cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                        result = when {
                            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                            else -> false
                        }
                    }
                }
            } else {
                cm?.run {
                    cm.activeNetworkInfo?.run {
                        if (type == ConnectivityManager.TYPE_WIFI) {
                            result = true
                        } else if (type == ConnectivityManager.TYPE_MOBILE) {
                            result = true
                        }
                    }
                }
            }
            return result
        }

        /**
         *  No network dialog
         * @param _context Context
         * @param _onClick OnClickListener
         */
        fun noNetworkDialog(
            _context: Context,
            _onClick: DialogInterface.OnClickListener
        ) {
            val dialog = AlertDialog.Builder(
                _context,
                android.R.style.ThemeOverlay_Material_Dialog
            ).create()
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!.attributes.windowAnimations =
                R.style.DialogAnimation
            dialog.setMessage(_context.getString(R.string.no_network))
            dialog.setButton(
                Dialog.BUTTON_POSITIVE,
                _context.getString(R.string.ok), _onClick
            )
            dialog.setCancelable(false)
            dialog.show()
        }
    }
}