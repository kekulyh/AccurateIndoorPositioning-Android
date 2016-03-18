package au.usyd.capstone.indoorandroid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LYH on 16/3/17.
 */
public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {

    private LayoutInflater mInflater;

    private List<String> mTitles=null;

    private static int colorPrimaryText;
    private static int colorSecondaryText;
    private static int colorDivider;
    private static int colorBackground;
    private static Drawable drawableBackground;
    private static String descriptionHTML;
    private static String contactHTML;

    private Integer aboutVersionCode;
    private String aboutVersionName;
    private Drawable aboutIcon;

    public  AboutAdapter(Context context){
        this.mInflater=LayoutInflater.from(context);
//        this.mTitles=new ArrayList<String>();
//        for (int i=0;i<20;i++){
//            int index=i+1;
//            mTitles.add("item"+index);
//        }

//        只能在构造函数里获取到资源文件的int
//        获取颜色, TextView的setTextColor需要的是resolved color(即8个数字的int),而不是直接由R文件提供的color.
        colorPrimaryText = context.getResources().getColor(R.color.primary_text);
        colorSecondaryText = context.getResources().getColor(R.color.secondary_text);
        colorDivider = context.getResources().getColor(R.color.divider);
        colorBackground = context.getResources().getColor(R.color.primary_light);
        drawableBackground = context.getResources().getDrawable(R.drawable.page_background_repeat);

//        获取AboutFragment的填充内容
        aboutIcon = context.getResources().getDrawable(R.drawable.android_color);
        descriptionHTML = context.getResources().getString(R.string.aboutDescription);
        contactHTML = context.getResources().getString(R.string.aboutContact);

        Log.e("AboutAdapter", "constructor");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View aboutListView = mInflater.inflate(R.layout.fragment_about_list,parent,false);

        Log.e("AboutAdapter", "onCreateViewHolder aboutListView");

        //这边可以做一些属性设置，甚至事件监听绑定
//        aboutListView.setBackgroundColor(colorBackground);
        aboutListView.setBackgroundDrawable(drawableBackground);
        ViewHolder viewHolder=new ViewHolder(aboutListView);

        Log.e("AboutAdapter", "onCreateViewHolder viewHolder");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AboutAdapter.ViewHolder holder, int position) {
        holder.aboutIcon.setImageDrawable(aboutIcon);
        holder.aboutAppName.setText(R.string.aboutAppName);
        holder.aboutVersion.setText(R.string.aboutVersion);
        holder.aboutAppDescription.setText(Html.fromHtml(descriptionHTML));
        holder.aboutContact.setText(Html.fromHtml(contactHTML));
        holder.aboutSpecial1.setText(R.string.aboutSpecial1);
        holder.aboutSpecial2.setText(R.string.aboutSpecial2);
        holder.aboutSpecial3.setText(R.string.aboutSpecial3);

        Log.e("AboutAdapter", "onBindViewHolder");
    }


//    注意!!: 必须在getItemCount中返回大于0的数,RecycleView才会去调用onCreateViewHolder和onBindViewHolder.
    @Override
    public int getItemCount() {

        Log.e("AboutAdapter", "getItemCount");

//        若需要填充多个item, 可用计数来代替,此处由于不需要故写死为1.
        return 1;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView aboutIcon;
        TextView aboutAppName;
        View aboutSpecialContainer;
        Button aboutSpecial1;
        Button aboutSpecial2;
        Button aboutSpecial3;
        TextView aboutVersion;
        View aboutDivider;
        TextView aboutAppDescription;
        TextView aboutContact;


        public ViewHolder(View view){
            super(view);

            //get the fragment_about_list.xml views
            aboutIcon = (ImageView) view.findViewById(R.id.aboutIcon);
            aboutAppName = (TextView) view.findViewById(R.id.aboutAppName);
            aboutAppName.setTextColor(colorPrimaryText);
            aboutSpecialContainer = view.findViewById(R.id.aboutSpecialContainer);
            aboutSpecial1 = (Button) view.findViewById(R.id.aboutSpecial1);
            aboutSpecial2 = (Button) view.findViewById(R.id.aboutSpecial2);
            aboutSpecial3 = (Button) view.findViewById(R.id.aboutSpecial3);
            aboutVersion = (TextView) view.findViewById(R.id.aboutVersion);
            aboutVersion.setTextColor(colorSecondaryText);
            aboutDivider = view.findViewById(R.id.aboutDivider);
            aboutDivider.setBackgroundColor(colorDivider);
            aboutAppDescription = (TextView) view.findViewById(R.id.aboutDescription);
            aboutAppDescription.setTextColor(colorSecondaryText);
            aboutContact = (TextView) view.findViewById(R.id.aboutContact);
            aboutContact.setTextColor(colorSecondaryText);
        }
    }


}
