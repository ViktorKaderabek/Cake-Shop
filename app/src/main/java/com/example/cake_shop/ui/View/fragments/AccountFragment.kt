import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cake_shop.databinding.FragmentAccountBinding
import com.example.cake_shop.ui.View.MainActivity

@Suppress("DEPRECATION")
class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, com.example.cake_shop.R.layout.fragment_account, container, false
        )
        val view = binding.root

        binding.btnSignOut.setOnClickListener {

            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)

            Toast.makeText(activity, "You've been logged out", Toast.LENGTH_SHORT).show()
        }

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