package by.tix.draft.artemapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity: AppCompatActivity() {

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(
            if (auth.currentUser == null) {
                Intent(this, AuthActivity::class.java)
            } else {
                Intent(this, MainActivity::class.java)
            }
        )
    }

}
