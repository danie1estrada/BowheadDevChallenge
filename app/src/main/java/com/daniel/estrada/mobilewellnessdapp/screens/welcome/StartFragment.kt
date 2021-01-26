package com.daniel.estrada.mobilewellnessdapp.screens.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.daniel.estrada.mobilewellnessdapp.R
import com.google.android.material.button.MaterialButton

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialButton>(R.id.buttonStart).setOnClickListener {
            findNavController().navigate(
                R.id.action_startFragment_to_createPasswordFragment,
                null,
                null,
                FragmentNavigatorExtras(view.findViewById<ImageView>(R.id.image) to getString(R.string.shared_animation))
            )
        }
    }
}