package com.example.still
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import java.util.concurrent.Executor

//Google sign in
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
class MainActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var executor: Executor


    // Google sign in
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001 // Request code for Google Sign-In

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

// Initialize Firebase Auth

        auth = FirebaseAuth.getInstance()

//Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

            .requestIdToken("1029846371898-c7rk7vcrvuje24sr7p7quhgop2d9d4le.apps.googleusercontent.com") // Replace with your Firebase project client ID

            .requestEmail()

            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        findViewById<SignInButton>(R.id.googleSignInButton).setOnClickListener {

            signInWithGoogle()
        }

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val username = findViewById<EditText>(R.id.LoginUsername)
        val password = findViewById<EditText>(R.id.LoginPassword)
        val SignUpRedirect = findViewById<TextView>(R.id.Redirect2SignUp)


// Set up biometric executor

        executor = ContextCompat.getMainExecutor(this)

// Redirect to Sign Up page

        SignUpRedirect.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)

            startActivity(intent)
        }

// Set up login button click listener

        btnLogin.setOnClickListener {

// Check if the device supports biometric authentication
            val biometricManager = BiometricManager.from(this)
            when (biometricManager.canAuthenticate()) {

                BiometricManager.BIOMETRIC_SUCCESS -> {

// Show biometric prompt

                    showBiometricPrompt { success ->
                        if (success) {

// If fingerprint authentication succeeds, proceed with Firebase login

                            performFirebaseLoginWithBiometrics()

                        } else {

                            Toast.makeText(this, "Fingerprint authentication failed", Toast.LENGTH_SHORT).show()

                        }
                    }

                }

                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->

                    Toast.makeText(this, "No biometric hardware available", Toast.LENGTH_SHORT).show()

                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->

                    Toast.makeText(this, "Biometric hardware is currently unavailable", Toast.LENGTH_SHORT).show()

                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {

// Suggest enrolling fingerprint or face data

                    Toast.makeText(this, "No biometric credentials enrolled. Please enroll via your device settings.", Toast.LENGTH_LONG).show()
                    val enrollIntent = Intent(android.provider.Settings.ACTION_BIOMETRIC_ENROLL)

                    startActivity(enrollIntent)

                }

            }
        }


    }

    // Function to sign in with Google
    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent

        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    // Handle the result of the Google Sign-In
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!

                Log.d(ContentValues.TAG, "firebaseAuthWithGoogle:" + account.id)

                firebaseAuthWithGoogle(account.idToken!!)

            } catch (e: ApiException) {

                Log.w(ContentValues.TAG, "Google sign in failed", e)

                Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_LONG).show()

            }

        }

    }

    // Authenticate with Firebase using the Google ID Token
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential)

            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Log.d(ContentValues.TAG, "signInWithCredential:success")
                    val user = auth.currentUser

                    Toast.makeText(this, "Sign in successful!", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, HomePage::class.java))

                    finish()

                } else {

                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)

                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()

                }
            }

    }


    // Function to show biometric prompt
    private fun showBiometricPrompt(onAuthenticationResult: (Boolean) -> Unit) {
        val biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)

                onAuthenticationResult(true)

            }
            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()

                onAuthenticationResult(false)

            }

        })
        val promptInfo = BiometricPrompt.PromptInfo.Builder()

            .setTitle("Fingerprint Authentication")

            .setSubtitle("Log in using your fingerprint")

            .setNegativeButtonText("Cancel")

            .build()

        biometricPrompt.authenticate(promptInfo)

    }

    // Function to perform Firebase login with biometrics
    private fun performFirebaseLoginWithBiometrics() {

// Assuming the user is already registered and the biometric ID corresponds to a Firebase user

// Here you would handle the logic to fetch the user based on the biometric login

// For demonstration, we're directly navigating to the home page

        Toast.makeText(this, "Login Successful via Fingerprint", Toast.LENGTH_SHORT).show()

        startActivity(Intent(this, HomePage::class.java))

    }

}

