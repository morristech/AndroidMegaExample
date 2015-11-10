package custom_view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

/**
 * This is a child of TextView. When a new number is set to RollingNumberTextView, it will display the new number using a rolling animation
 */
public class RollingNumberTextView extends FontTextView {

    private final int DEFAULT_ROLL_NUMBER = 10;
    private final int DEFAULT_ANIM_DURATION = 70;
    /**
     * This is the number of digits that will be animated through before reaching the final number
     */
    private int mRollNumber = DEFAULT_ROLL_NUMBER;

    private int mAnimDuration = DEFAULT_ANIM_DURATION;
    Handler mHandler = new Handler();
    int mCounter;

    public RollingNumberTextView(Context context) {
        super(context);
    }

    public RollingNumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RollingNumberTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RollingNumberTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setTextWithRollAnim(int newInt) {
        int oldInt = Integer.parseInt(getText().toString());
        int diff = newInt - oldInt;
        int chunk = diff / mRollNumber;
//        Log.d("RollingNumberTextView", "chunk: " + chunk);
//        Log.d("RollingNumberTextView", newInt + " - " + oldInt + " = " + diff);
        final int[] tempArray = new int[mRollNumber];
        for (int i = 0; i < mRollNumber; i++) {

            if (i == mRollNumber - 1) {
                tempArray[i] = newInt;
            } else {
                tempArray[i] = oldInt + (i + 1) * chunk;
            }
//            Log.d("RollingNumberTextView", "tempArray[" + i + "]: " + tempArray[i]);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub

                for (mCounter = 0; mCounter < mRollNumber; ) {
                    try {

                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
//                                Log.d("RollingNumberTextView", "mCounter/mRollNumber: " + mCounter + "/" + mRollNumber);
                                if (mCounter < tempArray.length) {
                                    setText(Integer.toString(tempArray[mCounter]));
                                }
                                mCounter++;
                            }
                        });
                        Thread.sleep(mAnimDuration);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();

    }

    public int getRollNumber() {
        return mRollNumber;
    }

    public void setRollNumber(int rollNumber) {
        if (rollNumber > 0) {
            mRollNumber = rollNumber;
        } else {
            mRollNumber = DEFAULT_ROLL_NUMBER;
        }
    }

    public int getAnimDuration() {
        return mAnimDuration;
    }

    public void setAnimDuration(int animDuration) {
        mAnimDuration = animDuration;
    }

}
