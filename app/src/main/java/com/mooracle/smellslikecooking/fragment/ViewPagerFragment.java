package com.mooracle.smellslikecooking.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mooracle.smellslikecooking.R;
import com.mooracle.smellslikecooking.model.Recipes;

public class ViewPagerFragment extends Fragment {
    //: set tag for ViewPagerFragment:
    public static final String VIEWPAGER_FRAGMENT = "Viewpager_Fragment";

    //: make String constant name to store the index name:
    public static final String KEY_RECIPE_INDEX = "recipe_index";
    private int index;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //: add a View object named view with layout in use fragment_viewpager, with no attach to root
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        //: set the IngredientFragment and DirectionFragment to be used in the viewPager Adapter:
        final IngredientsFragment ingredientsFragment = new IngredientsFragment();
        final DirectionsFragment directionsFragment = new DirectionsFragment();

        //: add ViewPager object into this fragment:
        ViewPager viewPager = view.findViewById(R.id.viewPager);

        //: set the ViewPager adapter: we will use the FragmentPagerAdapter and pass getChildFragmentManager
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0){return ingredientsFragment;} else {return directionsFragment;}
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0){
                    return "Ingredients";
                } else {
                    return "Directions";
                }
            }
        });

        //: get the int index value from the bundle
        index = getArguments().getInt(KEY_RECIPE_INDEX);

        //: add Toast from main activity to this fragment:
        //Toast.makeText(getActivity(), Recipes.names[index], Toast.LENGTH_SHORT).show();
        //: this toast will be changed by changing the action bar title into the recipe name
        getActivity().setTitle(Recipes.names[index]);

        //: set the tabLayout object and use setting from viewPager
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onStop() {
        //: when this fragment ends we need to return the action bar title back to app default (app name)
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
