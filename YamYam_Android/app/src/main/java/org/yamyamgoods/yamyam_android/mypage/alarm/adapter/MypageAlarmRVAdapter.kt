package org.yamyamgoods.yamyam_android.mypage.alarm.adapter

import android.content.Context
import android.media.Image
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.mypage.MypageActivity
import org.yamyamgoods.yamyam_android.mypage.alarm.MypageAlarmItem
import org.yamyamgoods.yamyam_android.network.get.AlarmListData


var alarmIndex: Int = -1
var reviewIndex: Int = -1


class MypageAlarmRVAdapter(private val ctx: Context, var dataList: List<AlarmListData>) :
    RecyclerView.Adapter<MypageAlarmRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageAlarmRVAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_mypage_alarm, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MypageAlarmRVAdapter.Holder, position: Int) {
//        if (dataList.size.toString() == "[]")
//        {
//            setVisible(holder.emptyClAlarm)
//        }

        dataList[position].let { item ->
            //알람 목록에 있는 빨간 색
//            if (item.alarm_check_flag == 0)   //읽지 않음
//                holder.ivReddot.isSelected = true
//            if (item.alarm_check_flag == 1) {    //읽음
//                holder.ivReddot.isSelected = false
//                setInvisible(holder.ivReddot)
//            }

            alarmIndex = item.alarm_idx
            reviewIndex = item.goods_review_idx
            holder.alarmContents.text = item.alarm_message
            holder.date.text = item.alarm_date_time

            holder.clAlarm.setOnClickListener{
                (ctx as MypageActivity).getAlarmReviewDetailResponse(item.alarm_idx, item.goods_review_idx)
            }
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var emptyClAlarm: ConstraintLayout = itemView.findViewById(R.id.cl_mypage_alarm_empty) as ConstraintLayout
        var clAlarm: ConstraintLayout = itemView.findViewById(R.id.cl_rv_item_mypage_alarm) as ConstraintLayout
        var ivReddot: ImageView = itemView.findViewById(R.id.iv_rv_item_mypage_alarm_reddot) as ImageView
        var alarmContents: TextView = itemView.findViewById(R.id.tv_rv_item_mypage_alarm_contents) as TextView
        var date: TextView = itemView.findViewById(R.id.tv_rv_item_mypage_alarm_date) as TextView
    }

    fun setInvisible(view: View) {
        view.visibility = View.INVISIBLE
    }
    fun setVisible(view: View) {
        view.visibility = View.VISIBLE
    }
}