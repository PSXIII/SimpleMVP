package com.kasparpeterson.example;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kasparpeterson.example.databinding.ActivityMainBinding;
import com.kasparpeterson.simplemvp.MVPBaseActivity;

public class MainActivity extends MVPBaseActivity<MainContract.PresenterViewOperations, MainContract.ViewOperations>
        implements MainContract.ViewOperations {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        final String firstName = mBinding.firstNameEditText.getText().toString();
        final String lastName = mBinding.lastNameEditText.getText().toString();

        mBinding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onContinue(firstName, lastName);
            }
        });
    }

    @Override
    protected MainContract.ViewOperations getView() {
        return this;
    }

    @Override
    protected MainContract.PresenterViewOperations createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showFirstNameError() {
        showToast(getString(R.string.show_first_name_error));
    }

    @Override
    public void showLastNameError() {
        showToast(getString(R.string.show_last_name_error));
    }

    @Override
    public void showSuccess() {
        showToast(getString(R.string.show_success));
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
