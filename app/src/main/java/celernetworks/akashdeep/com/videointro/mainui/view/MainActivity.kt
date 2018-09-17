package celernetworks.akashdeep.com.videointro.mainui.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import celernetworks.akashdeep.com.addFragment
import celernetworks.akashdeep.com.videointro.R

/**
 *  The main activity of the app. Currently the app supports one screen,but while it designing it
 *  was thought that the app could support multiple screen in next version.
 *  For example: if a user clicks on an image, some action is deemed to happen
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            addFragment(ClipsFragment.newInstance(), R.id.fragment_container)
        }
    }
}
