package com.mobiletrace.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {
    private final int navy = Color.rgb(16,58,92);
    private final int blue = Color.rgb(23,105,170);
    private final int pale = Color.rgb(239,246,251);
    private LinearLayout listArea;
    private EditText searchBox;
    private final List<Entry> entries = new ArrayList<>();

    static class Entry {
        String brand, model, type, details;
        Entry(String b, String m, String t, String d) {
            brand=b; model=m; type=t; details=d;
        }
    }

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        seed();
        buildUi();
        showEntries("");
    }

    private void seed() {
        entries.add(new Entry("NEXG","N75","Device reference","Add a verified diagram file to view it."));
        entries.add(new Entry("Samsung","Galaxy A12","Charging","Sample catalogue entry — no schematic included."));
        entries.add(new Entry("Xiaomi","Redmi 9A","Charging","Sample catalogue entry — no schematic included."));
        entries.add(new Entry("OPPO","A15","Power","Sample catalogue entry — no schematic included."));
        entries.add(new Entry("Vivo","Y12","Charging","Sample catalogue entry — no schematic included."));
        entries.add(new Entry("Realme","C11","Power","Sample catalogue entry — no schematic included."));
    }

    private void buildUi() {
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setBackgroundColor(pale);

        TextView header = new TextView(this);
        header.setText("MOBILE TRACE\\nPhone Repair Diagram Library");
        header.setTextColor(Color.WHITE);
        header.setTextSize(22);
        header.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        header.setPadding(dp(20), dp(22), dp(20), dp(20));
        header.setBackgroundColor(navy);
        page.addView(header, new LinearLayout.LayoutParams(-1, -2));

        TextView intro = new TextView(this);
        intro.setText("Search a phone model or diagram type");
        intro.setTextColor(navy);
        intro.setTextSize(15);
        intro.setPadding(dp(16), dp(16), dp(16), dp(6));
        page.addView(intro);

        LinearLayout searchRow = new LinearLayout(this);
        searchRow.setPadding(dp(12), dp(4), dp(12), dp(8));
        searchRow.setOrientation(LinearLayout.HORIZONTAL);
        searchBox = new EditText(this);
        searchBox.setSingleLine(true);
        searchBox.setTextSize(16);
        searchBox.setHint("e.g. NEXG N75, Samsung, charging");
        searchBox.setPadding(dp(12), 0, dp(12), 0);
        searchBox.setBackground(round(Color.WHITE, 12, 0));
        searchRow.addView(searchBox, new LinearLayout.LayoutParams(0, dp(52), 1));
        Button search = new Button(this);
        search.setText("Search");
        search.setTextColor(Color.WHITE);
        search.setBackground(round(blue, 12, 0));
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(dp(104), dp(52));
        bp.leftMargin = dp(8);
        searchRow.addView(search, bp);
        page.addView(searchRow);

        LinearLayout actions = new LinearLayout(this);
        actions.setPadding(dp(12), dp(4), dp(12), dp(8));
        Button add = new Button(this);
        add.setText("Add diagram file");
        add.setTextSize(13);
        add.setOnClickListener(v -> pickDiagram());
        actions.addView(add, new LinearLayout.LayoutParams(0, dp(48), 1));
        Button info = new Button(this);
        info.setText("How to use");
        info.setTextSize(13);
        info.setOnClickListener(v -> showHelp());
        LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(0, dp(48), 1);
        ip.leftMargin = dp(8);
        actions.addView(info, ip);
        page.addView(actions);

        TextView section = new TextView(this);
        section.setText("CATALOGUE");
        section.setTextColor(navy);
        section.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        section.setTextSize(13);
        section.setPadding(dp(16), dp(8), dp(16), dp(4));
        page.addView(section);

        ScrollView scroll = new ScrollView(this);
        listArea = new LinearLayout(this);
        listArea.setOrientation(LinearLayout.VERTICAL);
        listArea.setPadding(dp(12), dp(4), dp(12), dp(20));
        scroll.addView(listArea);
        page.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        search.setOnClickListener(v -> showEntries(searchBox.getText().toString()));
        searchBox.setOnEditorActionListener((v, actionId, event) -> {
            showEntries(searchBox.getText().toString());
            return true;
        });

        setContentView(page);
    }

    private void showEntries(String query) {
        listArea.removeAllViews();
        String q = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
        int count = 0;
        for (Entry e : entries) {
            String all = (e.brand+" "+e.model+" "+e.type+" "+e.details).toLowerCase(Locale.ROOT);
            if (q.isEmpty() || all.contains(q)) {
                listArea.addView(entryCard(e));
                count++;
            }
        }
        if (count == 0) {
            TextView empty = new TextView(this);
            empty.setText("No matching entries. Try another model name.");
            empty.setTextSize(15);
            empty.setPadding(dp(16), dp(20), dp(16), dp(20));
            listArea.addView(empty);
        }
    }

    private View entryCard(Entry e) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(14), dp(16), dp(14));
        card.setBackground(round(Color.WHITE, 14, Color.rgb(215,228,238)));
        TextView title = new TextView(this);
        title.setText(e.brand + " " + e.model);
        title.setTextSize(18);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setTextColor(navy);
        card.addView(title);
        TextView type = new TextView(this);
        type.setText("Diagram type: " + e.type);
        type.setTextSize(14);
        type.setTextColor(blue);
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(-1, -2);
        tp.topMargin = dp(5);
        card.addView(type, tp);
        TextView note = new TextView(this);
        note.setText(e.details);
        note.setTextSize(13);
        note.setTextColor(Color.DKGRAY);
        LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(-1, -2);
        np.topMargin = dp(5);
        card.addView(note, np);
        card.setOnClickListener(v -> {
            new android.app.AlertDialog.Builder(this)
                .setTitle(e.brand + " " + e.model)
                .setMessage(e.details + "\\n\\nThis starter app does not contain real schematic or boardview data. Use “Add diagram file” to open a PDF or image you have permission to use.")
                .setPositiveButton("OK", null)
                .show();
        });
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1, -2);
        cp.bottomMargin = dp(10);
        card.setLayoutParams(cp);
        return card;
    }

    private void pickDiagram() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"application/pdf","image/*"});
        try {
            startActivityForResult(intent, 1001);
        } catch (Exception ex) {
            Toast.makeText(this, "File picker could not be opened.", Toast.LENGTH_LONG).show();
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            String mime = getContentResolver().getType(uri);
            if (mime != null && mime.equals("application/pdf")) {
                Intent view = new Intent(Intent.ACTION_VIEW);
                view.setDataAndType(uri, "application/pdf");
                view.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try { startActivity(view); }
                catch (Exception e) { Toast.makeText(this, "Install a PDF viewer to open this file.", Toast.LENGTH_LONG).show(); }
            } else {
                Intent view = new Intent(Intent.ACTION_VIEW);
                view.setDataAndType(uri, "image/*");
                view.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try { startActivity(view); }
                catch (Exception e) { Toast.makeText(this, "No image viewer found.", Toast.LENGTH_LONG).show(); }
            }
        }
    }

    private void showHelp() {
        new android.app.AlertDialog.Builder(this)
            .setTitle("Mobile Trace starter")
            .setMessage("1. Search by brand, model, or diagram type.\\n2. Tap Add diagram file to choose a PDF/image.\\n3. This demo has sample catalogue entries only; it does not include real circuit schematics, boardview files, pin maps, or automatic line tracing. Add verified files you are allowed to use.")
            .setPositiveButton("OK", null)
            .show();
    }

    private GradientDrawable round(int color, int radius, int stroke) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        if (stroke != 0) d.setStroke(dp(1), stroke);
        return d;
    }
    private int dp(int n) { return (int)(n * getResources().getDisplayMetrics().density + 0.5f); }
}
