package xin.com.funtrek.other;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import xin.com.funtrek.R;

/**
 * @author ddy
 */
public class MyAnimation {
    public static void add(final ImageView ima1, final ImageView ima2, final ImageView ima3, final ImageView ima4) {

        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        ima1.measure(w, h);
        ima2.measure(w, h);
        ima3.measure(w, h);

        int width = ima1.getMeasuredWidth();
        int width1 = ima2.getMeasuredWidth();
        int width2 = ima3.getMeasuredWidth();

        //-----伸出时的动画
        final ObjectAnimator animator = ObjectAnimator.ofFloat(ima1, "rotation", 0f, 180f);
        final ObjectAnimator animator11 = ObjectAnimator.ofFloat(ima2, "alpha", 0, 1);
        final ObjectAnimator animator1 = ObjectAnimator.ofFloat(ima2, "translationX", 0f, -width);
        final ObjectAnimator animator21 = ObjectAnimator.ofFloat(ima3, "alpha", 0, 1);
        final ObjectAnimator animator2 = ObjectAnimator.ofFloat(ima3, "translationX", 0f, -(width + width1 - 5));
        final ObjectAnimator animator31 = ObjectAnimator.ofFloat(ima4, "alpha", 0, 1);
        final ObjectAnimator animator3 = ObjectAnimator.ofFloat(ima4, "translationX", 0f, -(width + width1 + width2 - 10));

        //----缩回时的动画
        final ObjectAnimator fanimator = ObjectAnimator.ofFloat(ima1, "rotation", 0f, -180f);
        final ObjectAnimator fanimator11 = ObjectAnimator.ofFloat(ima2, "alpha", 1, 0);
        final ObjectAnimator fanimator1 = ObjectAnimator.ofFloat(ima2, "translationX", -width, 0f);
        final ObjectAnimator fanimator21 = ObjectAnimator.ofFloat(ima3, "alpha", 1, 0);
        final ObjectAnimator fanimator2 = ObjectAnimator.ofFloat(ima3, "translationX", -(width + width1 - 5), 0f);
        final ObjectAnimator fanimator31 = ObjectAnimator.ofFloat(ima4, "alpha", 1, 0);
        final ObjectAnimator fanimator3 = ObjectAnimator.ofFloat(ima4, "translationX", -(width + width1 + width2 - 10), 0f);

//给伸出动画设置监听
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ima1.setImageResource(R.drawable.icon_packup);//动画结束改变图片
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        //给缩回动画设置监听
        fanimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ima1.setImageResource(R.drawable.icon_open);//改变图片
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        final int[] a = {0};
        ima1.setOnClickListener(new View.OnClickListener() {//图片的点击事件
            @Override
            public void onClick(View view) {
                a[0]++;
                if (a[0] % 2 == 1) {//第一次点击是实现伸出效果
                    AnimatorSet animSet = new AnimatorSet();//动画集合
                    animSet.play(animator).with(animator11).with(animator1).with(animator21).with(animator2).with(animator31).with(animator3);
                    animSet.setDuration(500);
                    animSet.start();

                } else {//再点击一次实现缩回效果
                    AnimatorSet animSet1 = new AnimatorSet();//动画集合
                    animSet1.play(fanimator).with(fanimator11).with(fanimator1).with(fanimator21).with(fanimator2).with(fanimator31).with(fanimator3);
                    animSet1.setDuration(500);
                    animSet1.start();
                }
            }
        });
    }
}
