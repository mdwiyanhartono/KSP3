package dwiyanhartono.com.ksp3;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

class NumberTextWatcher implements TextWatcher {
    private final DecimalFormat df;
    private final EditText et;
    private final EditText etPerkiraan;
    private double totalTunggakan;
    private double angsuran;
    private int trailingZeroCount;


    public NumberTextWatcher(String totalTunggakan, EditText totalbayar, String pattern, EditText totalperkiraan, String angsuran) {

        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        df = (DecimalFormat) nf;
        {
            df.applyPattern(pattern);
        }
        df.setDecimalSeparatorAlwaysShown(true);

        this.et = totalbayar;
        this.etPerkiraan = totalperkiraan;

        String t = totalTunggakan.replaceAll(",", "");

        this.totalTunggakan = Double.parseDouble(t);
        this.angsuran = Double.parseDouble(angsuran);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        et.removeTextChangedListener(this);
        if (s != null && !s.toString().isEmpty()) {

            try {
                int inilen = et.getText().length();
                int cp = et.getSelectionStart();

                String originalString = s.toString();

                if (originalString.contains(",")) {
                    originalString = originalString.replaceAll(",", "");
                }

                double totalBayar = Double.parseDouble(originalString);
                et.setText(df.format(totalBayar));

                int endlen = et.getText().length();
                int sel = (cp + (endlen - inilen));


                if (sel > 0 && sel < et.getText().length()) {
                    et.setSelection(sel);
                } else {
                    et.setSelection(et.getText().length() - 3);
                }

                double totalPerkiraan = totalTunggakan - totalBayar;
                double hasilperkiraan = Math.ceil(totalPerkiraan / angsuran);
                double hasilperkiraan2 = Math.ceil(hasilperkiraan * 30);

                String hasil22 = "";
                if (hasilperkiraan2 <= 0) {
                    hasil22 = "Current";
                } else if (hasilperkiraan2 >= 1 && hasilperkiraan2 <= 30) {
                    hasil22 = "X-Days";
                } else if (hasilperkiraan2 >= 31 && hasilperkiraan2 <= 60) {
                    hasil22 = "30+";
                } else if (hasilperkiraan2 >= 61 && hasilperkiraan2 <= 90) {
                    hasil22 = "60+";
                } else if (hasilperkiraan2 >= 91) {
                    hasil22 = "NPL";
                }

                etPerkiraan.setText(hasil22);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        et.addTextChangedListener(this);
    }
}
