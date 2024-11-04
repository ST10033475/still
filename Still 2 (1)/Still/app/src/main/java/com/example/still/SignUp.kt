package com.example.still
import android.content.ContentValues.TAG
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

class SignUp : BaseActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var executor: Executor

    // Google sign in
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001 // Request code for Google Sign-In
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)

// Initialize Firebase Auth

        auth = FirebaseAuth.getInstance()

// Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

            .requestIdToken("1029846371898-c7rk7vcrvuje24sr7p7quhgop2d9d4le.apps.googleusercontent.com") // Replace with your Firebase project client ID

            .requestEmail()

            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        findViewById<SignInButton>(R.id.googleSignInButton).setOnClickListener {

            signInWithGoogle()
        }


// Initialize other views and handle regular sign-up flow
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val loginRedirection = findViewById<TextView>(R.id.Redirect2Login)
        val username = findViewById<EditText>(R.id.LoginUsername)
        val password = findViewById<EditText>(R.id.LoginPassword)
        val confirmPassword = findViewById<EditText>(R.id.ConfirmPassword)

// Handle redirection to login page

        loginRedirection.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        executor = ContextCompat.getMainExecutor(this)

        btnLogin.setOnClickListener {
            val email = username.text.toString().trim()
            val pass = password.text.toString().trim()
            val confirmPass = confirmPassword.text.toString().trim()
            if (email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {

                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if (pass.length < 6) {

                Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if (pass != confirmPass) {

                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

// Check if the device supports biometric authentication
            val biometricManager = BiometricManager.from(this)
            when (biometricManager.canAuthenticate()) {

                BiometricManager.BIOMETRIC_SUCCESS -> {

                    showBiometricPrompt { success ->
                        if (success) {

// Proceed with Firebase Auth if biometric authentication is successful

                            auth.createUserWithEmailAndPassword(email, pass)

                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {

                                        Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()

// Navigate to next screen

                                        startActivity(Intent(this, HomePage::class.java))

                                    } else {

                                        Toast.makeText(this, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()

                                    }
                                }

                        } else {

                            Toast.makeText(this, "Fingerprint authentication failed", Toast.LENGTH_SHORT).show()

                        }
                    }

                }

                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->

                    Toast.makeText(this, "No biometric hardware available", Toast.LENGTH_SHORT).show()

                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->

                    Toast.makeText(this, "Biometric hardware is currently unavailable", Toast.LENGTH_SHORT).show()

                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->

                    Toast.makeText(this, "No biometric credentials enrolled", Toast.LENGTH_SHORT).show()

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

                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)

                firebaseAuthWithGoogle(account.idToken!!)

            } catch (e: ApiException) {

                Log.w(TAG, "Google sign in failed", e)

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

                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser

                    Toast.makeText(this, "Sign in successful!", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, HomePage::class.java))

                    finish()

                } else {

                    Log.w(TAG, "signInWithCredential:failure", task.exception)

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

            .setSubtitle("Authenticate with your fingerprint")

            .setNegativeButtonText("Cancel")

            .build()

        biometricPrompt.authenticate(promptInfo)

    }

}

