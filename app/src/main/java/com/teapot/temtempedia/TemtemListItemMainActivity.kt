package com.teapot.temtempedia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID: Int = 59
private const val NAME: String = "Cacaroto"
private const val THUMB_IMG: String = "image.png"

class TemtemListItemMainActivity : Fragment() {

    private var id: Int? = null
    private var name: String? = null
    private var thumbImg: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ID.toString())
            name = it.getString(NAME)
            thumbImg = it.getString(THUMB_IMG)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_temtem_list_item_main_activity, container, false)
        val fragmentName = view.findViewById(R.id.fragmentName) as TextView
        val fragmentThumb = view.findViewById(R.id.fragmentThumb) as ImageView
        view.setOnClickListener { view ->
            val intent= Intent(activity, TemtemInfo::class.java)
            val intentParams = Bundle()
            id?.let { intentParams.putInt("temtem", it) }
            intent.putExtras(intentParams)
            startActivity(intent)
        }
        fragmentName.text = name
        fragmentThumb.setImageResource(resources.getIdentifier(thumbImg, "drawable", "com.teapot.temtempedia"))

        return view
    }

    companion object {
        fun newInstance(id: Int, name: String, thumbImg: String) =
            TemtemListItemMainActivity().apply {
                arguments = Bundle().apply {
                    putInt(ID.toString(), id)
                    putString(NAME, name)
                    putString(THUMB_IMG, thumbImg)
                }
            }
    }
}