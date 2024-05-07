package com.example.chatapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chatapp.R
import com.example.chatapp.databinding.ItemRoomsBinding
import com.example.chatapp.myDateBase.Room

class RoomsAdapter( private var rooms:List<Room>?):RecyclerView.Adapter<RoomsAdapter.myViewHolder>() {
    class myViewHolder (var binding:ItemRoomsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
      val binding = ItemRoomsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myViewHolder(binding)
    }

    override fun getItemCount(): Int =rooms?.size?:0
    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val room = rooms?.get(position)?: Room()
        when(room.categoryImageId){
             0->{
                holder.binding.imageRv.setImageResource( R.drawable.image_movies_cat)
            }
            1 ->{
                holder.binding.imageRv.setImageResource( R.drawable.image_sports_cat)
            }
            2->{
                holder.binding.imageRv.setImageResource( R.drawable.image_music_cat)
            }
        }
        holder.binding.room = room
   holder.itemView.setOnClickListener {
       onClickListener?.OnClicked(room =room)
   }
    }
   fun  updateRoom(room:List<Room>?){
        this.rooms =room
       notifyItemRangeInserted(0, room?.size ?: 0)
   }
    private var onClickListener:OnClickListener? = null
    fun SetOnClickListener(listener: OnClickListener){
        onClickListener = listener
    }
    fun interface  OnClickListener{
      fun OnClicked(room:Room)
  }

}