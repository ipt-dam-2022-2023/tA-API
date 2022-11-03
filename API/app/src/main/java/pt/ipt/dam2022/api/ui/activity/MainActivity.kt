package pt.ipt.dam2022.api.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import pt.ipt.dam2022.api.Model.Note
import pt.ipt.dam2022.api.R
import pt.ipt.dam2022.api.retrofit.RetrofitInitializer
import pt.ipt.dam2022.api.ui.adapter.NoteListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ask retrofit to read the API data
        val call = RetrofitInitializer().noteService().list()
        // use data readed
        call.enqueue(object : Callback<List<Note>?> {
            override fun onResponse(
                    call: Call<List<Note>?>?, response: Response<List<Note>?>?
            ) {
                response?.body()?.let {
                    val notes: List<Note> = it
                    // takes the data readed from API
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
        val reciclerView=findViewById<RecyclerView>(R.id.nodeListRecicleView)

        reciclerView.adapter= NoteListAdapter(notes, this)

        val layoutManager=StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
        )

        reciclerView.layoutManager=layoutManager

    }
}



