package com.example.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mvvmnewsapp.NewsViewModel
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.databinding.FragmentArticleBinding
import com.example.mvvmnewsapp.ui.activities.NewsActivity
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    private var _binding: FragmentArticleBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val TAG = "ArticleFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            Log.d(TAG, "onViewCreated: ${article.url}")
            loadUrl(article.url!!)
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Toast.makeText(context, "Article saved successfully", Toast.LENGTH_SHORT).show()
        }
    }

}