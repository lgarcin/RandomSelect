package com.mpsi.laurent.randomselect

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.select_fragment.*
import java.time.LocalDate

class SelectFragment : Fragment() {
    private var nom: String? = null
    private var subList = ArrayList<String>()
    private var nombre: Int = 5

    private val epreuveViewModel: EpreuveViewModel by lazy {
        ViewModelProviders.of(this).get(EpreuveViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.select_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                nom = name.text.toString()
                (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(name.windowToken, 0)
                name.clearFocus()
                true
            } else false
        }
        nombre_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                nombre = progress
                select()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        select()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save -> {
            save()
            true
        }
        R.id.refresh -> {
            select()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun select() {
        val list = resources.getStringArray(R.array.eleves).toMutableList()
        list.shuffle()
        subList = ArrayList(list.subList(0, nombre))
        val adapter = ArrayAdapter(activity ?: return, android.R.layout.simple_list_item_1, subList)
        listview.adapter = adapter
    }

    private fun save() {
        if (nom == null) {
            Toast.makeText(this.context, "Indiquer le nom de l'épreuve", Toast.LENGTH_SHORT).show()
        } else {
            epreuveViewModel.insert(Epreuve(nom = nom
                    ?: return, eleves = subList, date = LocalDate.now()))
            Toast.makeText(this.context, "Epreuve $nom sauvegardée", Toast.LENGTH_SHORT).show()
        }

    }

}
