package com.sergeybogdanec.adidasstore

import android.app.Application
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


fun AndroidViewModel.getString(@StringRes resId: Int): String {
    return getApplication<Application>().getString(resId)
}

fun Fragment.createProgressDialog() = AlertDialog.Builder(requireContext())
    .setCancelable(false)
    .setView(View.inflate(requireContext(), R.layout.view_progress_bar, null))
    .create()
    .apply {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }


inline fun <T> Flow<T>.collectIn(
    scope: CoroutineScope,
    crossinline action: suspend (T) -> Unit
) = scope.launch {
    this@collectIn.collect(action)
}

@BindingAdapter("app:items")
fun setAdapterItems(view: RecyclerView, items: List<Item<*>>?) {
    val adapter: GroupAdapter<*> = (view.adapter?.takeIf { it is GroupAdapter } ?: run {
        GroupAdapter<GroupieViewHolder>().also(view::setAdapter)
    }) as GroupAdapter<*>
    adapter.update(items.orEmpty())
}
