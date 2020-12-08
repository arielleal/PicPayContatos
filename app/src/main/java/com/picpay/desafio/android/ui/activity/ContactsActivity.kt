package com.picpay.desafio.android.ui.activity

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.viewModel.ContactsViewModel
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val progressBar: ProgressBar by lazy { findViewById<ProgressBar>(R.id.user_list_progress_bar) }
    private val adapter: UserListAdapter by lazy { UserListAdapter() }
    private val contactsViewModel: ContactsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureAdapter()

        progressBar.isVisible = true

        contactsViewModel.loadUsers()

        observeFillUser()

        observeErrorMessage()
    }

    private fun observeFillUser() {
        contactsViewModel.usersSuccess.observe(this, Observer {
            progressBar.isVisible = false
            fillUserAdapter(contactsViewModel.usersSuccess.value as List<User>)
        })
    }

    private fun observeErrorMessage() {
        contactsViewModel.errorMessage.observe(this, Observer {
            progressBar.isVisible = true
            recyclerView.isVisible = true
            showErrorMensager(contactsViewModel.errorMessage.value.toString())
        })
    }

    private fun configureAdapter() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fillUserAdapter(user: List<User>) { adapter.users = user }

    private fun showErrorMensager(msgError: String){
        val error = getString(R.string.error, msgError)
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
