package net.azarquiel.fmatic.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.enums.ProviderType
import net.azarquiel.fmatic.interfaces.GlobalFun


class LoginActivity : AppCompatActivity(), GlobalFun{

    //Botones
    private lateinit var googleLogBtn: Button
    private lateinit var btnLogin: Button
    private lateinit var registerBtn: Button

    //EdiText
    private lateinit var etEmail : EditText
    private lateinit var etPassword: EditText

    //Firebase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

    }


    private fun initView() {
        title = "Autenticación"
        val instance = FirebaseAuth.getInstance()
        //Asignamos los listener en tempo de ejecucion
        registerBtn = findViewById(R.id.RegisteBtn)
        registerBtn.setOnClickListener {
            if(etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty())
                instance
                    .createUserWithEmailAndPassword(etEmail.text.toString(),etPassword.text.toString())
                    //Comprobamos si la operacion de registro ha sido completada
                    .addOnCompleteListener{
                        if (it.isSuccessful) openMainView(it.result?.user?.email ?: "",ProviderType.BASIC)
                        else showMessage(this,"ERROR","ERROR, Usuario no registrado.")
                    }
        }

        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            if (etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()) {
                instance
                    .signInWithEmailAndPassword( etEmail.text.toString(),etPassword.text.toString())
                    //Comprobamos si la operacion de registro ha sido completada
                    .addOnCompleteListener {
                        if (it.isSuccessful) openMainView(it.result?.user?.email ?: "", ProviderType.BASIC)
                        else showMessage(this, "ERROR", "ERROR, Usuario no registrado.")
                    }
            }
        }

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
    }

    private fun openMainView(email: String, provider:ProviderType) =
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                //Al ya tenerlo instanciado podremos mandar los datos que queramos
                putExtra("email",email)
                putExtra("provider", provider.name)
            }
        )

}