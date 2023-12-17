package com.example.mvvmnewsapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.databinding.ItemArticlePreviewBinding
import com.example.mvvmnewsapp.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    val TAG = "NewsAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
//        val binding = ItemArticlePreviewBinding
//            .inflate(LayoutInflater.from(parent.context),
//                parent,
//                false)
//        return ArticleViewHolder(binding)
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            article.urlToImage?.let {
                Glide.with(this).load(article.urlToImage).into(holder.ivArticleImage)
            }

            holder.tvSource.text = article.source!!.name
            holder.tvTitle.text = article.title
            holder.tvDescription.text = article.description
            holder.tvPublishedAt.text = article.publishedAt
            Log.d(TAG, "onBindViewHolder: listener")
//            setOnItemClickListener {
////                onItemClickListener?.let{it(article)}
//                onItemClickListener?.invoke(article)
//                if(onItemClickListener == null){
//                    Log.d(TAG, "onBindViewHolder: null listener")
//                } else {
//                    Log.d(TAG, "onBindViewHolder: not null")
//                }
//
//            }
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(article)
//            if(onItemClickListener == null){
//                Log.d(TAG, "onBindViewHolder: null listener")
//            } else {
//                Log.d(TAG, "onBindViewHolder: not null")
//            }
        }
    }



    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvSource: TextView = itemView.findViewById(R.id.tvSource)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvPublishedAt: TextView = itemView.findViewById(R.id.tvPublishedAt)
        val ivArticleImage: AppCompatImageView = itemView.findViewById(R.id.ivArticleImage)
    }

//    inner class ArticleViewHolder(val binding: ItemArticlePreviewBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


}