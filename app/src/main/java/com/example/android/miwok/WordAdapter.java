package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>  {

    /** Resource ID for the background color for this list of words */
    private int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Verifique si una vista existente esta siendo reutilizada, de lo contrario infla la vista
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        /*Obtiene el objeto (Word) ubicado en esta posicion en la lista*/
        Word currentWord = getItem(position);

        /*Busca el textView en el layout list_item.xml con el ID miwok_text_view.*/
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        /*Obtiene la traduccion  Miwok del objeto word y lo agrega al TextView*/
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Repite el procedimiento anterior para los demas atributos del objeto Word
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);

        defaultTextView.setText(currentWord.getDefaultTranslation());

        /*Encuentra el ImageView en el layout list_item con el ID image.*/
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);

        //Verifica si el objeto word dispone de una imagen o no.
        if (currentWord.hasImage()) {
            //Si la imagen esta disponible, muestra la imagen en el ImageView
            imageView.setImageResource(currentWord.getImageResourceId());
            // Asegurar que la imagen es visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            //De lo conttario esconde la imagen (Cambia la visibilidad a GONE)
            imageView.setVisibility(View.GONE);
        }

        // Establecer el color del tema para el elemento de la lista
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Encuentra el color que el ID de recurso asigna a
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Establecer el color de fondo del contenedor de texto Ver
        textContainer.setBackgroundColor(color);

        /*Devuelve todo el diseño del elemento de la lista para que se pueda mostrar en el listview*/
        return listItemView;
    }
}
