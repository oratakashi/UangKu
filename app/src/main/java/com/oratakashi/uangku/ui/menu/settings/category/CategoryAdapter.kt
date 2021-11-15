package com.oratakashi.uangku.ui.menu.settings.category

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.AdapterCategoryBinding
import com.oratakashi.uangku.domain.model.Category
import com.oratakashi.uangku.utils.enums.ActivityType
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick

class CategoryAdapter(
    private val onClick : (Category) -> Unit,
    private val onDelete : (Category) -> Unit
) : PagingDataAdapter<Category, ViewHolder<AdapterCategoryBinding>>(
    DIFF_CALLBACK
) {

    override fun onBindViewHolder(holder: ViewHolder<AdapterCategoryBinding>, position: Int) {
        with(holder.binding) {
            getItem(position)?.let {
                when(it.type){
                    ActivityType.INCOME.value   -> containerBackground.setBackgroundColor(
                        ContextCompat.getColor(root.context, R.color.green50)
                    )
                    ActivityType.EXPANSE.value  -> containerBackground.setBackgroundColor(
                        ContextCompat.getColor(root.context, R.color.red50)
                    )
                }
                tvName.text = it.name
                ivDelete.onClick { onDelete.invoke(it) }
                root.onClick { onClick.invoke(it) }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterCategoryBinding> = viewBinding(parent)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}