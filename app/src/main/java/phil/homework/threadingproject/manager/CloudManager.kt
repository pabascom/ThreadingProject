package phil.homework.threadingproject.manager

import android.annotation.SuppressLint
import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import phil.homework.threadingproject.util.toast

@SuppressLint("StaticFieldLeak")
object CloudManager {

    lateinit var context: Context

    val database = FirebaseDatabase.getInstance()
    val simpleDataReference = database.getReference("simpleData")

    fun saveFirebaseData(value: String){

        simpleDataReference.setValue(value)

    }

    fun readFirebaseData(){
        simpleDataReference.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                context.toast(snapshot.getValue(String::class.java))
            }

        })
    }

}