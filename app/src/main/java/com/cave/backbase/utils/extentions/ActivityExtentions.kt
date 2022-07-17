package com.cave.backbase.utils.extentions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cave.backbase.R

fun AppCompatActivity.showFragment(
    fragment: Fragment?,
    resourceId: Int = R.id.frameLayout_main,
    addToBackStack: Boolean = false
) {
    fragment?.let {
        supportFragmentManager.executePendingTransactions()
        supportFragmentManager.beginTransaction().apply {
            if (fragment.isAdded) {
                setReorderingAllowed(false)
                show(fragment)
            } else {
                setReorderingAllowed(false)
                add(resourceId, fragment, fragment::class.java.name)
                if (addToBackStack) {
                    val old = supportFragmentManager.fragments.find { it.tag == fragment.tag }
                    if (old != null) {
                        remove(old)
                    }
                    addToBackStack(fragment::class.java.name)
                }
            }
        }.commitAllowingStateLoss()
    }
}
