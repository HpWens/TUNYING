package com.tunyin.ui.fragment.mine

import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.tunyin.BaseInjectFragment
import com.tunyin.PersonalActivity
import com.tunyin.R
import com.tunyin.SettingActivity
import com.tunyin.mvp.contract.UploadFileContract
import com.tunyin.mvp.model.SelfBean
import com.tunyin.mvp.model.UploadFileEntity
import com.tunyin.mvp.presenter.UploadFilePresenter
import com.tunyin.ui.activity.MyRankActivity
import com.tunyin.ui.activity.ServiceActivity
import com.tunyin.ui.activity.mine.*
import com.tunyin.utils.ImagePickHelper
import com.tunyin.utils.ImageUtil
import com.tunyin.utils.PermissionHelper
import com.tunyin.utils.UriUtil
import com.zhihu.matisse.Matisse
import kotlinx.android.synthetic.main.fragment_mine.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


/**
 * 我的
 */
class MineFragment : BaseInjectFragment<UploadFilePresenter>(), UploadFileContract.View, View.OnClickListener, PermissionHelper.OnRequestPermissionResult {


    override fun getLayoutId(): Int = R.layout.fragment_mine

    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }

    override fun initPresenter() = mPresenter.attachView(this)

    override fun initInject() = fragmentComponent.inject(this)

    override fun uploadFileSuc(uploadFileEntity: UploadFileEntity) {
        SelfBean.instance.headUrl = uploadFileEntity.url
        ImageUtil.load(uploadFileEntity.url).isCircle.on(iv_avatar)

        mPresenter.updateHeader(uploadFileEntity.url)
    }

    override fun onResult(isSuccess: Boolean) {
    }

    override fun initWidget() {
        super.initWidget()
        ly_wallet.setOnClickListener(this)
        ly_order.setOnClickListener(this)
        rl_start_vip.setOnClickListener(this)
        rl_vip_grade.setOnClickListener(this)
        rl_voucher.setOnClickListener(this)
        rl_collect.setOnClickListener(this)
        rl_msg.setOnClickListener(this)
        rl_customer_service.setOnClickListener(this)
        iv_avatar.setOnClickListener(this)
        rl_setting.setOnClickListener(this)
        fl_personal_info.setOnClickListener(this)
        requestPermission()

//        https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573299081266&di=3539ee4863f614576acd82dec4b0bcb7&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201605%2F09%2F20160509144239_xSTPX.thumb.700_0.jpeg
        ImageUtil.load(SelfBean.instance.headUrl).isCircle.on(iv_avatar)
        if (TextUtils.isEmpty(SelfBean.instance.headImg))
            tv_nickname.text = if (TextUtils.isEmpty(SelfBean.instance.nickName)) "无昵称" else SelfBean.instance.nickName

    }

    private fun requestPermission() {
        val helper = PermissionHelper()
        helper.requestSDAndCameraPermissions(mContext as FragmentActivity?, this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == ImagePickHelper.REQUEST_PICK_IMAGE) {
        if (data == null) return
        val pickPhotoList = Matisse.obtainResult(data)
        if (pickPhotoList == null || pickPhotoList.size == 0) return

        val path = UriUtil.getPathFromUri(pickPhotoList[0])
        var file = File(path)

        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)

        val filePart = MultipartBody.Part.createFormData("file", file.name, requestFile)
        mPresenter.uploadFile(filePart)
//        }
    }


    override fun onClick(p0: View?) {
        when (p0) {
            ly_wallet -> {
                val intent = Intent(activity, MyWalletActivity::class.java)
                activity?.startActivity(intent)
            }

            ly_order -> {
                val intent = Intent(activity, MyOrderActivity::class.java)
                activity?.startActivity(intent)
            }

            rl_start_vip -> {

            }
            rl_vip_grade -> {
//                val intent = Intent(activity, TunYinVIPDepositActivity::class.java)
//                activity?.startActivity(intent)

                val intent = Intent(activity, MyRankActivity::class.java)
                activity?.startActivity(intent)

            }
            rl_voucher -> {
                val intent = Intent(activity, MyVoucherActivity::class.java)
                activity?.startActivity(intent)

            }
            rl_collect -> {
                val intent = Intent(activity, MyCollectActivity::class.java)
                activity?.startActivity(intent)

            }
            rl_msg -> {
                val intent = Intent(activity, MyMsgActivity2::class.java)
                activity?.startActivity(intent)

            }
            rl_customer_service -> {
                val intent = Intent(activity, ServiceActivity::class.java)
                activity?.startActivity(intent)
                // ToastUtils.showToast("暂时没有客服哦")
            }

            iv_avatar -> {
                ImagePickHelper.with(this).pickMulPicture(1)
            }
            rl_setting -> {
//                if (activity is MainActivity) {
////                    (activity as MainActivity).switchDrawer();
////                }

                startActivity(Intent(activity, SettingActivity::class.java))

            }

            fl_personal_info -> {
                startActivity(Intent(activity, PersonalActivity::class.java))
            }
        }
    }

}