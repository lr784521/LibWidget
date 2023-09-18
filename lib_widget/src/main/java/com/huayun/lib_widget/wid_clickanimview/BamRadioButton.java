package com.huayun.lib_widget.wid_clickanimview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RadioButton;

import androidx.appcompat.widget.AppCompatRadioButton;

/**
 * 自定义 RadioButton 支持点击缩小、松开放大的功能
 * <p>
 * 尘少为大家提供的，
 * 有两种点击效果，
 * <p>
 * 第一种是华丽效果，
 * 第二种是缩放效果，
 * <p>
 * 它与华丽效果的区别在于，
 * 使用默认的华丽效果时，
 * 点击View的上、下、左、右、中，
 * 分别对应的5种不一样的动画：
 * <p>
 * 上 ————> 后翻
 * 下 ————> 前翻
 * 左 ————> 左侧翻
 * 右 ————> 右侧翻
 * 中 ————> 缩放
 * <p>
 * 而缩放效果，
 * 无论点击哪个位置，
 * 都只有缩放的动画。
 * <p>
 * 而想要切换效果也很简单：
 * 先把View转为BamView，
 * 然后调用closeSuperb()即可。
 * <p>
 * 如：
 * ((BamRadioButton)rb_bam).closeSuperb();
 *
 * @author Bamboy
 */
public class BamRadioButton extends AppCompatRadioButton {

    /**
     * 动画模式【true：华丽效果——缩放加方向】【false：只缩放】
     * <p>
     * 华丽效果：
     * 即点击控件的 上、下、左、右、中间时的效果都不一样
     * <p>
     * 普通效果：
     * 即点击控件的任意部位，都只是缩放效果，与 华丽效果模式下 点击控件中间时的动画一样
     **/
    private boolean superb = true;
    /**
     * 是否关闭所有动画开关
     */
    private boolean closeAllAnim = false;
    /**
     * 顶点判断【0：中间】【1：上】【2：右】【3：下】【4：左】
     **/
    private int pivot = 0;

    public BamRadioButton(Context context) {
        super(context);
        this.setClickable(true);
    }

    public BamRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setClickable(true);
    }

    public BamRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setClickable(true);
    }

    /**
     * 打开华丽效果
     */
    public void openSuperb() {
        superb = true;
    }

    /**
     * 关闭华丽效果
     */
    public void closeSuperb() {
        superb = false;
    }

    /**
     * 设置全部动画开关
     *
     * @param closeAllAnim
     */
    public void setCloseAllAnim(boolean closeAllAnim) {
        this.closeAllAnim = closeAllAnim;
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            // 手指按下
            case MotionEvent.ACTION_DOWN:
                if (!closeAllAnim) {
                    pivot = BamAnim.startAnimDown(this, superb, event.getX(), event.getY());
                }
                break;
            // 触摸动作取消
            case MotionEvent.ACTION_CANCEL:
                // 手指抬起
            case MotionEvent.ACTION_UP:
                if (!closeAllAnim) {
                    BamAnim.startAnimUp(this, pivot);
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
