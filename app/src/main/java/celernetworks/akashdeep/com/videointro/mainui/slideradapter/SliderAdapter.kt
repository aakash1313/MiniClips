package celernetworks.akashdeep.com.videointro.mainui.slideradapter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.widget.RelativeLayout
import celernetworks.akashdeep.com.videointro.R
import kotlinx.android.synthetic.main.slider_item.view.*
import android.graphics.SurfaceTexture
import android.view.*
import android.media.MediaPlayer
import android.os.Environment
import android.support.constraint.ConstraintLayout
import celernetworks.akashdeep.com.utils.DOWNLOAD_VIDEO_DIRECTORY
import celernetworks.akashdeep.com.videointro.mainui.roomdatabase.entity.MiniClipEntity
import celernetworks.akashdeep.com.videointro.mainui.viewmodels.MiniClipsViewModel
import java.io.File
import kotlin.collections.ArrayList

/**
 * This is the adapter which would be observing the view model object clips. As soon as the data is
 * updates it reflects it on the view.
 */
class SliderAdapter : PagerAdapter{

    private var context: Context
    lateinit var inflater: LayoutInflater
    private var dataList:ArrayList<MiniClipEntity>
    private val dataName:Array<String>
    val mMediaPlayer:MediaPlayer


    constructor(context: Context,viewModel: MiniClipsViewModel,lifeCycleOwner:LifecycleOwner):super(){
        this.context = context
        dataList = ArrayList()
        this.mMediaPlayer = MediaPlayer()
        dataName = context.resources.getStringArray(R.array.video_titles)
        val miniClipsDataObserver: Observer<List<MiniClipEntity>> = Observer { data ->
            if (data != null) {
                dataList.clear()
                dataList.addAll(data)
                notifyDataSetChanged() // TODO: Use DiffUtil when we have auto value models
            }
        }
        viewModel.getPics().observe(lifeCycleOwner,miniClipsDataObserver)
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view == `object` as ConstraintLayout

    override fun getCount(): Int = dataList.size

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
       inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
       val view:View = inflater.inflate(R.layout.slider_item,container,false)
        view.video_title.text = dataName[position]
        val layout:TextureView = view.videoTexture
        val clip:MiniClipEntity = dataList.get(position)
        layout.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture?, p1: Int, p2: Int) {
            }
            override fun onSurfaceTextureUpdated(p0: SurfaceTexture?) {
            }

            override fun onSurfaceTextureDestroyed(p0: SurfaceTexture?): Boolean {
                return true
            }

            override fun onSurfaceTextureAvailable(surfaceTexture: SurfaceTexture, width: Int, height: Int) {
                    val surface = Surface(surfaceTexture)

                try {
                    mMediaPlayer.reset()
                    mMediaPlayer.setSurface(surface)
                    mMediaPlayer.setDataSource(clip.videoUrl)
                    mMediaPlayer.prepareAsync()
                    mMediaPlayer.setOnPreparedListener {
                        it.start()
                        it.isLooping = true
                    }

                }
                catch (ex:Exception){

                }
            }
        }
        container?.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(`object` as ConstraintLayout)
    }
}