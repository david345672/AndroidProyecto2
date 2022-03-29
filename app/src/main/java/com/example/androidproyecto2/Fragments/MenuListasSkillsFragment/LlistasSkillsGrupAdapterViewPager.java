package com.example.androidproyecto2.Fragments.MenuListasSkillsFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.viewpager.widget.PagerAdapter;

import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.R;

import java.util.List;

public class LlistasSkillsGrupAdapterViewPager extends PagerAdapter
{
    Context context;
    List<LlistaSkills> llistaSkills;

    public LlistasSkillsGrupAdapterViewPager(Context context, List<LlistaSkills> llistaSkills) {
        this.context = context;
        this.llistaSkills = llistaSkills;
    }




    @Override
    public int getCount() {
        return llistaSkills.size();
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.llistaskill_vpager_item,container,false);

        Button btnLlistaSkill = view.findViewById(R.id.btnLlistaSkill);
        LlistaSkills LS = llistaSkills.get(position);
        btnLlistaSkill.setText(LS.getNom());

        container.addView(view);
        return view;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }



}
