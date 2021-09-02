package com.example.lightning_weather.util

    import android.app.Activity
    import android.content.Context
    import android.content.SharedPreferences

    class SharedPref private constructor() {

        fun setToken(token: String) {
            editor?.putString(TOKEN, token)
            editor?.apply()
        }

        fun getToken(): String? {
            return sharedPreferences!!.getString(TOKEN, "")
        }

        companion object {

            private val TOKEN = "token"
            private var sharedPreferences: SharedPreferences? = null
            private var editor: SharedPreferences.Editor? = null

            @Synchronized
            fun getInstance(context: Context): SharedPref {

                if (sharedPreferences == null) {
                    sharedPreferences =
                        context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    editor = sharedPreferences!!.edit()
                }
                return SharedPref()
            }
        }
    }
