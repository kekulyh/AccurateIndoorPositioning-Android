package au.usyd.capstone.indoorandroid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LYH on 16/3/18.
 */
public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder> {

    private LayoutInflater mInflater;

    private List<BuildingCard> buildingCardList;

    private static int colorPrimaryText;
    private static int colorSecondaryText;
    private static int colorDivider;
    private static int colorBackground;
    private static Drawable drawableBackground;

    private Drawable buildingImage;
    private String buildingName;
    private String buildingOpeningTime;

    public BuildingAdapter(Context context, List<BuildingCard> buildingCardList){
        this.mInflater = LayoutInflater.from(context);

        this.buildingCardList = buildingCardList;

//        只能在构造函数里获取到资源文件的int
//        获取颜色, TextView的setTextColor需要的是resolved color(即8个数字的int),而不是直接由R文件提供的color.
        colorPrimaryText = context.getResources().getColor(R.color.primary_text);
        colorSecondaryText = context.getResources().getColor(R.color.secondary_text);
        colorDivider = context.getResources().getColor(R.color.divider);

        Log.e("BuildingAdapter", "constructor");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View buildingListView = mInflater.inflate(R.layout.fragment_building_list,parent,false);

        Log.e("BuildingAdapter", "onCreateViewHolder buildingListView");

        //这边可以做一些属性设置，甚至事件监听绑定
//        buildingListView.setBackgroundDrawable(drawableBackground);
        ViewHolder viewHolder=new ViewHolder(buildingListView);

        Log.e("BuildingAdapter", "onCreateViewHolder viewHolder");

        return viewHolder;
    }

//    待优化
    @Override
    public void onBindViewHolder(BuildingAdapter.ViewHolder holder, int position) {

        BuildingCard buildingCard = buildingCardList.get(position);

        holder.buildingImage.setImageDrawable(buildingCard.buildingImage);
        holder.buildingName.setText(buildingCard.buildingName);
        holder.buildingOpeningTime.setText(buildingCard.buildingOpeningTime);

        Log.e("BuildingAdapter", "onBindViewHolder");
    }

//    待优化
//    注意!!: 必须在getItemCount中返回大于0的数,RecycleView才会去调用onCreateViewHolder和onBindViewHolder.
    @Override
    public int getItemCount() {

        Log.e("BuildingAdapter", "getItemCount");
        System.out.println(buildingCardList.size());

        return buildingCardList.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView buildingCardView;

        ImageView buildingImage;
        TextView buildingName;
        View buildingDivider;
        TextView buildingOpeningTime;

        public ViewHolder(View view){
            super(view);

            buildingCardView = (CardView) view;
//            buildingCardView.setCardBackgroundColor(R.color.primary_light);
//            buildingCardView.setRadius(50);
            //get the fragment_building_list.xml views
            buildingImage = (ImageView) view.findViewById(R.id.buildingImage);
            buildingName = (TextView) view.findViewById(R.id.buildingName);
            buildingName.setTextColor(colorPrimaryText);
            buildingDivider = view.findViewById(R.id.buildingDivider);
            buildingDivider.setBackgroundColor(colorDivider);
            buildingOpeningTime = (TextView) view.findViewById(R.id.buildingOpeningTime);
            buildingOpeningTime.setTextColor(colorSecondaryText);

        }
    }


}
