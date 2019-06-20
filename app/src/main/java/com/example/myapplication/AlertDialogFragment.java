package com.example.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialogFragment extends DialogFragment {

    View content;

    /*
    public static AlertDialogFragment newInstance(String param)
    {
        AlertDialogFragment instance = new AlertDialogFragment();

        // ダイアログに渡すパラメータはBundleにまとめる
        Bundle arguments = new Bundle();
        arguments.putString("parameter", param);

        instance.setArguments(arguments);
        return instance;
    }
    */

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // タイトル
        dialog.setTitle("My Custom Dialog");
        // ダイアログ外タップで消えるように設定
        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle b)
    {
        content = i.inflate(R.layout.activity_dialog, null);

        // ボタンを取得して、ClickListenerをセット
        Button btn = (Button)content.findViewById(R.id.button1);
        btn.setOnClickListener(onClick_button);
        TextView text = (TextView)content.findViewById(R.id.textView);
        text.setText("Sample");
        return content;
    }

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_MyDialog);
    }

    AlertDialogFragment instance = this ;

    private View.OnClickListener onClick_button = new View.OnClickListener(){
        @Override
        public void onClick(View content) {
            System.out.println("閉じる");
            instance.dismiss();
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        getFragmentManager().beginTransaction().remove(this).commit();
        this.getDialog().dismiss();
    }
}
