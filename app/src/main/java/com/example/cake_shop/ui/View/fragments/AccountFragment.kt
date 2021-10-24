import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cake_shop.databinding.FragmentAccountBinding
import com.example.cake_shop.ui.View.MainActivity
import java.sql.*

@Suppress("DEPRECATION")
class AccountFragment : Fragment() {
    private val ip = "192.168.0.242" //ip addressa na ktere bezi server
    private val port = "1300" // cislo portu
    private val database = "User_DB" //nazev Databaze
    private val us = "test" //prihlasovaci udaje do Sql Serveru
    private val pass = "1234"//prihlasovaci udaje do Sql Serveru
    private val url =
        "jdbc:jtds:sqlserver://$ip:$port/$database" //url pres ktery se prihlasuje do dbs nemeni se!!
    private var connRes: String? = null
    private var connection: Connection? = null

    private var email: String? = null
    private var password: String? = null

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

        val policy: StrictMode.ThreadPolicy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        try {//zkousi se pripojit do me dbs
            Class.forName("net.sourceforge.jtds.jdbc.Driver")//nemeni se
            connection = DriverManager.getConnection(url, us, pass)//zadava url a prihlasovaci udaje
            connRes = "SUCCESS" // nastavuje vysledek jako uspescny
        } catch (exception: ClassNotFoundException) {
            exception.printStackTrace()
            connRes = "ERROR"// nastavuje vysledek jako neuspesny
            Log.e("Error :", exception.message.toString())
        } catch (exception: SQLException) {
            exception.printStackTrace()
            connRes = "FAILURE"
            Log.e("Error :", exception.message.toString())
        }



        if (connRes == "SUCCESS") { //pokud se pripojeni k dbs zdarilo stane se podminka

            var statement: Statement? = null

            try {
                statement = connection!!.createStatement()
                val query1: ResultSet =
                    statement.executeQuery("Select email from EmailHolder where id = ('1')")
                if (query1.next()) {
                    val result: String = query1.getString(1)


                    var query2: ResultSet =
                        statement.executeQuery("Select email from Users where email = ('$result')")

                    if (query2.next()) {
                        binding.textView3.text = query2.getString(1)
                        query2 =
                            statement.executeQuery("Select name from Users where email = ('$result')")

                        if (query2.next()) {
                            binding.textView2.text = query2.getString(1)
                        }
                    }
                }

            } catch (e: SQLException) {
                e.printStackTrace()
            }
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