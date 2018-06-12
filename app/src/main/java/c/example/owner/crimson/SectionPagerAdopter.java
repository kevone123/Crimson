package c.example.owner.crimson;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SectionPagerAdopter extends FragmentPagerAdapter {

    public SectionPagerAdopter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ReqFragment requestFragment=new ReqFragment();
                return requestFragment;
            case 1:
                ChatsFragment chatsFragment=new ChatsFragment();
                return chatsFragment;
            case 2:
                FriendsFragment friendsFragment=new FriendsFragment();
                return friendsFragment;
            default:
                return null;
        }

    }



    @Override
    public int getCount() {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return  "Requests";
        } else if (position == 1) {
            return "Chats";
        } else if (position == 2) {
            return "Friends";
        }
        else{
            return null;

        }
    }



}



