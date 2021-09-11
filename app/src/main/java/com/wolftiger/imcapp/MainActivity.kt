package com.wolftiger.imcapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity() : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }

    private fun setListener() {
        edtPeso?.doOnTextChanged { text, _, _, _ ->
            txtTitle?.text = text
        }
        btnCalcular.setOnClickListener{
            calcularImc(edtPeso.text.toString(), edtAltura.text.toString())
        }
    }

    private fun calcularImc(peso: String, altura: String) {
        val peso = peso.toFloatOrNull()
        val altura = altura.toFloatOrNull()
        if (peso != null && altura != null ){
            val imc = peso / (altura*altura)
            txtTitle.text = String.format("Seu imc é:\n%.2f",imc)
            txtResult.text =  imcResult(imc)
        }
    }

    private fun imcResult(imc: Float) : String {
        return when {
            imc <= 18.59f -> "Abaixo do peso"
            imc in 18.60f..24.99f ->  "Peso ideal (Parabéns)"
            imc in 25f..29.99f ->  "Levemente acima do peso!"
            imc in 30f..34.99f ->  "Obesidade grau 1"
            imc in 35f..39.99f ->"Obesidade grau 2 (severa)"
            imc >= 40f -> "Obesidade grau 3 (mórbida)"
            else -> "Dados inconsistentes, verifique"
        }
    }
}