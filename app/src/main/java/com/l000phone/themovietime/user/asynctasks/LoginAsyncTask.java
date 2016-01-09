package com.l000phone.themovietime.user.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.l000phone.themovietime.user.bean.LoginBean;
import com.l000phone.themovietime.user.bean.ParserLogin;
import com.l000phone.themovietime.utils.HttpUtil;

/**
 * 登录异步任务
 *
 *  
 */
public class LoginAsyncTask extends AsyncTask<String,Void,LoginBean> {

    private Context context;
    private LoginCallback callback;

    public LoginAsyncTask(Context context, LoginCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected LoginBean doInBackground(String... params) {
        String json = new String(HttpUtil.postDownload(params[0],params[1]));
        LoginBean bean = ParserLogin.getLoginBean(json);
        return bean;
    }

    @Override
    protected void onPostExecute(LoginBean bean) {
        super.onPostExecute(bean);
        callback.getLogin(bean);
    }

    public interface LoginCallback{
        public void getLogin(LoginBean bean);
    }

}
