package ru.lesruss;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

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
        list.add(new ItemModel("О компании", context.getDrawable(R.drawable.logo),new Intent(context,AboutActivity.class)));
        list.add(new ItemModel("Корзина", null,new Intent(context,ChartActivity.class)));

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
                list.add(new ItemModel(price.getName(),null,new Intent(context,ProductActivity.class).putExtra("id",price.getId())));
            }
        }

        return list;
    }
    private static ArrayList<CatalogClass> getAllCatalog(Context context){
        ArrayList<CatalogClass> list=new ArrayList<>();
        //лавное меню
        list.add(new CatalogClass(0,"","vse-dlya-bani-i-sauny","Все для бани и сауны","vse-dlya-bani-i-sauny","Значение бани в нашей жизни переоценить достаточно сложно. Баня - это не просто помещение для принятия гигиенических процедур, но и, прежде всего, здравница и место для душевных встреч. И как не назови ее: баня, сауна, хамам, - суть не изменится.", context.getDrawable(R.drawable.bany320)));
        list.add(new CatalogClass(1,"","pogonazhnye-izdeliya","Погонажные изделия","pogonazhnye-izdeliya","Погонаж обрабатывают на специальных профилирующих станках. Сырье проходит три основных стадии: сушка древесины в специальных сушильных боксах, последующая калибровка и подгон заготовок под определенный размер (на некоторых фабриках калибровка до сих пор проводится вручную) и, наконец, сортировка готовой продукции. При видимой простоте, производство погонажных изделий – довольно трудоемкий процесс, который требует сочетания высококачественных материалов и современного оборудования, а также высокого профессионализма мастеров.\n" +
                "\n",context.getDrawable(R.drawable.sosna400)));
        list.add(new CatalogClass(2,"","pilomaterialy","Пиломатериалы","pilomaterialy","",context.getDrawable(R.drawable.pilomaterials)));
        //все для бани
        list.add(new CatalogClass(3,"vse-dlya-bani-i-sauny","polok","Полок","polok","Полок в парилке позволяет с комфортом пользоваться баней или сауной. Обладая приятным древесным ароматом и низкой теплопроводностью, он идеально подходит для использования в парилках. Главное требование к дереву заключается в том, что оно не должно выделять смол, иначе человек может сильно обжечься. Поэтому хвойная древесина для таких целей не подходит, хотя, конечно, весьма полезна для здоровья. Хорошим выбором станет липа, осина, ольха или абаш.  Наша компания предлагает широкий выбор полок для сауны из натурального дерева, отвечающего всем современным стандартам качества.\n" +
                "\n",context.getDrawable(R.drawable.polok_abash)));
        list.add(new CatalogClass(4,"vse-dlya-bani-i-sauny","vagonka-dlya-bani","Вагонка для бани и сауны","vagonka-dlya-bani","",context.getDrawable(R.drawable.vagonka)));

        //Погонажные изделия
        list.add(new CatalogClass(5,"pogonazhnye-izdeliya","nalichniki-derevyannye","Наличники деревянные","nalichniki-derevyannye","Деревянные наличники сделаны из натуральных материалов, и потому являются экологически чистыми изделиями, которые можно применять как для домашнего, так и для офисного дизайна. Высококачественное дерево, обработанное в соответствии с самыми современными технологиями, отвечает всем стандартам европейского и российского качества.\n" +
                "\n",context.getDrawable(R.drawable.nalichniki_derevyannye)));

        //полок
        list.add(new CatalogClass(6,"polok","polok-abash","Полок абаш","polok-abash","Абаш – лиственное дерево, которое произрастает в Африке. Основным поставщиком древесины считается Гана. На данный момент он имеет огромный спрос на строительном рынке России, хотя стал известен совсем недавно. Его уникальные свойства идеально подходят для отделки помещений, в которых повышена влажность и царят высокие температуры. Спустя долгие годы эксплуатации абаш не деформируется и не растрескается, а также сохранит свой красивый белоснежный цвет с кремовым оттенком. Поэтому полок из абаша считается самым лучшим и долговечным.",context.getDrawable(R.drawable.polok_abash)));
        list.add(new CatalogClass(7,"polok","polok-termoabash","Полок термо абаш","polok-termoabash","Абаш – лиственное дерево, которое произрастает в Африке. Основным поставщиком древесины считается Гана. На данный момент он имеет огромный спрос на строительном рынке России, хотя стал известен совсем недавно. Его уникальные свойства идеально подходят для отделки помещений, в которых повышена влажность и царят высокие температуры. Спустя долгие годы эксплуатации абаш не деформируется и не растрескается, а также сохранит свой красивый белоснежный цвет с кремовым оттенком. Поэтому полок из абаша считается самым лучшим и долговечным.",context.getDrawable(R.drawable.termoobash)));


        //Вагонка для бани и сауны
        list.add(new CatalogClass(8,"vagonka-dlya-bani","vagonka-lipa","Вагонка липа","vagonka-lipa","Вагонка липа идеально подходит для внутренней отделки помещений, а также для обшивки стен бань и саун. Она обладает приятным сладковатым ароматом, а благодаря большому количеству антиоксидантов, содержащихся в древесине, благотворно влияет на здоровье человека. Вот почему она так ценится  любителями русской и финской бань.\n" +
                "\n",context.getDrawable(R.drawable.vagonko_osina)));
        //
        list.add(new CatalogClass(9,"pilomaterialy","doska-strogannaya-sosna","Доска строганная","doska-strogannaya-sosna","Казалось бы, доски строганные все одинаковы. Но это не так – сейчас активно применяются различные технологии в изготовлении строительных материалов, поэтому появились не только обычный доски естественной влажности, сушащиеся в обычных условиях, но и сухая строганная доска, высушенная искусственно, с помощью камерной сушки.\n",context.getDrawable(R.drawable.nalichniki_derevyannye)));

        return list;
    }
    private static List<Price> getAllPriceList(){
        List<Price> list=new ArrayList<>();
        //плок абаш
        list.add(new Price(1,"94 х 26\nАзиатский","Длина доски в метрах\n2,0 - 2,5",270,"polok-abash","Цена за погонный метр, рубли","Потолог абаш"));
        list.add(new Price(2,"94 х 26\nАфриканский","Длина доски в метрах\n1,85 м; 2,05 м; 2,15 м; 2,25 м; 2,35 м; 2,45 м;\n" ,445,"polok-abash","Цена за погонный метр, рубли","Потолог абаш"));
        list.add(new Price(3,"140 х 26\nАфриканский","Длина доски в метрах\n 2,15 м;\n" ,920,"polok-abash","Цена за погонный метр, рубли","Потолог абаш"));

        //Наличники деревянные
        list.add(new Price(4,"Наличник липа","Ширина наличника 70 мм\nДлинна доски мерт погонный: 2-2,5м" ,86,"nalichniki-derevyannye","Цена за погонный метр, рубли","Наличник липа"));
        list.add(new Price(5,"Наличник Термо липа","Ширина наличника 70 мм\nДлинна доски мерт погонный: 1-3м" ,139,"nalichniki-derevyannye","Цена за погонный метр, рубли","Наличник термо липа"));
        list.add(new Price(6,"Наличник Термо ольха","Ширина наличника 70 мм\nДлинна доски мерт погонный: 2-2,5м" ,150,"nalichniki-derevyannye","Цена за погонный метр, рубли","Наличник Термо ольха"));
        list.add(new Price(7,"Наличник канадский кедр","Ширина наличника 60 мм\nДлинна доски мерт погонный: 2,13м" ,489,"nalichniki-derevyannye","Цена за погонный метр, рубли","Наличник канадский кедр"));

        //вагонка липа
        list.add(new Price(8,"96мм х 16мм \nЙошкар-Ола\nКласс «Экстра»","Длинна доски метрах: 0.5 - 0.9м" ,520,"vagonka-lipa","Цена за метр квадратный , рубли","Вагонка липа"));
        list.add(new Price(9,"66мм х 16 мм (штиль)\nКласс «Экстра»","Длинна доски метрах: 2.0 - 3.0м" ,1300,"vagonka-lipa","Цена за метр квадратный , рубли","Вагонка липа"));

        //термообаш
        list.add(new Price(10,"   94 х 26\n(Азиатский)","1,8 м; 2,0 м; 2,1 м; 2,2 м; 2,3 м; 2,4 м; 2,5 м " ,380,"polok-termoabash","Цена за погонный метр, рубли","Потолог термоабаш"));
        //пиломатериалы
        list.add(new Price(11,"Доска строганная 20 х 100 х 3000 мм\n" ,"     м /п\n",49,"doska-strogannaya-sosna","Цена за погонный метр, рубли","Доска строганная"));

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

    public static Price getPriceById(int id){
        for (Price price:getAllPriceList()){
            if (price.getId()==id){
                return price;
            }
        }
        return null;
    }


}
