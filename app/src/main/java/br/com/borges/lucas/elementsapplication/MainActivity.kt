package br.com.borges.lucas.elementsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.borges.lucas.elementsapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.buttonToast.setOnClickListener{
      val toast = Toast.makeText( this, "Toast", Toast.LENGTH_LONG )
      toast.show()
    }
  }

  private fun toast( str: String ) {
    Toast.makeText( this, str, Toast.LENGTH_LONG ).show()
  }
}