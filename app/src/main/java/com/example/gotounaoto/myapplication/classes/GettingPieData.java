package com.example.gotounaoto.myapplication.classes;

import android.graphics.Color;

import com.example.gotounaoto.myapplication.ExtendSugar.Word;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gotounaoto on 2018/01/30.
 */

public class GettingPieData {

    public static PieData gettingPieData1(){
        //piedata1を返す
        //ここからはセットするデータを引き出してセットする
        List<Word> word_resource = Word.listAll(Word.class);
        int number_all = 0;
        int number_zero_twenty = 0;
        int number_twenty_forty = 0;
        int number_forty_sixty = 0;
        int number_sixty_eighty = 0;
        int number_eighty_hundred = 0;
        //全て最初の以上を後の未満にする
        //最後は100も含めるので特例
        for (Word item : word_resource) {
            float proportion = item.getProportion();
            if (proportion >= 0.0 & proportion < 20.0) {
                number_zero_twenty++;
            } else if (proportion >= 20.0 & proportion < 40.0) {
                number_twenty_forty++;
            } else if (proportion >= 40.0 & proportion < 60.0) {
                number_forty_sixty++;
            } else if (proportion >= 60.0 & proportion < 80.0) {
                number_sixty_eighty++;
            } else if (proportion >= 80.0 & proportion <= 100.0) {
                number_eighty_hundred++;
            }
            number_all++;
        }
        if(number_all == 0){
            return null;
        }
        float percentage_zero_twenty = number_zero_twenty / number_all;
        float percentage_twenty_forty = number_twenty_forty / number_all;
        float percentage_forty_sixty = number_forty_sixty / number_all;
        float percentage_sixty_eighty = number_sixty_eighty / number_all;
        float percentage_eighty_hundred = number_eighty_hundred / number_all;
        List<Float> values = Arrays.asList(percentage_zero_twenty, percentage_twenty_forty, percentage_forty_sixty,
                percentage_sixty_eighty, percentage_eighty_hundred);
        List<PieEntry> entries = new ArrayList<>();
        List<String> labels = Arrays.asList("0~20%", "20~40%", "40~60%", "60~80%", "80~100%");
        for (int i = 0; i < 5; i++) {
            entries.add(new PieEntry(values.get(i), labels.get(i)));
        }
        PieDataSet pieDataSet = new PieDataSet(entries, "正答率");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setDrawValues(true);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.WHITE);
        return pieData;
    }
}
