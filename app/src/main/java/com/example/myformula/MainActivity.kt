package com.example.myformula

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myformula.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinnerFormulas : Spinner = findViewById(R.id.select_formula)
        ArrayAdapter.createFromResource(
            this,
            R.array.formulas,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFormulas.adapter = adapter
        }
        binding.selectFormula.onItemSelectedListener = this

        binding.thirdData.visibility = View.INVISIBLE

    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val formuleSelected = position.toString().toInt()
        if (formuleSelected == 0) {
            binding.thirdData.visibility = View.INVISIBLE
            val formulaImage = findViewById<ImageView>(R.id.formula_image)
            formulaImage.setImageResource(R.drawable.moles_compuesto)

            val boxone = findViewById<EditText>(R.id.first_data)
            boxone.hint = getString(R.string.num_moles)
            val boxtwo = findViewById<EditText>(R.id.second_data)
            boxtwo.hint = getString(R.string.masa_mol)

            binding.verificauno.setOnClickListener{
                val num1: Double
                val num2: Double
                if(binding.firstData.text.isNotEmpty() && binding.secondData.text.isNotEmpty()) {
                    if (binding.secondData.text.toString().toDouble() <= 0) {
                        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
                    } else {
                        num1 = binding.firstData.text.toString().toDouble()
                        num2 = binding.secondData.text.toString().toDouble()
                        val resultado = numoles(num1, num2)
                        binding.viewoneResult.text = getString(R.string.view_resultado, resultado)
                    }
                }
            }

        }else if (formuleSelected == 1){
            binding.thirdData.visibility = View.VISIBLE
            val formulaImage = findViewById<ImageView>(R.id.formula_image)
            formulaImage.setImageResource(R.drawable.gases_ideales)

            val boxone = findViewById<EditText>(R.id.first_data)
            boxone.hint = getString(R.string.num_moles)
            val boxtwo = findViewById<EditText>(R.id.second_data)
            boxtwo.hint = getString(R.string.temp_K)
            val boxthree = findViewById<EditText>(R.id.third_data)
            boxthree.hint = getString(R.string.volumen)

            binding.verificauno.setOnClickListener {
                val num1: Double
                val num2: Double
                val num3: Double

                if (binding.firstData.text.isNotEmpty() && binding.secondData.text.isNotEmpty() && binding.thirdData.text.isNotEmpty()) {
                    if (binding.thirdData.text.toString().toDouble() <= 0) {
                        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
                    } else {
                        num1 = binding.firstData.text.toString().toDouble()
                        num2 = binding.secondData.text.toString().toDouble()
                        num3 = binding.thirdData.text.toString().toDouble()
                        val resultado = presion(num1, num2, num3)
                        binding.viewoneResult.text = getString(R.string.view_resultado, resultado)
                    }
                }
            }

        } else{
            binding.thirdData.visibility = View.INVISIBLE
            val formulaImage = findViewById<ImageView>(R.id.formula_image)
            formulaImage.setImageResource(R.drawable.masa_equivalente)

            val boxone = findViewById<EditText>(R.id.first_data)
            boxone.hint = getString(R.string.masa_atom)
            val boxtwo = findViewById<EditText>(R.id.second_data)
            boxtwo.hint = getString(R.string.valencia)

            binding.verificauno.setOnClickListener {
                val num1: Double
                val num2: Double

                if (binding.firstData.text.isNotEmpty() && binding.secondData.text.isNotEmpty()) {
                    if (binding.secondData.text.toString().toDouble() <= 0) {
                        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
                    } else {
                        num1 = binding.firstData.text.toString().toDouble()
                        num2 = binding.secondData.text.toString().toDouble()
                        val resultado = masaeq(num1, num2)
                        binding.viewoneResult.text = getString(R.string.view_resultado, resultado)
                    }
                }
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun numoles(masag: Double, masam: Double): Double {
        return masag / masam
    }

    private fun presion(numol: Double, temp: Double, vol: Double): Double {
        val r = 0.082057
        return ((numol * r * temp)/vol)
    }

    private fun masaeq(masat: Double, valen: Double): Double {
        return masat / valen
    }
}