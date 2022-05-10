package de.hft.ubq;

import android.widget.ImageButton;

import com.example.ubq.R;

public class Game {
    public boolean GM = false;
    public int door = 0;
    public int usedpicture1=0;
    public int usedpicture2=0;
    public int usedpicture3=0;
    public int usedpicture4=0;
    public int usedpicture5=0;
    public int usedpicture6=0;


    public int set_door(){
        int max = 76;

        door = (int)(Math.random()*(max+1));

        if (usedpicture1==0) usedpicture1 = door;
        if (door == usedpicture1) set_door();

        if (usedpicture2==0 && usedpicture1!=0) usedpicture2 = door;
        if (door == usedpicture2) set_door();

        if (usedpicture3==0 && usedpicture2!=0) usedpicture3 = door;
        if (door == usedpicture3) set_door();

        if (usedpicture4==0 && usedpicture3!=0) usedpicture4 = door;
        if (door == usedpicture4) set_door();

        if (usedpicture5==0 && usedpicture4!=0) usedpicture5 = door;
        if (door == usedpicture5) set_door();

        if (usedpicture6==0 && usedpicture5!=0) usedpicture1 = door;
        if (door == usedpicture6) set_door();

        return door;
    }



    public void matchdoor( ImageButton button){
        set_door();


        switch (door){
            case 1: button.setImageResource(R.drawable.p_1);
                button.setTag(R.drawable.p_1);
                break;
            case 2: button.setImageResource(R.drawable.p_2);
                button.setTag(R.drawable.p_2);
                break;
            case 3: button.setImageResource(R.drawable.p_3);
                button.setTag(R.drawable.p_3);
                break;
            case 4: button.setImageResource(R.drawable.p_4);
                button.setTag(R.drawable.p_4);
                break;
            case 5: button.setImageResource(R.drawable.p_5);
                button.setTag(R.drawable.p_5);
                break;
            case 6: button.setImageResource(R.drawable.p_6);
                button.setTag(R.drawable.p_6);
                break;
            case 7: button.setImageResource(R.drawable.p_7);
                button.setTag(R.drawable.p_7);
                break;
            case 8: button.setImageResource(R.drawable.p_8);
                button.setTag(R.drawable.p_8);
                break;
            case 9: button.setImageResource(R.drawable.p_9);
                button.setTag(R.drawable.p_9);
                break;
            case 10: button.setImageResource(R.drawable.p_10);
                button.setTag(R.drawable.p_10);
                break;
            case 11: button.setImageResource(R.drawable.p_11);
                button.setTag(R.drawable.p_11);
                break;
            case 12: button.setImageResource(R.drawable.p_12);
                button.setTag(R.drawable.p_12);
                break;
            case 13: button.setImageResource(R.drawable.p_13);
                button.setTag(R.drawable.p_13);
                break;
            case 14: button.setImageResource(R.drawable.p_14);
                button.setTag(R.drawable.p_14);
                break;
            case 15: button.setImageResource(R.drawable.p_15);
                button.setTag(R.drawable.p_15);
                break;
            case 16: button.setImageResource(R.drawable.p_16);
                button.setTag(R.drawable.p_16);
                break;
            case 17: button.setImageResource(R.drawable.p_17);
                button.setTag(R.drawable.p_17);
                break;
            case 18: button.setImageResource(R.drawable.p_18);
                button.setTag(R.drawable.p_18);
                break;
            case 19: button.setImageResource(R.drawable.p_19);
                button.setTag(R.drawable.p_19);
                break;
            case 20: button.setImageResource(R.drawable.p_20);
                button.setTag(R.drawable.p_20);
                break;
            case 21: button.setImageResource(R.drawable.p_21);
                button.setTag(R.drawable.p_21);
                break;
            case 22: button.setImageResource(R.drawable.p_22);
                button.setTag(R.drawable.p_22);
                break;
            case 23: button.setImageResource(R.drawable.p_23);
                button.setTag(R.drawable.p_23);
                break;
            case 24: button.setImageResource(R.drawable.p_24);
                button.setTag(R.drawable.p_24);
                break;
            case 25: button.setImageResource(R.drawable.p_25);
                button.setTag(R.drawable.p_25);
                break;
            case 26: button.setImageResource(R.drawable.p_26);
                button.setTag(R.drawable.p_26);
                break;
            case 27: button.setImageResource(R.drawable.p_27);
                button.setTag(R.drawable.p_27);
                break;
            case 28: button.setImageResource(R.drawable.p_28);
                button.setTag(R.drawable.p_28);
                break;
            case 29: button.setImageResource(R.drawable.p_29);
                button.setTag(R.drawable.p_29);
                break;
            case 30: button.setImageResource(R.drawable.p_30);
                button.setTag(R.drawable.p_30);
                break;
            case 31: button.setImageResource(R.drawable.p_31);
                button.setTag(R.drawable.p_31);
                break;
            case 32: button.setImageResource(R.drawable.p_32);
                button.setTag(R.drawable.p_32);
                break;
            case 33: button.setImageResource(R.drawable.p_34);
                button.setTag(R.drawable.p_34);
                break;
            case 34: button.setImageResource(R.drawable.p_35);
                button.setTag(R.drawable.p_35);
                break;
            case 35: button.setImageResource(R.drawable.p_36);
                button.setTag(R.drawable.p_36);
                break;
            case 36: button.setImageResource(R.drawable.p_37);
                button.setTag(R.drawable.p_37);
                break;
            case 37: button.setImageResource(R.drawable.p_38);
                button.setTag(R.drawable.p_38);
                break;
            case 38: button.setImageResource(R.drawable.p_39);
                button.setTag(R.drawable.p_39);
                break;
            case 39: button.setImageResource(R.drawable.p_40);
                button.setTag(R.drawable.p_40);
                break;
            case 40: button.setImageResource(R.drawable.p_41);
                button.setTag(R.drawable.p_41);
                break;
            case 41: button.setImageResource(R.drawable.p_42);
                button.setTag(R.drawable.p_42);
                break;
            case 42: button.setImageResource(R.drawable.p_43);
                button.setTag(R.drawable.p_43);
                break;
            case 43: button.setImageResource(R.drawable.p_44);
                button.setTag(R.drawable.p_44);
                break;
            case 44: button.setImageResource(R.drawable.p_45);
                button.setTag(R.drawable.p_45);
                break;
            case 45: button.setImageResource(R.drawable.p_46);
                button.setTag(R.drawable.p_46);
                break;
            case 46: button.setImageResource(R.drawable.p_47);
                button.setTag(R.drawable.p_47);
                break;
            case 47: button.setImageResource(R.drawable.p_48);
                button.setTag(R.drawable.p_48);
                break;
            case 48: button.setImageResource(R.drawable.p_49);
                button.setTag(R.drawable.p_49);
                break;
            case 49: button.setImageResource(R.drawable.p_50);
                button.setTag(R.drawable.p_50);
                break;
            case 50: button.setImageResource(R.drawable.p_51);
                button.setTag(R.drawable.p_51);
                break;
            case 51: button.setImageResource(R.drawable.p_52);
                button.setTag(R.drawable.p_52);
                break;
            case 52: button.setImageResource(R.drawable.p_53);
                button.setTag(R.drawable.p_53);
                break;
            case 53: button.setImageResource(R.drawable.p_54);
                button.setTag(R.drawable.p_54);
                break;
            case 54: button.setImageResource(R.drawable.p_55);
                button.setTag(R.drawable.p_55);
                break;
            case 55: button.setImageResource(R.drawable.p_56);
                button.setTag(R.drawable.p_56);
                break;
            case 56: button.setImageResource(R.drawable.p_57);
                button.setTag(R.drawable.p_57);
                break;
            case 57: button.setImageResource(R.drawable.p_58);
                button.setTag(R.drawable.p_58);
                break;
            case 58: button.setImageResource(R.drawable.p_59);
                button.setTag(R.drawable.p_59);
                break;
            case 59: button.setImageResource(R.drawable.p_60);
                button.setTag(R.drawable.p_60);
                break;
            case 60: button.setImageResource(R.drawable.p_61);
                button.setTag(R.drawable.p_61);
                break;
            case 61: button.setImageResource(R.drawable.p_62);
                button.setTag(R.drawable.p_62);
                break;
            case 62: button.setImageResource(R.drawable.p_63);
                button.setTag(R.drawable.p_63);
                break;
            case 63: button.setImageResource(R.drawable.p_64);
                button.setTag(R.drawable.p_64);
                break;
            case 64: button.setImageResource(R.drawable.p_65);
                button.setTag(R.drawable.p_65);
                break;
            case 65: button.setImageResource(R.drawable.p_66);
                button.setTag(R.drawable.p_66);
                break;
            case 66: button.setImageResource(R.drawable.p_67);
                button.setTag(R.drawable.p_67);
                break;
            case 67: button.setImageResource(R.drawable.p_68);
                button.setTag(R.drawable.p_68);
                break;
            case 68: button.setImageResource(R.drawable.p_69);
                button.setTag(R.drawable.p_69);
                break;
            case 69: button.setImageResource(R.drawable.p_70);
                button.setTag(R.drawable.p_70);
                break;
            case 70: button.setImageResource(R.drawable.p_71);
                button.setTag(R.drawable.p_71);
                break;
            case 71: button.setImageResource(R.drawable.p_33);
                button.setTag(R.drawable.p_33);
                break;
            case 72: button.setImageResource(R.drawable.p_72);
                button.setTag(R.drawable.p_72);
                break;
            case 73: button.setImageResource(R.drawable.p_73);
                button.setTag(R.drawable.p_73);
                break;
            case 74: button.setImageResource(R.drawable.p_74);
                button.setTag(R.drawable.p_74);
                break;
            case 75: button.setImageResource(R.drawable.p_75);
                button.setTag(R.drawable.p_75);
                break;
            case 76: button.setImageResource(R.drawable.p_76);
                button.setTag(R.drawable.p_76);
                break;
            default: button.setImageResource(R.drawable.p_0);
                button.setTag(R.drawable.p_0);
                break;

        }

    }
}