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
import au.usyd.capstone.indoorandroid.domain.Floor;

/**
 * Created by LYH on 16/4/3.
 */
public class FloorAdapter extends RecyclerView.Adapter<FloorAdapter.ViewHolder> {

    private LayoutInflater mInflater;

    private List<Floor> floorCardList;

    private static int colorPrimaryText;
    private static int colorSecondaryText;
    private static int colorDivider;

//    用于启动activity时,传给intent
    private Context context;

    public FloorAdapter(Context context, List<Floor> floorCardList) {
        this.mInflater = mInflater.from(context);
        this.floorCardList = floorCardList;
        this.context = context;

//        只能在构造函数里获取到资源文件的int
//        获取颜色, TextView的setTextColor需要的是resolved color(即8个数字的int),而不是直接由R文件提供的color.
        colorPrimaryText = context.getResources().getColor(R.color.primary_text);
        colorSecondaryText = context.getResources().getColor(R.color.secondary_text);
        colorDivider = context.getResources().getColor(R.color.divider);

        Log.e("FloorAdapter", "constructor");
    }

    @Override
    public FloorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View floorListView = mInflater.inflate(R.layout.activity_floor_list, parent,false);

        Log.e("FloorAdapter", "onCreateViewHolder floorListView");

        //这边可以做一些属性设置，甚至事件监听绑定
//        buildingListView.setBackgroundDrawable(drawableBackground);
        ViewHolder viewHolder = new ViewHolder(floorListView);

        Log.e("FloorAdapter", "onCreateViewHolder viewHolder");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FloorAdapter.ViewHolder holder, int position) {

        final Floor floor = floorCardList.get(position);

        holder.floorImage.setImageDrawable(floor.getFloorImage());
        holder.floorName.setText(floor.getFloorName());
        holder.floorDetail.setText(floor.getFloorDetail());

        holder.floorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FloorActivity.class);
//                把该floor name传给打开的room activity
                intent.putExtra("title", floor.getFloorName());
                context.startActivity(intent);
            }
        });


        Log.e("BuildingAdapter", "onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        Log.e("FloorAdapter", "getItemCount");
        System.out.println(floorCardList.size());
        return floorCardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView floorCardView;

        ImageView floorImage;
        TextView floorName;
        View floorDivider;
        TextView floorDetail;

        public ViewHolder(View itemView) {
            super(itemView);

            floorCardView = (CardView) itemView;
            floorImage = (ImageView) itemView.findViewById(R.id.floorImageView);
            floorName = (TextView) itemView.findViewById(R.id.floorName);
            floorName.setTextColor(colorPrimaryText);
            floorDivider = itemView.findViewById(R.id.floorDivider);
            floorDivider.setBackgroundColor(colorDivider);
            floorDetail = (TextView) itemView.findViewById(R.id.floorDetail);
            floorDetail.setTextColor(colorSecondaryText);

        }
    }
}
