package com.articlegure.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.articlegure.Activity.MainActivity;
import com.articlegure.Model.ArticleData;
import com.articlegure.Helper.CenterRepository;
import com.articlegure.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 04/03/18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    Context context;
    private List<ArticleData> itemList=new ArrayList<ArticleData>();;
    public DataAdapter(Context context,List<ArticleData> itemList) {
        this.context = context;
        this.itemList=itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }
    private MainActivity getcontext(){
        return (MainActivity) context;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ArticleData tempObj = this.itemList.get(position);
        holder.loadDetails(tempObj.getDescription(),tempObj.getLikes());
        holder.loadImage(tempObj.getImgURL());
        if(CenterRepository.getCenterRepository().getIDOfProductsInShoppingList().contains(tempObj.getId())){
            int index=CenterRepository.getCenterRepository().getIDOfProductsInShoppingList().indexOf(tempObj.getId());
            holder.count.setText(CenterRepository.getCenterRepository().getListOfProductsInShoppingList().get(index).getLikes());
        }
        holder.count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if current object is lready in shopping list
                if (CenterRepository.getCenterRepository()
                        .getIDOfProductsInShoppingList().contains(tempObj.getId())) {


                    //get position of current item in shopping list
                    int indexOfTempInShopingList = CenterRepository.getCenterRepository()
                            .getIDOfProductsInShoppingList()
                            .indexOf(tempObj.getId());

                    // increase quantity of current item in shopping list
                    if (Integer.parseInt(CenterRepository.getCenterRepository().getListOfProductsInShoppingList().get(indexOfTempInShopingList).getLikes()) == 0) {

                        ((MainActivity)getcontext())
                                .updateItemCount(true);

                    }
                    if (Integer.parseInt(CenterRepository.getCenterRepository().getListOfProductsInShoppingList().get(indexOfTempInShopingList).getLikes()) > 0) {




                        // update quanity in shopping list
                        CenterRepository
                                .getCenterRepository()
                                .getListOfProductsInShoppingList()
                                .get(indexOfTempInShopingList)
                                .setLikes(
                                        String.valueOf(Integer
                                                .valueOf(CenterRepository.getCenterRepository().getListOfProductsInShoppingList().get(indexOfTempInShopingList).getLikes()) + 1));


                        holder.count.setText(CenterRepository.getCenterRepository().getListOfProductsInShoppingList().get(indexOfTempInShopingList).getLikes());


                    }



                } else {

                    ((MainActivity) getcontext()).updateItemCount(true);

                    tempObj.setLikes(String.valueOf(1));

                    holder.count.setText(tempObj.getLikes());

                    CenterRepository.getCenterRepository()
                            .getListOfProductsInShoppingList().add(tempObj);




                }





            }

        });
        }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView count,title;
        private ImageView thumbnail;
        public ViewHolder(View view) {
            super(view);

            thumbnail=(ImageView)view.findViewById(R.id.thumbnail);
            title=(TextView)view.findViewById(R.id.title);
            count=(TextView) view.findViewById(R.id.count);

        }
        public void loadDetails(String name, String like) {
            title.setText(name);
            count.setText(like);
        }
        public void loadImage(String imageUrl) {
            Glide.with(context).load(imageUrl).asBitmap()
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(thumbnail);
        }

    }
}