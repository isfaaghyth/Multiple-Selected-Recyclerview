package isfaaghyth.app.multipleselected.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import isfaaghyth.app.multipleselected.R;
import isfaaghyth.app.multipleselected.adapter.TimeAdapter;
import isfaaghyth.app.multipleselected.bean.Time;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lstTime;
    private List<Time> times;

    private Map<String, List<Integer>> collectTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collectTime = new HashMap<>();

        lstTime = findViewById(R.id.lst_time);
        lstTime.setLayoutManager(new LinearLayoutManager(this));

        dataInit();
        listSetup();

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String fullData = "";
                for (Map.Entry<String, List<Integer>> data: collectTime.entrySet()) {
                    fullData = fullData + data.getKey() + ":";
                    for (Integer time: data.getValue()) {
                        fullData = fullData + "," + time;
                    }
                    fullData = fullData + "\n";
                }
                Toast.makeText(getApplicationContext(), fullData, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void dataInit() {
        times = new ArrayList<>();

        List<Integer> senin = new ArrayList<>();
        senin.add(1); senin.add(2); senin.add(3);
        times.add(new Time("Senin", senin));

        List<Integer> selasa = new ArrayList<>();
        selasa.add(4); selasa.add(5); selasa.add(6);
        times.add(new Time("Selasa", selasa));

        List<Integer> rabu = new ArrayList<>();
        rabu.add(7); rabu.add(8); rabu.add(9);
        times.add(new Time("Rabu", rabu));
    }

    private void listSetup() {
        TimeAdapter adapter = new TimeAdapter(this, times);
        adapter.setListener(new TimeListener() {
            @Override public void onTimeClicked(View view, String root, Integer values) {
                if (!inMap(collectTime, root, values)) {
                    List<Integer> time = new ArrayList<>();
                    if (!collectTime.containsKey(root)) {
                        time.add(values);
                        collectTime.put(root, time);
                    } else {
                        time = collectTime.get(root);
                        time.add(values);
                        collectTime.put(root, time);
                    }
                    view.setBackgroundColor(Color.YELLOW);
                } else {
                    collectTime.get(root).remove(values);
                    view.setBackgroundColor(Color.WHITE);
                }
            }
        });
        lstTime.setAdapter(adapter);
    }

    public boolean inMap(Map<String, List<Integer>> map, String key, Integer value) {
        final List<Integer> values = map.get(key);
        return values != null && values.contains(value);
    }
}
