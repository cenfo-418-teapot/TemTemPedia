package com.teapot.temtempedia

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.teapot.temtempedia.databinding.ActivityTemtemInfoBinding
import android.content.Context
import android.content.Intent
import android.widget.TextView
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teapot.temtempedia.models.Temtem

class TemtemInfo : AppCompatActivity() {

    private lateinit var binding: ActivityTemtemInfoBinding
    private var idTemem = 59
    private lateinit var stats: Stats
    private var titulo = "#0 Temtem"
    private var imagenNormal = ""
    private var imagenLuma = ""
    private var ratioCaptura =  "0"
    private var minutosEclosion = "0"
    private var porcentajeSexo = "Porcentaje de sexos"
    private var imagenTipo1 = "tipo_fuego_background.xml"
    private var imagenTipo2 = "tipo_fuego_background.xml"
    private lateinit var rasgos: Array<String>
    private lateinit var fortalezas: Fortalezas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTemtemInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val infoEmpaquetada = intent.extras
        if (infoEmpaquetada != null) {
            idTemem = infoEmpaquetada.getInt("temtem")
        }
        fillUpTemtem()
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.actionBack.setOnClickListener { view ->
//            Snackbar.make(view, "Ha vuelto a la lista principal", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun fillUpTemtem() {
        getDataTemtem()
        findViewById<TextView>(R.id.titulo_temem).text = titulo
        findViewById<TextView>(R.id.ratio_captura).text = ratioCaptura
        findViewById<TextView>(R.id.minutos_eclosion).text = minutosEclosion
        findViewById<TextView>(R.id.porcentaje_sexo).text = porcentajeSexo
        findViewById<ImageView>(R.id.foto_tipo_1).setImageResource(
            resources.getIdentifier(imagenTipo1, "mipmap",this.packageName))
        findViewById<ImageView>(R.id.foto_tipo_2).setImageResource(
            resources.getIdentifier(imagenTipo2, "mipmap",this.packageName))
        findViewById<ImageView>(R.id.imagen_normal).setImageResource(
            resources.getIdentifier(imagenNormal, "drawable",this.packageName))
        findViewById<ImageView>(R.id.imagen_luma).setImageResource(
            resources.getIdentifier(imagenLuma, "drawable",this.packageName))
        setUpStatsBar()
        setUpFortalezas()
        findViewById<TextView>(R.id.rasgo1).text = rasgos[0]
        findViewById<TextView>(R.id.rasgo2).text = rasgos[1]
    }
    fun getDataTemtem() {
        val temtem = getTemtem(idTemem) ?: return
        titulo = "#" + temtem.id + " " + temtem.nombre
        imagenNormal = temtem.fotoNormal
        imagenLuma = temtem.fotoLuma
        ratioCaptura = temtem.ratioCaptura.toString()
        porcentajeSexo = "M: " + String.format("%.2f", temtem.porcentajeM).toFloat()*100 +
                "% - F: " + String.format("%.2f", temtem.porcentajeF).toFloat()*100 + "%"
        minutosEclosion = temtem.minutosEclosion.toString()
        imagenTipo1 = "tipo_" + temtem.tipos[0] + "_foreground"
        if (temtem.tipos.size>1)
            imagenTipo2 = "tipo_" + temtem.tipos[1] + "_foreground"
        rasgos =  temtem.rasgos
        stats =  temtem.stats
        fortalezas = temtem.fortalezas
    }


    private fun setUpStatsBar() {
        val pgHP = findViewById<ProgressBar>(R.id.pg_HP)
        val pgSTA = findViewById<ProgressBar>(R.id.pg_STA)
        val pgSPE = findViewById<ProgressBar>(R.id.pg_SPE)
        val pgATK = findViewById<ProgressBar>(R.id.pg_ATK)
        val pgDEF = findViewById<ProgressBar>(R.id.pg_DEF)
        val pgSPATK = findViewById<ProgressBar>(R.id.pg_SPATK)
        val pgSPDEF = findViewById<ProgressBar>(R.id.pg_SPDEF)
        setUpStatBar(pgHP, stats.ps)
        setUpStatBar(pgSTA, stats.sta)
        setUpStatBar(pgSPE, stats.spd)
        setUpStatBar(pgATK, stats.atk)
        setUpStatBar(pgDEF, stats.def)
        setUpStatBar(pgSPATK, stats.spatk)
        setUpStatBar(pgSPDEF, stats.spdef)

        setUpStatsNumbers()
    }

    private fun setUpStatsNumbers() {
        findViewById<TextView>(R.id.HP_stat).text = stats.ps.toString()
        findViewById<TextView>(R.id.STA_stat).text = stats.sta.toString()
        findViewById<TextView>(R.id.SPE_stat).text = stats.spd.toString()
        findViewById<TextView>(R.id.ATK_stat).text = stats.atk.toString()
        findViewById<TextView>(R.id.DEF_stat).text = stats.def.toString()
        findViewById<TextView>(R.id.SPATK_stat).text = stats.spatk.toString()
        findViewById<TextView>(R.id.SPDEF_stat).text = stats.spdef.toString()
    }

    private fun setUpFortalezas() {
        findViewById<TextView>(R.id.resistencia_1).text = "x" + fortalezas.neutral
        findViewById<TextView>(R.id.resistencia_2).text = "x" + fortalezas.fuego
        findViewById<TextView>(R.id.resistencia_3).text = "x" + fortalezas.agua
        findViewById<TextView>(R.id.resistencia_4).text = "x" + fortalezas.naturaleza
        findViewById<TextView>(R.id.resistencia_5).text = "x" + fortalezas.electricidad
        findViewById<TextView>(R.id.resistencia_6).text = "x" + fortalezas.roca
        findViewById<TextView>(R.id.resistencia_7).text = "x" + fortalezas.mental
        findViewById<TextView>(R.id.resistencia_8).text = "x" + fortalezas.viento
        findViewById<TextView>(R.id.resistencia_9).text = "x" + fortalezas.digital
        findViewById<TextView>(R.id.resistencia_10).text = "x" + fortalezas.melee
        findViewById<TextView>(R.id.resistencia_11).text = "x" + fortalezas.cristal
        findViewById<TextView>(R.id.resistencia_12).text = "x" + fortalezas.toxico
    }

    private fun setUpStatBar(bar: ProgressBar, stat: Int) {
        ObjectAnimator.ofInt(bar,"progress", stat)
            .setDuration(2000)
            .start()
    }

    fun getTemtem(id: Int): Temtem? {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "TemtemSource.json")
        val gson = Gson()
        val listaTetmtem = object : TypeToken<List<Temtem>>() {}.type
        var temtem: List<Temtem> = gson.fromJson(jsonFileString, listaTetmtem)
        temtem.forEachIndexed { index, temp ->
            if (temp.id == id)
                return temp
        }
        return null;
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}