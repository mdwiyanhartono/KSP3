package dwiyanhartono.com.ksp3;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import uk.co.barbuzz.beerprogressview.BeerProgressView;

public class MainActivityCekRoot extends AppCompatActivity
        implements CheckRootTask.OnCheckRootFinishedListener {


    private static final String GITHUB_LINK = "https://github.com/scottyab/rootbeer";
    private static final int YOUR_REQUEST_CODE_HERE = 1;
    int UNINSTALL_REQUEST_CODE = 1;

    private AlertDialog infoDialog;
    private BeerProgressView beerView;
    private MainActivityCekRoot mActivity;
    private TextViewFont isRootedText;
    private ArrayList<ImageView> checkRootImageViewList;
    private TextView isRootedTextDisclaimer;
    private FloatingActionButton floatingActionButton;
    private int DELETE_APP = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cek_root);

        initView();


    }

    private void initView() {
        mActivity = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        beerView = (BeerProgressView) findViewById(R.id.loadingRootCheckBeerView);
        isRootedText = (TextViewFont) findViewById(R.id.content_main_is_rooted_text);
        isRootedTextDisclaimer = (TextView) findViewById(R.id.content_mainisRootedTextDisclaimer);

        ImageView rootCheck1ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_1);
        ImageView rootCheck2ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_2);
        ImageView rootCheck3ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_3);
        ImageView rootCheck4ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_4);
        ImageView rootCheck5ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_5);
        ImageView rootCheck6ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_6);
        ImageView rootCheck7ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_7);
        ImageView rootCheck8ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_8);
        ImageView rootCheck9ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_9);
        ImageView rootCheck10ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_10);
        ImageView rootCheck11ImageView = (ImageView) findViewById(R.id.content_main_root_check_image_11);
        checkRootImageViewList = new ArrayList<>();
        checkRootImageViewList.add(rootCheck1ImageView);
        checkRootImageViewList.add(rootCheck2ImageView);
        checkRootImageViewList.add(rootCheck3ImageView);
        checkRootImageViewList.add(rootCheck4ImageView);
        checkRootImageViewList.add(rootCheck5ImageView);
        checkRootImageViewList.add(rootCheck6ImageView);
        checkRootImageViewList.add(rootCheck7ImageView);
        checkRootImageViewList.add(rootCheck8ImageView);
        checkRootImageViewList.add(rootCheck9ImageView);
        checkRootImageViewList.add(rootCheck10ImageView);
        checkRootImageViewList.add(rootCheck11ImageView);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                floatingActionButton.setVisibility(View.GONE);
                resetRootCheckImages();
                CheckRootTask checkRootTask = new CheckRootTask(mActivity, mActivity);
                checkRootTask.execute(true);
            }
        });
    }

    private void resetRootCheckImages() {
        isRootedText.setVisibility(View.GONE);
        for (ImageView imageView : checkRootImageViewList) {
            imageView.setImageDrawable(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_github) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(GITHUB_LINK));
            startActivity(i);
            return true;
        } else if (id == R.id.action_info) {
            showInfoDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showInfoDialog() {
        if (infoDialog != null && infoDialog.isShowing()) {
            //do nothing if already showing
        } else {
            infoDialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage(R.string.info_details)
                    .setCancelable(true)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("More info", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse(GITHUB_LINK)));
                        }
                    })
                    .create();
            infoDialog.show();
        }
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onCheckRootFinished(boolean isRooted) {
        floatingActionButton.setVisibility(View.VISIBLE);
        isRootedText.setText(isRooted ? "ROOTED*" : "NOT ROOTED");
        isRootedTextDisclaimer.setVisibility(isRooted ? View.VISIBLE : View.GONE);
        isRootedText.setTextColor(isRooted ? getResources().getColor(R.color.fail) : getResources().getColor(R.color.pass));
        isRootedText.setVisibility(View.VISIBLE);

        if (isRooted) {
            infoDialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage("Device Not Safe")
                    .setCancelable(true)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
                            intent.setData(Uri.parse("package:" + mActivity.getPackageName()));
                            intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
                            startActivityForResult(intent, UNINSTALL_REQUEST_CODE);
//                            Intent intent = new Intent(Intent.ACTION_DELETE);
//                            intent.setData(Uri.parse("package:" + mActivity.getPackageName()));
//                            startActivity(intent);
                        }
                    })
                    .create();
            infoDialog.show();
        } else {
            infoDialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage("Device Safe From Rooted")
                    .setCancelable(true)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
//                            Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
//                            intent.setData(Uri.parse("package:" + mActivity.getPackageName()));
//                            intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
//                            startActivityForResult(intent, UNINSTALL_REQUEST_CODE);
                            Intent intent = new Intent(MainActivityCekRoot.this, SplashScreen.class);
                            startActivity(intent);

                        }
                    })
                    .create();
            infoDialog.show();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UNINSTALL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("TAG", "onActivityResult: user accepted the (un)install");
            } else if (resultCode == RESULT_CANCELED) {
                Log.d("TAG", "onActivityResult: user canceled the (un)install");
            } else if (resultCode == RESULT_FIRST_USER) {
                Log.d("TAG", "onActivityResult: failed to (un)install");
            }
        }
    }


}
