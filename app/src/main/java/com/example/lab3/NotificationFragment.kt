package com.example.lab3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.lab3.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {

    private val choiceModel : ChoiceModel by activityViewModels()
    lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        choiceModel.choice.observe(activity as LifecycleOwner, {
            if (choiceModel.choice.value?.size  == 2) {
                binding.textView4.text = choiceModel.choice.value
                    ?.joinToString(prefix = "You chose ", separator = " ", postfix = " phone.")
            } else { binding.textView4.text = "You've canceled your choice" }
        })

        binding.button7.setOnClickListener {
            val intent = Intent(context, StorageActivity::class.java).apply {}
            startActivity(intent)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = NotificationFragment()
    }
}