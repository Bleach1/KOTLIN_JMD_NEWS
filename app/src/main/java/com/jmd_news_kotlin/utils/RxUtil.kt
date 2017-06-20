import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by asus on 2017/6/3.
 */

class RxUtil {

    companion object {
        fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {
            return ObservableTransformer { observable ->
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}
