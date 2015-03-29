package fastpace.com.appme;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ViewSpinner extends ArrayAdapter{
    private Context mContext;

    private Drawable[] mViewImages;

    public ViewSpinner(Context context, int resource, Drawable[] viewImages) {
        super(context, resource, viewImages);
        mViewImages = viewImages;
        mContext = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomerView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomerView(position, convertView,  parent);
    }

    public View getCustomerView(int position, View converView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View spinnerRow = inflater.inflate(R.layout.view_spinner, parent, false);
        ImageView image = (ImageView) spinnerRow.findViewById(R.id.view_image);

        image.setImageDrawable(mViewImages[position]);

        return spinnerRow;
    }
}
