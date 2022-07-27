package com.meu.myrepositories.iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import com.meu.myrepositories.R
import com.meu.myrepositories.core.createDialog
import com.meu.myrepositories.core.createProgressDialog
import com.meu.myrepositories.core.hideSoftKeyboard
import com.meu.myrepositories.data.di.DataModule
import com.meu.myrepositories.data.model.Repo
import com.meu.myrepositories.data.repositories.RepoRepository
import com.meu.myrepositories.databinding.ActivityMainBinding
import com.meu.myrepositories.presentation.MainViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy { RepoListAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        binding.rvRepositories.adapter = adapter


        viewModel.repos.observe(this){
            when(it){
                is MainViewModel.State.Error -> {
                    dialog.dismiss()
                    //Caso não encontre um usuário.
                    createDialog { setMessage(it.error.message) }.show()
                }
                MainViewModel.State.Loading -> { dialog.show() }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                    binding.includedEmpty.gif.visibility = View.GONE
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepoList(it) }
        //Fecha o telhado dps do clique em buscar.
        binding.root.hideSoftKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e("TAG", "onQueryTextChange: $newText")
        return false
    }
}