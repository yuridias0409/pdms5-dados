package pdms5.tfinal.dados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import pdms5.tfinal.dados.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var geradorRandomico: Random

    private lateinit var settingsActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        geradorRandomico = Random(System.currentTimeMillis())

        activityMainBinding.jogarDadoBt.setOnClickListener {
            val resultado: Int = geradorRandomico.nextInt(1..6)
            "A face sorteada foi $resultado".also { activityMainBinding.resultadoTv.text }
            val nomeImagem: String = "dice_${resultado}"
            activityMainBinding.resultadoIv.setImageResource(
                resources.getIdentifier(nomeImagem, "mipmap", packageName)
            )
        }

        settingsActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK){
                if(result.data != null){
                    val configuracao:Configuracao? = result.data?.getParcelableExtra<Configuracao>(Intent.EXTRA_USER)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.settingsMi){
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            settingsActivityLauncher.launch(settingsIntent)
            return true
        }
        return false
    }
}