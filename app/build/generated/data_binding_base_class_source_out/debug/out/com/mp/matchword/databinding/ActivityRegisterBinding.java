// Generated by view binder compiler. Do not edit!
package com.mp.matchword.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mp.matchword.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView alreadyHaveAccount;

  @NonNull
  public final EditText inputConfirmPassword;

  @NonNull
  public final EditText inputEmail;

  @NonNull
  public final EditText inputPassword;

  @NonNull
  public final EditText inputUserName;

  @NonNull
  public final TextView logo;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final Button signInButton;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView alreadyHaveAccount, @NonNull EditText inputConfirmPassword,
      @NonNull EditText inputEmail, @NonNull EditText inputPassword,
      @NonNull EditText inputUserName, @NonNull TextView logo, @NonNull ProgressBar progressBar,
      @NonNull Button signInButton) {
    this.rootView = rootView;
    this.alreadyHaveAccount = alreadyHaveAccount;
    this.inputConfirmPassword = inputConfirmPassword;
    this.inputEmail = inputEmail;
    this.inputPassword = inputPassword;
    this.inputUserName = inputUserName;
    this.logo = logo;
    this.progressBar = progressBar;
    this.signInButton = signInButton;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.alreadyHaveAccount;
      TextView alreadyHaveAccount = ViewBindings.findChildViewById(rootView, id);
      if (alreadyHaveAccount == null) {
        break missingId;
      }

      id = R.id.inputConfirmPassword;
      EditText inputConfirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (inputConfirmPassword == null) {
        break missingId;
      }

      id = R.id.inputEmail;
      EditText inputEmail = ViewBindings.findChildViewById(rootView, id);
      if (inputEmail == null) {
        break missingId;
      }

      id = R.id.inputPassword;
      EditText inputPassword = ViewBindings.findChildViewById(rootView, id);
      if (inputPassword == null) {
        break missingId;
      }

      id = R.id.inputUserName;
      EditText inputUserName = ViewBindings.findChildViewById(rootView, id);
      if (inputUserName == null) {
        break missingId;
      }

      id = R.id.logo;
      TextView logo = ViewBindings.findChildViewById(rootView, id);
      if (logo == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.signInButton;
      Button signInButton = ViewBindings.findChildViewById(rootView, id);
      if (signInButton == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, alreadyHaveAccount,
          inputConfirmPassword, inputEmail, inputPassword, inputUserName, logo, progressBar,
          signInButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
