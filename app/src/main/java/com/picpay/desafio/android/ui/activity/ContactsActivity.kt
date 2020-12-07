package com.picpay.desafio.android.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.viewModel.ContactsViewModel
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private val contactsViewModel: ContactsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureAdapter()

        progressBar.visibility = View.GONE
        contactsViewModel.loadUsers()

        observeFillUser()
        observeErrorMessage()
    }

    override fun onResume() {
        super.onResume()
        progressBar.visibility = View.VISIBLE
        contactsViewModel.loadUsers()
    }

    private fun observeFillUser() {
        contactsViewModel.users.observe(this, Observer {
            progressBar.visibility = View.GONE
            fillUserAdapter(contactsViewModel.users.value as List<User>)
        })
    }

    private fun observeErrorMessage() {
        contactsViewModel.errorMessage.observe(this, Observer {
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.GONE
            showErrorMensager(contactsViewModel.errorMessage.value.toString())
        })
    }

    private fun configureAdapter() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fillUserAdapter(user: List<User>){ adapter.users = user }

    private fun showErrorMensager(msgError: String){
        val error = getString(R.string.error)
        Toast.makeText(this, error + " - " + msgError, Toast.LENGTH_LONG).show()
    }
}
