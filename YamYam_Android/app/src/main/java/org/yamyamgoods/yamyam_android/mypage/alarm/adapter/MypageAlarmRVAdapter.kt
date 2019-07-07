package org.yamyamgoods.yamyam_android.mypage.alarm.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.mypage.alarm.MypageAlarmItem

class MypageAlarmRVAdapter(private val ctx: Context, val dataList: List<MypageAlarmItem>): RecyclerView.Adapter<MypageAlarmRVAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageAlarmRVAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_mypage_alarm, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        dataList[position].let { item->
            if (item.isRead == true)
                setInvisible(holder.ivReddot)

            holder.clAlarm.setOnClickListener{
                item.isRead = true
                setInvisible(holder.ivReddot)
            }

//            var startText: String = ""
//            if (item.reviewFlag == 0)
//                startText = "리뷰에 댓글이 달렸습니다: "
//            if (item.reviewFlag == 1)
//                startText = "댓글에 답글이 달렸습니다: "
            holder.alarmContents.text = item.contents

            holder.date.text = item.date
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var clAlarm: ConstraintLayout = itemView.findViewById(R.id.cl_rv_item_mypage_alarm)
        var ivReddot: ImageView = itemView.findViewById(R.id.iv_rv_item_mypage_alarm_reddot)
        var alarmContents: TextView = itemView.findViewById(R.id.tv_rv_item_mypage_alarm_contents)
        var date: TextView = itemView.findViewById(R.id.tv_rv_item_mypage_alarm_date)
    }

    fun setInvisible(view: View) {
        view.visibility = View.INVISIBLE
    }
}