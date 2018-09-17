package celernetworks.akashdeep.com.videointro.mainui.view

import android.arch.lifecycle.ViewModelProviders
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import celernetworks.akashdeep.com.videointro.R
import celernetworks.akashdeep.com.videointro.mainui.slideradapter.SliderAdapter
import celernetworks.akashdeep.com.videointro.mainui.viewmodels.MiniClipsViewModel
import kotlinx.android.synthetic.main.fragment_clips.view.*


/**
 * The is the fragment which contains the video/clips fetched from the view model
 */
class ClipsFragment : Fragment() {

    companion object {
        fun newInstance(): ClipsFragment {
            return ClipsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_clips, container,
                false)
        val activity = activity
        val miniClipViewModel = ViewModelProviders.of(this).get(MiniClipsViewModel::class.java)
        val adapter = SliderAdapter(activity, viewModel = miniClipViewModel, lifeCycleOwner = this)
        view.viewpager_videos.adapter = adapter
        view.viewpager_videos.currentItem = 1
        return view
    }


}