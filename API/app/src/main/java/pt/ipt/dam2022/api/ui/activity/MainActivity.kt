package pt.ipt.dam2022.api.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import pt.ipt.dam2022.api.R
import pt.ipt.dam2022.api.model.APIResult
import pt.ipt.dam2022.api.model.Note
import pt.ipt.dam2022.api.retrofit.RetrofitInitializer
import pt.ipt.dam2022.api.ui.adapter.NoteListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ask Retrofit to read data from API, and list Notes on screen
        listNotes()

        // add reference to button that we are going to use
        // to add some random data to API
        val btNewNote = findViewById<Button>(R.id.bt_new_note)
        btNewNote.setOnClickListener {
            // define the task that app will do when a user click the button
            addNewNote()
        }
    }

    /**
     * create a random Note
     */
    private fun addNewNote() {

        var i = Random(GregorianCalendar.getInstance().timeInMillis).nextInt(100)
        var note = Note("Note $i", "Description of Note " + i)

        addNote(note) {
            Toast.makeText(
                    this, "new Note added" + it?.description, Toast.LENGTH_SHORT
            ).show()
            // refresh the list of Notes on screen
            listNotes()
        }
    }

    /**
     * add the random Note to the API
     */
    private fun addNote(note: Note, onResult: (APIResult?) -> Unit) {
        // prepare Retrofit to add the Note
        val call = RetrofitInitializer().noteService().addNote(note)

        // Tell Retrofit to do the work
        call.enqueue(object :Callback<APIResult>{
            override fun onFailure(call: Call<APIResult>, t: Throwable) {
                t.printStackTrace()
                onResult(null)
            }
            override fun onResponse(call: Call<APIResult>,
                                    response: Response<APIResult>) {
                val addedNote=response.body()
                onResult(addedNote)
            }
        })
    }


    private fun listNotes() {
        // ask retrofit to read the API data
        val call = RetrofitInitializer().noteService().list()
        // use data read
        call.enqueue(object : Callback<List<Note>?> {
            override fun onResponse(
                    call: Call<List<Note>?>?, response: Response<List<Note>?>?
            ) {
                response?.body()?.let {
                    val notes: List<Note> = it
                    // takes the data read from API
                    // and formats that data to the interface
                    configureList(notes)
                }
            }

            override fun onFailure(call: Call<List<Note>?>?, t: Throwable?) {
                t?.message?.let { Log.e("I can not read data...", it) }
            }
        })
    }

    /**
     * configure each 'fragment' to present data
     */
    private fun configureList(notes: List<Note>) {
        val recyclerView = findViewById<RecyclerView>(R.id.nodeListRecyclerView)

        recyclerView.adapter = NoteListAdapter(notes, this)

        val layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
        )

        recyclerView.layoutManager = layoutManager
    }
}



