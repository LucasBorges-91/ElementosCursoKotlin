package br.com.borges.lucas.elementsapplication

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewParent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import br.com.borges.lucas.elementsapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
  SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.buttonToast.setOnClickListener {
      val toast = Toast.makeText(this, "Toast", Toast.LENGTH_LONG)

      //pegando o texto de dentro do toast
//      val text = toast.view?.findViewById<TextView>( R.id.message )
//      text?.setTextColor( Color.RED )

      //setando um layout personalizado para a toast
      val layout = LayoutInflater.from(this).inflate(R.layout.toast_layout, null)
      toast.view = layout

      //setando o gravity do toast
      toast.setGravity(Gravity.TOP, 0, 0)
      toast.show()
    }

    binding.buttonSnack.setOnClickListener {
      val snack = Snackbar.make(binding.linearRoot, "Snack", Snackbar.LENGTH_SHORT)

      snack.setAction("desfazer", View.OnClickListener {
        toast("desfeito")
      })
      snack.setActionTextColor(Color.BLUE)
      snack.setBackgroundTint(Color.GRAY)
      snack.show()
    }

    binding.spinnerStatic.onItemSelectedListener = this

    //configura spinner dinamico
    loadSpinner()

    binding.buttonGetSpinner.setOnClickListener {
      val selectItem = binding.spinnerStatic.selectedItem
      val selectItemId = binding.spinnerStatic.selectedItemId
      val selectItemPosition = binding.spinnerStatic.selectedItemPosition

      toast("position: $selectItemId: $selectItem")
    }

    binding.buttonSetSpinner.setOnClickListener {
      binding.spinnerStatic.setSelection(2)
    }

    binding.seekbar.setOnSeekBarChangeListener(this)

    binding.buttonGetSeek.setOnClickListener {
      toast( "Seekbar: ${binding.seekbar.progress}")
    }

    binding.buttonSetSeek.setOnClickListener {
      binding.seekbar.progress = 10
    }

    binding.switchButton.setOnCheckedChangeListener( this )

    binding.checkButton.setOnCheckedChangeListener( this )

    binding.radioOn.setOnCheckedChangeListener( this )

    binding.radioOff.setOnCheckedChangeListener( this )

  }

  private fun loadSpinner() {
    val mList = listOf("gramas", "kg", "arroba", "tonelada")
    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList)
    binding.spinnerDynamic.adapter = adapter
  }

  private fun toast(str: String) {
    Toast.makeText(this, str, Toast.LENGTH_LONG).show()
  }

  override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
    when (parent?.id) {
      R.id.spinner_static -> {
        toast(parent?.getItemAtPosition(position).toString())
      }
    }
  }

  override fun onNothingSelected(p0: AdapterView<*>?) {
    toast("nothing")
  }

  override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
    binding.textSeekbarValue.text = "valor seekbar: $progress"
  }

  override fun onStartTrackingTouch(p0: SeekBar?) {
    toast( "track started")
  }

  override fun onStopTrackingTouch(p0: SeekBar?) {
    toast( "track stoped")
  }

  override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
    when ( p0?.id ) {
      R.id.switch_button -> {
//        binding.switchButton.isChecked = true
        toast( "Switch: ${if (p1) "true" else "false"}")
      }

      R.id.check_button -> {
        toast( "CheckBox: ${if (p1) "true" else "false"}")
//        binding.checkButton.isChecked = true
      }

      R.id.radio_on -> {
        toast( "Radio on: ${if (p1) "true" else "false"}")
      }

      R.id.radio_off -> {
        toast( "Radio off: ${if (p1) "true" else "false"}")
      }
    }
  }
}