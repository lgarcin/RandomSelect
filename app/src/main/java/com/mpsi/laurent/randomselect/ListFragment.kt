package com.mpsi.laurent.randomselect

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {

    private val epreuveViewModel: EpreuveViewModel by lazy {
        ViewModelProviders.of(this).get(EpreuveViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EpreuveAdapter()
        recycler_view.adapter = adapter
        epreuveViewModel.getAll()?.observe(this, Observer {
            adapter.setItems(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.save).isVisible = false
        menu.findItem(R.id.refresh).isVisible = false
    }
}
