package com.example.lab3

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.lab3.databinding.FragmentConfirmBinding
import com.example.lab3.databinding.FragmentDataInputBinding
import java.io.*


class ConfirmFragment : Fragment() {

    lateinit var binding: FragmentConfirmBinding
    private val choiceModel : ChoiceModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        choiceModel.choice.observe(activity as LifecycleOwner, {
            binding.textConfirm.text = choiceModel.choice.value
                ?.joinToString(prefix = "You chose ", separator = " ", postfix = " phone. Confirm?")
        })

        binding.button.setOnClickListener {
            try {
                context?.openFileOutput("storage.txt", Context.MODE_APPEND).use {
                    it?.write(choiceModel.choice.value?.joinToString(separator = " ", postfix = " phone. \n")?.toByteArray())
                }
            } catch (e : IOException) { }
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frameLayoutConfirm, NotificationFragment.newInstance())
                ?.commit()}

        binding.button2.setOnClickListener {
            choiceModel.choice.value = mutableListOf("You've canceled your choice")
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frameLayoutConfirm, NotificationFragment.newInstance())
                ?.commit()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = ConfirmFragment()
    }
}
