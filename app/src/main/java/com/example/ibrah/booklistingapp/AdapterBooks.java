package com.example.ibrah.booklistingapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 * Created by ibrah on 16/07/2017.
 */

public class AdapterBooks extends ArrayAdapter<Book> {
    private BigDecimal number;
    String TAG = "Adapter";

    public AdapterBooks(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_view, parent, false);
        }
        // Get the {@link AndroidFlavor} object located at this position in the list
        final Book currenbook = getItem(position);

        //text view for the title of the book
        TextView autor = (TextView) listItemView.findViewById(R.id.Autor);
        autor.setText(currenbook.getAuthor());
        //text view for the title of the book
        TextView title = (TextView) listItemView.findViewById(R.id.Title);
        title.setText(currenbook.getTitle());
        //text view for the title of the book
        TextView rating = (TextView) listItemView.findViewById(R.id.rating);
        GradientDrawable ratingBook = (GradientDrawable) rating.getBackground();
        int ratingColor;
        if (currenbook.getRating() != null) {
            ratingColor = getRatingColor(formatRating(currenbook.getRating()));
        } else {
            ratingColor = 1;
        }

        ratingBook.setColor(ratingColor);
        rating.setText(currenbook.getRating());

        // Set Image if available
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.pic_book);
        String image = currenbook.getThumbnailLink();
        if (image != null && image.length() > 0) {
            Picasso.with(getContext()).load(currenbook.getThumbnailLink()).resize(24, 24).centerCrop().into(imageView);
            GradientDrawable magnitudeCircle = (GradientDrawable) imageView.getBackground();
            magnitudeCircle.setColor(getRatingColor(100));
        }

        return listItemView;
    }

    private int getRatingColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.rating1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.rating2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.rating3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.rating4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.rating5;
                break;
            case 100:
                magnitudeColorResourceId = R.color.image;
                break;
            default:
                magnitudeColorResourceId = R.color.rating5;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    private double formatRating(String magnitud) {
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        format.setParseBigDecimal(true);
        try {
            number = (BigDecimal) format.parse(magnitud);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return number.doubleValue();
    }
}
