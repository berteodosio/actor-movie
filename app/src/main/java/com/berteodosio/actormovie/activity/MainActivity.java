package com.berteodosio.actormovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.berteodosio.actormovie.R;
import com.berteodosio.actormovie.activity.base.BaseActivity;
import com.berteodosio.actormovie.animation.ViewAnimation;
import com.berteodosio.actormovie.model.Actor;
import com.berteodosio.actormovie.presenter.MainPresenter;
import com.berteodosio.actormovie.validator.Validator;
import com.berteodosio.actormovie.validator.general.EditTextValidator;
import com.berteodosio.actormovie.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    private MainPresenter mPresenter;

    private EditText mFirstActorName;
    private LinearLayout mLayout;
    private Button mShow;
    private FloatingActionButton mAdd;

    private List<EditText> mActorEditTextList = new ArrayList<>();
    private List<Integer> mActorIds = new ArrayList<>(); // preenchido conforme as buscas ocorrem
    private List<String> mActorNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mPresenter = new MainPresenter(this);
        initComponents();
    }

    private void initComponents() {
        mFirstActorName = (EditText) findViewById(R.id.main_activity_actorName);
        mShow = (Button) findViewById(R.id.main_activity_showMovies);
        mAdd = (FloatingActionButton) findViewById(R.id.main_activity_addActor);
        mLayout = (LinearLayout) findViewById(R.id.main_activity_editTextLayout);

        initListeners();

        mActorEditTextList.add(mFirstActorName);
        initEditTextListener(mFirstActorName);
    }

    private void initEditTextListener(EditText editText) {
        editText.addTextChangedListener(new EditTextValidator(editText) {
            @Override
            public void validate(EditText editText) {
                Validator.validateName(editText);
            }
        });
    }

    private void initListeners() {
        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowClick();
            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClick();
            }
        });
    }

    private void onAddClick() {
        ViewAnimation.doLittleBigAnimation(mAdd);
        AppCompatEditText editText = new AppCompatEditText(this);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        mLayout.addView(editText, layoutParams);
        mActorEditTextList.add(editText);
    }

    private void onShowClick() {
        for (EditText editText : mActorEditTextList) {
            if (editText.getError() != null || editText.getText().toString().equals("")) {
                Snackbar.make(editText, "Preencha todos os campos corretamente", Snackbar.LENGTH_SHORT)
                        .show();
                return;
            }
        }

        showLoading();
        ViewAnimation.doContractHorizontalAnimation(mShow);

        for (EditText editText : mActorEditTextList) {
            // TODO: fazer verificação aqui
//            if (editText.getError() == null && !editText.getText().toString().equals(""))
            mPresenter.getActorId(editText.getText().toString());
        }
    }

    @Override
    public void displayActorInfo(Actor actor) {
        mActorIds.add(actor.id());
        mActorNames.add(actor.name());

        // já carregou os ids de todos os atores
        if (mActorIds.size() == mActorEditTextList.size()) {
            hideLoading();
            Intent intent = new Intent(this, MovieListActivity.class);
            intent.putExtra(MovieListActivity.EXTRA_ACTOR_ID_LIST, (ArrayList) mActorIds);
            intent.putExtra(MovieListActivity.EXTRA_ACTOR_NAME_LIST, (ArrayList) mActorNames);
            startActivityForResult(intent, MovieListActivity.REQUEST_CODE);
        }
    }

    @Override
    public void displayNoActorsFound() {
        // TODO: arrumar displayNoActorsFound() e seu uso
        Snackbar.make(mShow, "Nenhum ator encontrado", Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.main_menu_remove) {
            int childCount = mLayout.getChildCount();
            if (childCount > 1) {
                mLayout.removeViewAt(childCount - 1);
                mActorEditTextList.remove(mActorEditTextList.size() - 1);
            }
            else {
                Snackbar.make(mShow, "Pelo menos um ator deve ser mantido", Snackbar.LENGTH_SHORT)
                        .show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        recreate();
    }
}
