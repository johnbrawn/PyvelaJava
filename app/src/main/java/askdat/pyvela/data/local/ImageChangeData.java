package askdat.pyvela.data.local;

import askdat.pyvela.R;

public final class ImageChangeData {
    public static volatile ImageChangeData instance;

    public static void getInstance(){
        if (instance==null)
            synchronized (ImageChangeData.class) {
                if (instance == null)
                    instance = new ImageChangeData();
            }
    }

    public int[] Images;

    private ImageChangeData(){
        Images = new int[]{
                R.drawable.morning_in_a_pine_forest,
                R.drawable.serow_girl_with_peaches,
                R.drawable.the_last_day_of_pompeii,
                R.drawable.the_ninth_wave,
        };
    }
}
