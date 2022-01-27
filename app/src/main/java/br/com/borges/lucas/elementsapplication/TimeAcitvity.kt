package br.com.borges.lucas.elementsapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import br.com.borges.lucas.elementsapplication.databinding.ActivityMainBinding
import br.com.borges.lucas.elementsapplication.databinding.ActivityTimeAcitvityBinding
import java.text.SimpleDateFormat
import java.util.*

class TimeAcitvity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
  TimePickerDialog.OnTimeSetListener,TimePicker.OnTimeChangedListener {
  private lateinit var binding: ActivityTimeAcitvityBinding
  private val mSimpleDate = SimpleDateFormat("dd/MM/yyyy")

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    binding = ActivityTimeAcitvityBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.buttonDate.setOnClickListener {
      val calendar = Calendar.getInstance()
      val day = calendar.get(Calendar.DAY_OF_MONTH)
      val month = calendar.get(Calendar.MONTH)
      val year = calendar.get(Calendar.YEAR)
      DatePickerDialog(this, this, year, month, day).show()
    }

    binding.buttonTime.setOnClickListener {
      TimePickerDialog(this, this, 1, 1, false).show()
    }

    binding.timepicker.setOnTimeChangedListener( this )

    binding.buttonSetTime.setOnClickListener{
      if ( Build.VERSION.SDK_INT >= 23 ) {
        binding.timepicker.hour = 20
        binding.timepicker.minute = 20
      } else {
        binding.timepicker.currentHour = 20
        binding.timepicker.currentMinute = 20
      }
    }

    binding.buttonGetTime.setOnClickListener{
      var hour = 0
      var minute = 0
      if ( Build.VERSION.SDK_INT >= 23 ) {
        hour = binding.timepicker.hour
        minute = binding.timepicker.minute
      } else {
        hour = binding.timepicker.currentHour
        minute = binding.timepicker.currentMinute
      }
      toast( "$hour:$minute")
    }
  }

  private fun toast(str: String) {
    Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
  }

  override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
    val date = Calendar.getInstance()
    date.set(year, month, dayOfMonth)
    binding.buttonDate.text = mSimpleDate.format(date.time)
  }

  override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
    toast( "$hourOfDay:$minute")
  }

  override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
    toast( "$hourOfDay:$minute")
  }
}