package com.example.cake_shop.ui.View.Fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cake_shop.databinding.ActivityLogginBinding
import com.example.cake_shop.databinding.FragmentAccountBinding
import com.example.cake_shop.ui.View.LogginActivity
import com.example.cake_shop.ui.View.MainActivity
import com.vishnusivadas.advanced_httpurlconnection.PutData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var logginBinding : ActivityLogginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, com.example.cake_shop.R.layout.fragment_account, container, false
        )
        val view = binding.root

        val mainActivityIntent : Intent =
            Intent(activity, MainActivity::class.java)
        val logginIntent: Intent =
            Intent(activity, LogginActivity::class.java)

        binding.btnSignOut.setOnClickListener {
            Toast.makeText(activity, "You've been logged out", Toast.LENGTH_SHORT).show()
            startActivity(mainActivityIntent)
        }

        binding.textView2.text = logginIntent.getStringExtra("email").toString()

        binding.btnEditProfilePicture.setOnClickListener {
            val galleryIntent: Intent = Intent()
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT)
            galleryIntent.type = "image/*"
            startActivityForResult(Intent.createChooser(galleryIntent, "Pick an image"), 123)
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            val imageData: Uri? = data.data
            binding.imProfilePicture.setImageURI(imageData)
        }
    }
}


