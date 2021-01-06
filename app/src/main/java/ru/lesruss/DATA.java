package ru.lesruss;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import ru.lesruss.Class.CatalogClass;
import ru.lesruss.Class.ItemModel;
import ru.lesruss.Class.Price;
import ru.lesruss.Class.ScreenItem;

public class DATA {
    public static ArrayList<ItemModel>  getMain(Context context){

        ArrayList<ItemModel> list=new ArrayList<>();
        for (CatalogClass catalog:getAllCatalog(context)){
            if (catalog.getFrom().equals("")){
                list.add(new ItemModel(catalog.getName(),catalog.getBackground(),new Intent(context,CatalogActivity.class).putExtra("nextPage",catalog.getTo())));
            }
        }
        list.add(new ItemModel("О компании", null,new Intent(context,AboutActivity.class)));

        return list;
    }
    public static ArrayList<ItemModel>  getCatalog(Context context,String page){

        ArrayList<ItemModel> list=new ArrayList<>();
        for (CatalogClass catalog:getAllCatalog(context)){
            if (catalog.getFrom().equals(page)){
                list.add(new ItemModel(catalog.getName(),catalog.getBackground(),new Intent(context,CatalogActivity.class).putExtra("nextPage",catalog.getTo())));
            }
        }
        for (Price price:getAllPriceList()){
            if (price.getPage().equals(page)){
                list.add(new ItemModel(price.getName(),null,new Intent(context,BuyActivity.class)));
            }
        }

        return list;
    }
    private static ArrayList<CatalogClass> getAllCatalog(Context context){
        ArrayList<CatalogClass> list=new ArrayList<>();
        //лавное меню
        list.add(new CatalogClass(0,"","vse-dlya-bani-i-sauny","Все для бани и сауны","vse-dlya-bani-i-sauny","Значение бани в нашей жизни переоценить достаточно сложно. Баня - это не просто помещение для принятия гигиенических процедур, но и, прежде всего, здравница и место для душевных встреч. И как не назови ее: баня, сауна, хамам, - суть не изменится.",null));
        list.add(new CatalogClass(1,"","pogonazhnye-izdeliya","Погонажные изделия","pogonazhnye-izdeliya","",null));
        list.add(new CatalogClass(2,"","pilomaterialy","Пиломатериалы","pilomaterialy","",null));
        //все для бани
        list.add(new CatalogClass(3,"vse-dlya-bani-i-sauny","polok","Полок","polok","Полок в парилке позволяет с комфортом пользоваться баней или сауной. Обладая приятным древесным ароматом и низкой теплопроводностью, он идеально подходит для использования в парилках. Главное требование к дереву заключается в том, что оно не должно выделять смол, иначе человек может сильно обжечься. Поэтому хвойная древесина для таких целей не подходит, хотя, конечно, весьма полезна для здоровья. Хорошим выбором станет липа, осина, ольха или абаш.  Наша компания предлагает широкий выбор полок для сауны из натурального дерева, отвечающего всем современным стандартам качества.\n" +
                "\n",null));
        list.add(new CatalogClass(4,"vse-dlya-bani-i-sauny","vagonka-dlya-bani","Вагонка для бани и сауны","vagonka-dlya-bani","",null));

        //полок

        list.add(new CatalogClass(5,"polok","polok-abash","Полок абаш","polok-abash","Абаш – лиственное дерево, которое произрастает в Африке. Основным поставщиком древесины считается Гана. На данный момент он имеет огромный спрос на строительном рынке России, хотя стал известен совсем недавно. Его уникальные свойства идеально подходят для отделки помещений, в которых повышена влажность и царят высокие температуры. Спустя долгие годы эксплуатации абаш не деформируется и не растрескается, а также сохранит свой красивый белоснежный цвет с кремовым оттенком. Поэтому полок из абаша считается самым лучшим и долговечным.",null));
        list.add(new CatalogClass(6,"polok","polok-termoabash","Полок термо абаш","polok-termoabash","Абаш – лиственное дерево, которое произрастает в Африке. Основным поставщиком древесины считается Гана. На данный момент он имеет огромный спрос на строительном рынке России, хотя стал известен совсем недавно. Его уникальные свойства идеально подходят для отделки помещений, в которых повышена влажность и царят высокие температуры. Спустя долгие годы эксплуатации абаш не деформируется и не растрескается, а также сохранит свой красивый белоснежный цвет с кремовым оттенком. Поэтому полок из абаша считается самым лучшим и долговечным.",null));

        return list;
    }
    private static List<Price> getAllPriceList(){
        List<Price> list=new ArrayList<>();
        //плок абаш
        list.add(new Price("94 х 26\nАзиатский","Длина доски в метрах\n2,0 - 2,5","Цена за погонный метр, рубли \nКласс экстра 270 руб","polok-abash" ));
        return list;
    }
    public static CatalogClass getItemCatalog(Context context,String page){
        for (CatalogClass catalog:getAllCatalog(context)){
            if (catalog.getUrl().equals(page)){
                return catalog;
            }
        }
        return null;
    }


}
