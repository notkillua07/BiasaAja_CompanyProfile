package com.example.biasaaja_companyprofile.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.biasaaja_companyprofile.R
import com.example.biasaaja_companyprofile.databinding.FragmentSchDetailBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 * Use the [SchDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SchDetailFragment : Fragment() {
    private lateinit var binding: FragmentSchDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name =arguments?.getString("name")
        val location =arguments?.getString("location")
        val team =arguments?.getString("team")
        val desc =arguments?.getString("desc")
        val image =arguments?.getString("image")


        val picasso = Picasso.Builder(view.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(image).into(binding.imgGame)

        picasso.build().load(image)
            .into(binding.imgGame, object: Callback {
                override fun onSuccess() {
                    binding.progressBar2.visibility = View.INVISIBLE
                    binding.imgGame.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }
            })

        binding.txtName.text = name
        binding.txtLocation.text = location
        binding.txtTeam.text = team
        binding.txtDes.text = desc

        binding.btnNotif.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Notification created.")
            builder.setMessage("Would you like to proceed?")

            builder.setPositiveButton("Yes") { dialog, which ->
                MainActivity.showNotification(
                    name.toString(), "You will get a notification about this event",
                    R.drawable.ic_launcher_foreground
                )
            }

            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }
}