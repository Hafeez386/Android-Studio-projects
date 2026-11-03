package com.example.oel1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.oel1.model.Student
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StudentListActivity : AppCompatActivity() {

    private val students = ArrayList<Student>()
    private val titles = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var tvEmpty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val listView = findViewById<ListView>(R.id.lvStudents)
        tvEmpty = findViewById(R.id.tvEmpty)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)
        listView.adapter = adapter


        listView.setOnItemClickListener { _, _, position, _ ->
            val s = students[position]
            val intent = Intent(this, StudentDetailActivity::class.java)
            intent.putExtra("name", s.name)
            intent.putExtra("regNo", s.regNo)
            intent.putExtra("email", s.email)
            intent.putExtra("gender", s.gender)
            intent.putExtra("department", s.department)
            startActivity(intent)
        }

        FirebaseDatabase.getInstance().reference.child("Students")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    students.clear()
                    titles.clear()
                    for (child in snapshot.children) {
                        val s = child.getValue(Student::class.java)
                        if (s != null) {
                            students.add(s)
                            titles.add("${s.name}  (${s.regNo})")
                        }
                    }
                    adapter.notifyDataSetChanged()
                    tvEmpty.visibility = if (students.isEmpty()) View.VISIBLE else View.GONE
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@StudentListActivity, error.message, Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}