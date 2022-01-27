package br.com.borges.lucas.elementsapplication

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.borges.lucas.elementsapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.buttonToast.setOnClickListener{
      val toast = Toast.makeText( this, "Toast", Toast.LENGTH_LONG )

      //pegando o texto de dentro do toast
//      val text = toast.view?.findViewById<TextView>( R.id.message )
//      text?.setTextColor( Color.RED )

      //setando um layout personalizado para a toast
      val layout = LayoutInflater.from(this).inflate( R.layout.toast_layout, null )
      toast.view = layout

      //setando o gravity do toast
      toast.setGravity( Gravity.TOP, 0, 0 )
      toast.show()
    }

    binding.buttonSnack.setOnClickListener{
      val snack = Snackbar.make(binding.linearRoot, "Snack", Snackbar.LENGTH_SHORT )

      snack.setAction( "desfazer" , View.OnClickListener {
        toast( "desfeito" )
      })
      snack.setActionTextColor( Color.BLUE )
      snack.setBackgroundTint( Color.GRAY )
      snack.show()
    }

    //configura spinner dinamico
    loadSpinner()
  }

  private fun loadSpinner() {
    val mList = listOf("gramas", "kg", "arroba", "tonelada")
    val adapter = ArrayAdapter( this, android.R.layout.simple_spinner_dropdown_item, mList )
    binding.spinnerDynamic.adapter = adapter
  }

  private fun toast( str: String ) {
    Toast.makeText( this, str, Toast.LENGTH_LONG ).show()
  }
}