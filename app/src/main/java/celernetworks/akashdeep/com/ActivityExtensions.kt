package celernetworks.akashdeep.com

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * This is an extension class to perform fragment transactions using the kotlin way.
 * Advantage: This reduces the pain of boilerplate code and debugging if we miss anything in the
 * transaction.
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}
fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction{replace(frameId, fragment)}
}