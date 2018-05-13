package com.articlegure.Helper;


import com.articlegure.Model.ArticleData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CenterRepository {

    private static CenterRepository centerRepository;

    private ConcurrentHashMap<String, ArrayList<ArticleData>> mapOfProductsInCategory = new ConcurrentHashMap<String, ArrayList<ArticleData>>();
    private List<ArticleData> listOfProductsInShoppingList = Collections.synchronizedList(new ArrayList<ArticleData>());

    public static CenterRepository getCenterRepository() {

        if (null == centerRepository) {
            centerRepository = new CenterRepository();
        }
        return centerRepository;
    }


    public List<ArticleData> getListOfProductsInShoppingList() {
        return listOfProductsInShoppingList;
    }
    public List<String> getIDOfProductsInShoppingList() {
        List<String> list=new ArrayList<>();
        for(int i=0;i<listOfProductsInShoppingList.size();i++)
            list.add(listOfProductsInShoppingList.get(i).getId());
        return list;
    }

}
