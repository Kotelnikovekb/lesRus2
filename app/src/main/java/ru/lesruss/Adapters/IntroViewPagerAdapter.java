package ru.lesruss.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import ru.lesruss.Class.ScreenItem;
import ru.lesruss.R;


public class IntroViewPagerAdapter extends PagerAdapter {
    Context mContext;
    List<ScreenItem> mList;

    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View rootView = inflater.inflate(R.layout.screen_item_intro,null);
        ImageView imageView=rootView.findViewById(R.id.imageView);
        TextView title=rootView.findViewById(R.id.textView2);
        TextView description=rootView.findViewById(R.id.textView3);

        title.setText(mList.get(position).getTitle());
        description.setText(mList.get(position).getDescription());

        imageView.setImageResource(mList.get(position).getImg());
        container.addView(rootView);
        return rootView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
