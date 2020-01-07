package com.tunyin.ui.fragment.index

import android.os.Bundle
import android.text.Html
import com.tunyin.R
import com.tunyin.base.BaseRefreshFragment
import com.tunyin.mvp.contract.index.PlayDetailSingleContract
import com.tunyin.mvp.model.Event
import com.tunyin.mvp.model.index.PalyDetailSingleEntity
import com.tunyin.mvp.presenter.index.PlayerDetailSinglePresenter
import com.tunyin.utils.MImageGetter
import kotlinx.android.synthetic.main.fragment_player_detail.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 播放——详情
 */
class PlayerDetailFragment : BaseRefreshFragment<PlayerDetailSinglePresenter, String>(), PlayDetailSingleContract.View {


    private var musicId: String? = null
    override fun initPresenter() = mPresenter.attachView(this)

    override fun initInject() = fragmentComponent.inject(this)

    override fun getMusicDetialSingleData(palyDetailSingleEntity: PalyDetailSingleEntity) {

        tv_detail.text = Html.fromHtml(palyDetailSingleEntity.detail, MImageGetter(tv_detail, mContext), null)

        // showDetailContent(palyDetailSingleEntity.detail)

//        tv_detail.text = "加拿大（英语/法语：Canada），位于北美洲最北端，英联邦国家之一，素有“枫叶之国”的美誉，首都是渥太华。 [1] \n" +
//                "加拿大西抵太平洋，东迄大西洋，北至北冰洋，东北部和丹麦领地格陵兰岛相望，东部和法属圣皮埃尔和密克隆群岛相望，南方与美国本土接壤，西北方与美国阿拉斯加州为邻。领土面积为998.467万平方公里，位居世界第二，人口主要集中在南部五大湖沿岸。著名城市有多伦多、温哥华等。官方语言有英语和法语两种，是典型的双语国家。 [1] \n" +
//                "加拿大政治体制为联邦制和议会制君主立宪制，英国女王伊丽莎白二世为国家元首及国家象征，并任命女王总督派驻。\n" +
//                "加拿大原为印第安人与因纽特人的居住地。16世纪后，英国和法国殖民者先后侵入；1763年沦为英国殖民地。 [1]  1867年成为英国自治领。1926年英国承认其\"平等地位\"，获得外交独立权。1931年成为英联邦成员国，其议会也获得了同英国议会同等的立法权，1982年，英国女王签署《加拿大宪法法案》，加拿大议会获得立宪、修宪的全部权利。 [1] \n" +
//                "加拿大是一个高度发达的资本主义国家，得益于丰富的自然资源和高度发达的科技，使其成为世界上拥有最高生活水准、社会最富裕、经济最发达的国家之一。加拿大为继乌拉圭之后第二个大麻合法化的国家。 [2]  也是世界上最大的钻石生产国之一。 [3]  加拿大在教育、政府的透明度、社会自由度、生活品质及经济自由的国际排名都名列前茅。同时，加拿大也是八国集团、20国集团、北约、联合国、法语国家组织、世界贸易组织等国际组织的成员国加拿大（英语/法语：Canada），位于北美洲最北端，英联邦国家之一，素有“枫叶之国”的美誉，首都是渥太华。 [1] \n" +
//                "加拿大西抵太平洋，东迄大西洋，北至北冰洋，东北部和丹麦领地格陵兰岛相望，东部和法属圣皮埃尔和密克隆群岛相望，南方与美国本土接壤，西北方与美国阿拉斯加州为邻。领土面积为998.467万平方公里，位居世界第二，人口主要集中在南部五大湖沿岸。著名城市有多伦多、温哥华等。官方语言有英语和法语两种，是典型的双语国家。 [1] \n" +
//                "加拿大政治体制为联邦制和议会制君主立宪制，英国女王伊丽莎白二世为国家元首及国家象征，并任命女王总督派驻。\n" +
//                "加拿大原为印第安人与因纽特人的居住地。16世纪后，英国和法国殖民者先后侵入；1763年沦为英国殖民地。 [1]  1867年成为英国自治领。1926年英国承认其\"平等地位\"，获得外交独立权。1931年成为英联邦成员国，其议会也获得了同英国议会同等的立法权，1982年，英国女王签署《加拿大宪法法案》，加拿大议会获得立宪、修宪的全部权利。 [1] \n" +
//                "加拿大是一个高度发达的资本主义国家，得益于丰富的自然资源和高度发达的科技，使其成为世界上拥有最高生活水准、社会最富裕、经济最发达的国家之一。加拿大为继乌拉圭之后第二个大麻合法化的国家。 [2]  也是世界上最大的钻石生产国之一。 [3]  加拿大在教育、政府的透明度、社会自由度、生活品质及经济自由的国际排名都名列前茅。同时，加拿大也是八国集团、20国集团、北约、联合国、法语国家组织、世界贸易组织等国际组织的成员国加拿大（英语/法语：Canada），位于北美洲最北端，英联邦国家之一，素有“枫叶之国”的美誉，首都是渥太华。 [1] \n" +
//                "加拿大西抵太平洋，东迄大西洋，北至北冰洋，东北部和丹麦领地格陵兰岛相望，东部和法属圣皮埃尔和密克隆群岛相望，南方与美国本土接壤，西北方与美国阿拉斯加州为邻。领土面积为998.467万平方公里，位居世界第二，人口主要集中在南部五大湖沿岸。著名城市有多伦多、温哥华等。官方语言有英语和法语两种，是典型的双语国家。 [1] \n" +
//                "加拿大政治体制为联邦制和议会制君主立宪制，英国女王伊丽莎白二世为国家元首及国家象征，并任命女王总督派驻。\n" +
//                "加拿大原为印第安人与因纽特人的居住地。16世纪后，英国和法国殖民者先后侵入；1763年沦为英国殖民地。 [1]  1867年成为英国自治领。1926年英国承认其\"平等地位\"，获得外交独立权。1931年成为英联邦成员国，其议会也获得了同英国议会同等的立法权，1982年，英国女王签署《加拿大宪法法案》，加拿大议会获得立宪、修宪的全部权利。 [1] \n" +
//                "加拿大是一个高度发达的资本主义国家，得益于丰富的自然资源和高度发达的科技，使其成为世界上拥有最高生活水准、社会最富裕、经济最发达的国家之一。加拿大为继乌拉圭之后第二个大麻合法化的国家。 [2]  也是世界上最大的钻石生产国之一。 [3]  加拿大在教育、政府的透明度、社会自由度、生活品质及经济自由的国际排名都名列前茅。同时，加拿大也是八国集团、20国集团、北约、联合国、法语国家组织、世界贸易组织等国际组织的成员国加拿大（英语/法语：Canada），位于北美洲最北端，英联邦国家之一，素有“枫叶之国”的美誉，首都是渥太华。 [1] \n" +
//                "加拿大西抵太平洋，东迄大西洋，北至北冰洋，东北部和丹麦领地格陵兰岛相望，东部和法属圣皮埃尔和密克隆群岛相望，南方与美国本土接壤，西北方与美国阿拉斯加州为邻。领土面积为998.467万平方公里，位居世界第二，人口主要集中在南部五大湖沿岸。著名城市有多伦多、温哥华等。官方语言有英语和法语两种，是典型的双语国家。 [1] \n" +
//                "加拿大政治体制为联邦制和议会制君主立宪制，英国女王伊丽莎白二世为国家元首及国家象征，并任命女王总督派驻。\n" +
//                "加拿大原为印第安人与因纽特人的居住地。16世纪后，英国和法国殖民者先后侵入；1763年沦为英国殖民地。 [1]  1867年成为英国自治领。1926年英国承认其\"平等地位\"，获得外交独立权。1931年成为英联邦成员国，其议会也获得了同英国议会同等的立法权，1982年，英国女王签署《加拿大宪法法案》，加拿大议会获得立宪、修宪的全部权利。 [1] \n" +
//                "加拿大是一个高度发达的资本主义国家，得益于丰富的自然资源和高度发达的科技，使其成为世界上拥有最高生活水准、社会最富裕、经济最发达的国家之一。加拿大为继乌拉圭之后第二个大麻合法化的国家。 [2]  也是世界上最大的钻石生产国之一。 [3]  加拿大在教育、政府的透明度、社会自由度、生活品质及经济自由的国际排名都名列前茅。同时，加拿大也是八国集团、20国集团、北约、联合国、法语国家组织、世界贸易组织等国际组织的成员国加拿大（英语/法语：Canada），位于北美洲最北端，英联邦国家之一，素有“枫叶之国”的美誉，首都是渥太华。 [1] \n" +
//                "加拿大西抵太平洋，东迄大西洋，北至北冰洋，东北部和丹麦领地格陵兰岛相望，东部和法属圣皮埃尔和密克隆群岛相望，南方与美国本土接壤，西北方与美国阿拉斯加州为邻。领土面积为998.467万平方公里，位居世界第二，人口主要集中在南部五大湖沿岸。著名城市有多伦多、温哥华等。官方语言有英语和法语两种，是典型的双语国家。 [1] \n" +
//                "加拿大政治体制为联邦制和议会制君主立宪制，英国女王伊丽莎白二世为国家元首及国家象征，并任命女王总督派驻。\n" +
//                "加拿大原为印第安人与因纽特人的居住地。16世纪后，英国和法国殖民者先后侵入；1763年沦为英国殖民地。 [1]  1867年成为英国自治领。1926年英国承认其\"平等地位\"，获得外交独立权。1931年成为英联邦成员国，其议会也获得了同英国议会同等的立法权，1982年，英国女王签署《加拿大宪法法案》，加拿大议会获得立宪、修宪的全部权利。 [1] \n" +
//                "加拿大是一个高度发达的资本主义国家，得益于丰富的自然资源和高度发达的科技，使其成为世界上拥有最高生活水准、社会最富裕、经济最发达的国家之一。加拿大为继乌拉圭之后第二个大麻合法化的国家。 [2]  也是世界上最大的钻石生产国之一。 [3]  加拿大在教育、政府的透明度、社会自由度、生活品质及经济自由的国际排名都名列前茅。同时，加拿大也是八国集团、20国集团、北约、联合国、法语国家组织、世界贸易组织等国际组织的成员国"
//
    }

    fun showDetailContent(content: String): Unit {
    }

    override fun showError(msg: String) {
    }

    override fun initWidget() {
        super.initWidget()
        musicId = arguments?.getString("mMusicId")

        musicId?.let { mPresenter.getMusicDetialSingle(it) }
    }

    override fun getLayoutId(): Int = R.layout.fragment_player_detail

    companion object {

        fun newInstance(mMusicId: String): PlayerDetailFragment {
            val fragment = PlayerDetailFragment()
            val args = Bundle()
            args.putString("mMusicId", mMusicId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun isRegisterEventBus(): Boolean {
        return true
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUpdateDetail(event: Event<*>?) {
        if (event?.code == 101 && event?.data is String) {
            musicId = event.data.toString()
            if (mPresenter != null) {
                musicId?.let { mPresenter.getMusicDetialSingle(it) }
            }
        }
    }

    private fun getImageGetter(): Html.ImageGetter {
        return Html.ImageGetter { source ->
            activity?.let {
                val drawable = it.resources.getDrawable(Integer.parseInt(source))
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight())
                drawable
            }
        }
    }

}