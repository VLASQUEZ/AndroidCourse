// Generated code from Butter Knife. Do not modify!
package com.example.andresvelasquez.seccion_19_notificaciones;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131165311;

  private View view2131165218;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.et_title = Utils.findRequiredViewAsType(source, R.id.et_title, "field 'et_title'", EditText.class);
    target.et_message = Utils.findRequiredViewAsType(source, R.id.et_message, "field 'et_message'", EditText.class);
    view = Utils.findRequiredView(source, R.id.sw_importance, "field 'sw_importance' and method 'onCheckedChanged'");
    target.sw_importance = Utils.castView(view, R.id.sw_importance, "field 'sw_importance'", Switch.class);
    view2131165311 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onCheckedChanged(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_send, "method 'onClick'");
    view2131165218 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(Utils.castParam(p0, "doClick", 0, "onClick", 0, Button.class));
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.notificationOn = res.getString(R.string.switch_notification_on);
    target.notificationOff = res.getString(R.string.switch_notification_off);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.et_title = null;
    target.et_message = null;
    target.sw_importance = null;

    ((CompoundButton) view2131165311).setOnCheckedChangeListener(null);
    view2131165311 = null;
    view2131165218.setOnClickListener(null);
    view2131165218 = null;
  }
}
