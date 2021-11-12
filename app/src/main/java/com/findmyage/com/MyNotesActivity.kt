package com.findmyage.com

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_my_notes.*
import kotlinx.android.synthetic.main.activity_ticcket.view.*

class MyNotesActivity: AppCompatActivity() {
    var listNotes=ArrayList<MyNotes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
      /*  listNotes.add(MyNotes(1,"professer","The fist parameter in super adds support for dragging the RecyclerView item up or down. We don’t care about that hence the 0"))
        listNotes.add(MyNotes(2,"doctor","The fist parameter in super adds support for dragging the RecyclerView item up or down. We don’t care about that hence the 0"))
        listNotes.add(MyNotes(3,"student","The fist parameter in super adds support for dragging the RecyclerView item up or down. We don’t care about that hence the 0"))
*/

        //load from db
        loadQuery("%")

    }

    fun loadQuery(title:String){
        var  dbManager=DbManager(this)
        val projection= arrayOf("ID","Title","Description")
        val selectionArgs= arrayOf(title)
        val cursor=dbManager.query(projection,"Title Like ?",selectionArgs,"Title" )
        listNotes.clear()
        if (cursor.moveToFirst()){
            do {
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val title=cursor.getString(cursor.getColumnIndex("Title"))
                val des=cursor.getString(cursor.getColumnIndex("Description"))
                listNotes.add(MyNotes(ID,title,des))
            }while (cursor.moveToNext())
        }
        var notesAdapter=myNotesAdapter(this,listNotes)
        listViewNotes.adapter=notesAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val sv: SearchView=menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val sm=getSystemService(Context.SEARCH_SERVICE)as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext,query,Toast.LENGTH_LONG).show()
                loadQuery("%$query%")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item!=null){
            when(item.itemId){
                R.id.addNotes->{
                    var intent=Intent(this,AddNotesActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    inner class myNotesAdapter:BaseAdapter{
        var listNotesAdapter=ArrayList<MyNotes>()
        var context:Context?=null
        constructor(context:Context, listNotesAdpater:ArrayList<MyNotes>):super(){
            this.listNotesAdapter=listNotesAdpater
            this.context=context
        }

        override fun getCount(): Int {
            return listNotesAdapter.size
        }

        override fun getItem(position: Int): Any {
            return listNotesAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView=layoutInflater.inflate(R.layout.activity_ticcket,null)
            var myNode=listNotesAdapter[position]
            myView.tvTitle.text=myNode.nodeName
            myView.tvContext.text=myNode.nodeDes
            myView.btnDelete.setOnClickListener{
                var dbManager=DbManager(this.context!!)
                val selectionArgs= arrayOf(myNode.nodeID.toString())
                dbManager.Delete("ID=?",selectionArgs)
            }
            myView.btnEdit.setOnClickListener{

                GoToUpdate(myNode)

            }
            return myView

            /**/
        }
        fun GoToUpdate(note:MyNotes){
            var intent=Intent(this@MyNotesActivity,AddNotesActivity::class.java)
            intent.putExtra("ID",note.nodeID)
            intent.putExtra("name",note.nodeName)
            intent.putExtra("des",note.nodeDes)
            startActivity(intent)
        }



    }
    override  fun onResume() {
        super.onResume()
        loadQuery("%")
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"onStart",Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()

        Toast.makeText(this,"onPause",Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()

        Toast.makeText(this,"onStop",Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this,"onDestroy",Toast.LENGTH_LONG).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,"onRestart",Toast.LENGTH_LONG).show()
    }

}