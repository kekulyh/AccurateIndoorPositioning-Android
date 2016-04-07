package au.usyd.capstone.indoorandroid.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import au.usyd.capstone.indoorandroid.R;
import au.usyd.capstone.indoorandroid.domain.Room;

/**
 * Created by LYH on 16/4/3.
 */
public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private LayoutInflater mInflater;

    private List<Room> roomCardList;

    private static int colorPrimaryText;
    private static int colorSecondaryText;
    private static int colorDivider;

//    用于启动activity时,传给intent
    private Context context;

    public RoomAdapter(Context context, List<Room> roomCardList) {
        this.mInflater = mInflater.from(context);

        if (!roomCardList.isEmpty()){
            this.roomCardList = roomCardList;
        }

        this.context = context;

//        只能在构造函数里获取到资源文件的int
//        获取颜色, TextView的setTextColor需要的是resolved color(即8个数字的int),而不是直接由R文件提供的color.
        colorPrimaryText = context.getResources().getColor(R.color.primary_text);
        colorSecondaryText = context.getResources().getColor(R.color.secondary_text);
        colorDivider = context.getResources().getColor(R.color.divider);

        Log.e("roomAdapter", "constructor");
    }

    @Override
    public RoomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View roomListView = mInflater.inflate(R.layout.activity_room_list, parent,false);

        Log.e("roomAdapter", "onCreateViewHolder roomListView");

        //这边可以做一些属性设置，甚至事件监听绑定
        ViewHolder viewHolder = new ViewHolder(roomListView);

        Log.e("roomAdapter", "onCreateViewHolder viewHolder");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RoomAdapter.ViewHolder holder, int position) {

        final Room room = roomCardList.get(position);

        holder.roomImage.setImageDrawable(room.getRoomImage());
        holder.roomName.setText(room.getRoomName());
        holder.roomDetail.setText(room.getRoomDetail());

        holder.roomCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleRoomActivity.class);
//                把该room name传给打开的room activity
                intent.putExtra("title", room.getRoomName());
                context.startActivity(intent);
            }
        });


        Log.e("roomAdapter", "onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        Log.e("roomAdapter", "getItemCount");
        System.out.println(roomCardList.size());
        return roomCardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView roomCardView;

        ImageView roomImage;
        TextView roomName;
        View roomDivider;
        TextView roomDetail;

        public ViewHolder(View itemView) {
            super(itemView);

            roomCardView = (CardView) itemView;
            roomImage = (ImageView) itemView.findViewById(R.id.roomImage);
            roomName = (TextView) itemView.findViewById(R.id.roomName);
            roomName.setTextColor(colorPrimaryText);
            roomDivider = itemView.findViewById(R.id.roomDivider);
            roomDivider.setBackgroundColor(colorDivider);
            roomDetail = (TextView) itemView.findViewById(R.id.roomDetail);
            roomDetail.setTextColor(colorSecondaryText);

        }
    }
}
