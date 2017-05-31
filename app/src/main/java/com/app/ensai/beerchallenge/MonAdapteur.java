package com.app.ensai.beerchallenge;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MonAdapteur extends BaseAdapter implements AdapterView.OnItemClickListener {

    ArrayList<ListItem> myList = new ArrayList<ListItem>();
    Context context;

    // on passe le context afin d'obtenir un LayoutInflater pour utiliser notre
// row_layout.xml
// on passe les valeurs de notre à l'adapter
    public MonAdapteur(Context context, ArrayList<ListItem> myList) {
        this.myList = myList;
        this.context = context;
    }

    // retourne le nombre d'objet présent dans notre liste
    @Override
    public int getCount() {
        return myList.size();
    }

    // retourne un élément de notre liste en fonction de sa position
    @Override
    public ListItem getItem(int position) {
        return myList.get(position);
    }

    // retourne l'id d'un élément de notre liste en fonction de sa position
    @Override
    public long getItemId(int position) {
        return myList.indexOf(getItem(position));
    }

    // retourne la vue d'un élément de la liste
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder = null;

// au premier appel ConvertView est null, on inflate notre layout
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.rowlayout, parent, false);

// nous plaçons dans notre MyViewHolder les vues de notre layout
            mViewHolder = new MyViewHolder();
            mViewHolder.textViewName = (TextView) convertView
                    .findViewById(R.id.textViewName);
            mViewHolder.textViewCouleur = (TextView) convertView
                    .findViewById(R.id.textViewCouleur);
            mViewHolder.textViewFermentation = (TextView) convertView .findViewById(R.id.textViewFermentation);
// mViewHolder.imageView = (ImageView) convertView
// .findViewById(R.id.imageView);
            mViewHolder.textViewNote = (TextView) convertView
                    .findViewById(R.id.textViewNote);
// mViewHolder.textViewImage = (TextView) convertView
// .findViewById(R.id.textViewImage);

// nous attribuons comme tag notre MyViewHolder à convertView
            convertView.setTag(mViewHolder);
        } else {
// convertView n'est pas null, nous récupérons notre objet MyViewHolder
// et évitons ainsi de devoir retrouver les vues à chaque appel de getView
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

// nous récupérons l'item de la liste demandé par getView
        ListItem listItem = (ListItem) getItem(position);

// nous pouvons attribuer à nos vues les valeurs de l'élément de la liste
        mViewHolder.textViewName.setText(listItem.getName());
        mViewHolder.textViewCouleur.setText(String.valueOf(listItem.getCouleur()));
        mViewHolder.textViewFermentation.setText(listItem.getFermentation());
// mViewHolder.imageView.setImageResource(Integer.valueOf(listItem.getImage()));
        mViewHolder.textViewNote.setText(listItem.getNote());

// nous retournos la vue de l'item demandé
        return convertView;
    }

    // MyViewHolder va nous permettre de ne pas devoir rechercher
// les vues à chaque appel de getView, nous gagnons ainsi en performance
    private class MyViewHolder {
        TextView textViewName, textViewCouleur,textViewFermentation, textViewNote;
// ImageView imageView;
    }

    // nous affichons un Toast à chaque clic sur un item de la liste
// nous récupérons l'objet grâce à sa position
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(context, "Item " + (position + 1) + ": "
                + this.myList.get(position), Toast.LENGTH_SHORT);
        toast.show();

    }

}