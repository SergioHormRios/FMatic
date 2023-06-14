package net.azarquiel.fmatic.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.interfaces.GlobalInterface

class RegisterActivity : AppCompatActivity(),GlobalInterface {
    //EdiText
    private lateinit var etNick : EditText
    private lateinit var etEmailRegister : EditText
    private lateinit var etPasswordRegister: EditText

    private lateinit var btnRegister: Button

    //Firebase
    private val instance = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    private fun initView() {
        etEmailRegister = findViewById(R.id.etEmailRegister)
        etNick = findViewById(R.id.etNickRegister)
        etPasswordRegister = findViewById(R.id.etPasswordRegister)

        btnRegister = findViewById(R.id.btnRegiste)
        btnRegister.setOnClickListener {
            if(etEmailRegister.text.isNotEmpty() && etPasswordRegister.text.isNotEmpty() && etNick.text.isNotEmpty())
                instance
                    .createUserWithEmailAndPassword(etEmailRegister.text.toString(),etPasswordRegister.text.toString())
                    //Comprobamos si la operacion de registro ha sido completada
                    .addOnCompleteListener{
                        if (it.isSuccessful) openMainView(it.result?.user?.email ?: "", etNick.text.toString())
                        else showMessage(this,"ERROR","ERROR, El usuario ya se encuentra registrado.")
                    }
            else
                if (etEmailRegister.text.isEmpty())
                    showMessage(this, "ERROR", "No se ha introduccido ningun correo.")
                else if(etPasswordRegister.text.isEmpty())
                    showMessage(this, "ERROR", "No se ha introduccido ninguna contraseña.")
                else if(etNick.text.isEmpty())
                    showMessage(this, "ERROR", "No se ha introduccido ningun nick.")
        }
    }

    private fun openMainView(email: String, name: String) =
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                //Al ya tenerlo instanciado podremos mandar los datos que queramos
                putExtra("email",email)
                putExtra("name",name)

            }
        )
}