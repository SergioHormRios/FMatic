package net.azarquiel.fmatic.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.interfaces.GlobalInterface


class LoginActivity : AppCompatActivity(), GlobalInterface{
    private val GOOGLE_SING_IN = 100
    //Botones
    private lateinit var googleLogBtn: Button
    private lateinit var btnLogin: Button
    private lateinit var registerBtn: Button

    //EdiText
    private lateinit var etEmail : EditText
    private lateinit var etPassword: EditText

    //Firebase
    private val instance = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

    }
    private fun initView() {
        title = "Autenticación"

        //Asignamos los listener en tempo de ejecucion
        registerBtn = findViewById(R.id.registeBtn)
        registerBtn.setOnClickListener {
            if(etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty())
                instance
                    .createUserWithEmailAndPassword(etEmail.text.toString(),etPassword.text.toString())
                    //Comprobamos si la operacion de registro ha sido completada
                    .addOnCompleteListener{
                        if (it.isSuccessful) openMainView(it.result?.user?.email ?: "", "")
                        else showMessage(this,"ERROR","ERROR, El usuario ya se encuentra registrado.")
                    }
            else
                if (etEmail.text.isEmpty())
                    showMessage(this, "ERROR", "No se ha introduccido ningun correo.")
                else if(etPassword.text.isEmpty())
                    showMessage(this, "ERROR", "No se ha introduccido ninguna contraseña.")
        }

        btnLogin = findViewById(R.id.loginBtn)
        btnLogin.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty())
                instance
                    .signInWithEmailAndPassword( etEmail.text.toString(),etPassword.text.toString())
                    //Comprobamos si la operacion de registro ha sido completada
                    .addOnCompleteListener {
                        if (it.isSuccessful) openMainView(it.result?.user?.email ?: "", "")
                        else
                            showMessage(this, "ERROR",
                                "La contraseña o el correo no se han especificado correctamente."
                            )
                    }
            else
                if (etEmail.text.isEmpty()) showMessage(this, "ERROR", "No se ha introduccido ningun correo.")
                else if(etPassword.text.isEmpty()) showMessage(this, "ERROR", "No se ha introduccido ninguna contraseña.")

        }

        googleLogBtn = findViewById(R.id.googleBtn)
        googleLogBtn.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this,googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)
        }

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SING_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            //Colocamos el try-cacth ya que esta parte puede dar error
            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null)
                    instance.signInWithCredential(
                        GoogleAuthProvider.getCredential(account.idToken, null)
                    ).addOnCompleteListener {
                        if (it.isSuccessful) openMainView(account.email ?: "", account.givenName ?: "")
                        else
                            showMessage(this, "ERROR","No se ha conectado a la cuenta de google correctamente.")
                    }
            }catch (e: ApiException) {
                showMessage(this, "Error", "No se ha podido iniciar la cuenta de google correctamente.")
            }
        }
    }
    private fun openMainView(email: String, name: String) =
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                //Al ya tenerlo instanciado podremos mandar los datos que queramos
                putExtra("email",email)
                if (name != "")
                    putExtra("name",name)
            }
        )

}