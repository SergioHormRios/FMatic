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
    private lateinit var GoogleLogBtn: Button
    private lateinit var LoginBtn: Button
    private lateinit var RegisterBtn: Button

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

        //Asignamos los listener en tempo de ejecucion
        RegisterBtn = findViewById(R.id.RegisteBtn)
        RegisterBtn.setOnClickListener {
            if(etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(etEmail.text.toString(),etPassword.text.toString())
                    //Comprobamos si la operacion de registro ha sido completada
                    .addOnCompleteListener{
                        if (it.isSuccessful)
                            openMainView(it.result?.user?.email ?: "",ProviderType.BASIC)
                        else
                            showMessage(this,"ERROR","ERROR, Usuario no registrado.")

                    }
            }
        }

        LoginBtn = findViewById(R.id.LoginBtn)
        LoginBtn.setOnClickListener {
            if(etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(etEmail.text.toString(),etPassword.text.toString())
                    //Comprobamos si la operacion de login ha sido completada
                    .addOnCompleteListener{
                        if (it.isSuccessful)
                            openMainView(it.result?.user?.email ?: "",ProviderType.BASIC)
                        else
                            showMessage(this,"ERROR","ERROR, Usuario no registrado.")
                    }
            }
        }

        GoogleLogBtn = findViewById(R.id.LoginBtn)
        GoogleLogBtn.setOnClickListener {

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