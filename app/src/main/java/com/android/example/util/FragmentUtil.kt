package com.android.example.util

import androidx.annotation.IntDef
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
class FragmentUtil {

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(
        NONE_CUSTOM, FULL_IN_OUT, SLIGHTLY_OUT
    )
    annotation class AnimationType

    companion object {

        const val NONE_CUSTOM = 0
        const val FULL_IN_OUT = 1
        const val SLIGHTLY_OUT = 2
    }
}

inline fun <reified T : Fragment> AppCompatActivity.replaceFragment(
    viewId: Int,
    fragment: T,
    tag: String? = null,
    addToBackStack: Boolean = false,
) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(viewId, fragment, tag)
    if (addToBackStack) {
        transaction.addToBackStack(if (tag.isNullOrEmpty()) tag else fragment.getSimpleName())
    }
    transaction.commit()
}

inline fun <reified T : Fragment> T.getSimpleName(): String {
    return javaClass.simpleName
}