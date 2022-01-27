package br.com.borges.lucas.elementsapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import br.com.borges.lucas.elementsapplication.databinding.ActivityMainBinding
import br.com.borges.lucas.elementsapplication.databinding.ActivityTimeAcitvityBinding
import java.text.SimpleDateFormat
import java.util.*

class TimeAcitvity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
  private lateinit var binding: ActivityTimeAcitvityBinding
  private val mSimpleDate = SimpleDateFormat( "dd/MM/yyyy" )

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    binding = ActivityTimeAcitvityBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.buttonDate.setOnClickListener{
      val calendar = Calendar.getInstance()
      val day = calendar.get( Calendar.DAY_OF_MONTH )
      val month = calendar.get( Calendar.MONTH )
      val year = calendar.get( Calendar.YEAR )
      DatePickerDialog(this, this, year, month, day ).show()
    }

  }

  private fun toast( str: String ) {
    Toast.makeText( this, str, Toast.LENGTH_SHORT).show()
  }

  override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
    val date = Calendar.getInstance()
    date.set(year, month, dayOfMonth)
    binding.buttonDate.text = mSimpleDate.format(date.time)
  }
}