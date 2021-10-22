package com.teapot.temtempedia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID: Int = 59
private const val NAME: String = "Cacaroto"
private const val THUMB_IMG: String = "image.png"
private  val Show = false

private var temtemList : MutableList<Int?> = mutableListOf()

class TemtemListItemMainActivity : Fragment() {

    private var id: Int? = null
    private var name: String? = null
    private var thumbImg: String? = null
    private  var show : Boolean = false
    private var checkBtn : CheckBox? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ID.toString())
            name = it.getString(NAME)
            thumbImg = it.getString(THUMB_IMG)
            show = it.getBoolean(Show.toString())
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_temtem_list_item_main_activity, container, false)
        val fragmentName = view.findViewById(R.id.fragmentName) as TextView
        val fragmentThumb = view.findViewById(R.id.fragmentThumb) as ImageView
        val check = view.findViewById<CheckBox>(R.id.checkBoxTemtemMain)

        checkBtn = check

        view.setOnClickListener { view ->
            Log.i("caca XXXXX", "$id")
            val intent= Intent(activity, TemtemInfo::class.java)
            val intentParams = Bundle()
            id?.let { intentParams.putInt("temtem", it) }
            intent.putExtras(intentParams)
            startActivity(intent)
        }
        fragmentName.text = name
        fragmentThumb.setImageResource(resources.getIdentifier(thumbImg, "drawable", "com.teapot.temtempedia"))
        check.text = "Agregar"

        if (show == true){
            check.visibility = View.VISIBLE
        }


        isChecked()
        return view
    }

    companion object {
        fun newInstance(id: Int, name: String, thumbImg: String, show: Boolean) =
            TemtemListItemMainActivity().apply {
                arguments = Bundle().apply {
                    putInt(ID.toString(), id)
                    putString(NAME, name)
                    putString(THUMB_IMG, thumbImg)
                    putBoolean(Show.toString(), show)
                }
            }
    }

    fun isChecked(){
        checkBtn?.setOnCheckedChangeListener { compoundButton, b ->

            if (compoundButton.isChecked){
                checkBtn?.text = "No Agregar"
                temtemList.add(id)
                Toast.makeText(context, temtemList.toString(), Toast.LENGTH_SHORT).show()

                (getActivity() as SaveTeam?)?.passDataCom(temtemList)

            } else {
                checkBtn?.text = "Agregar"
                temtemList.remove(id)
            }
        }
    }


}




