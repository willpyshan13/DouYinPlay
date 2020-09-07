package com.will.play.pick.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.will.habit.base.BaseActivity
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.ActivityPickCollectionVideoBinding
import com.will.play.base.entity.PickDouyinEntity
import com.will.play.pick.entity.VideoLists
import com.will.play.pick.ui.dialog.PickCollectVideoFragment
import com.will.play.pick.ui.viewmodel.PickCollectionVideoViewModel
import com.will.play.third.DouyinLogin

/**
 * Desc:视频领取
 *
 * Date: 2020-07-01
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
@Route(path = "/pick/collectvideo")
class PickCollectionVideoActivity : BaseActivity<ActivityPickCollectionVideoBinding, PickCollectionVideoViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_pick_collection_video
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.douyinLogin.observe(this, Observer {
            DouyinLogin.login(this)
        })

        DouyinLogin.authSuccess.observe(this, Observer {
            if (it!=null) {
                viewModel.getDouyinUserinfo(it)
            }
        })

        viewModel.showCollectVideo.observe(this, Observer {
            if (it!=null&&viewModel.showCollectVideoList!=null) {
                showVideoDialog(it,viewModel.showCollectVideoList!!)
            }
        })
    }

    private fun showVideoDialog(data: VideoLists,showCollectVideoList: PickDouyinEntity) {
        PickCollectVideoFragment(data,showCollectVideoList).show(supportFragmentManager, "video")
    }


}