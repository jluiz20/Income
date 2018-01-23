package br.com.jluiz20.baseproject.model.usecase.version;

import android.util.Log;

import javax.inject.Inject;

import br.com.jluiz20.baseproject.model.repository.version.VersionRepository;
import br.com.jluiz20.baseproject.model.usecase.UseCaseCallback;

/**
 * This use case returns the local app version.
 *
 * @author João Luiz Vieira <vieira.jluiz@gmail.com>.
 */

public class GetAppVersion {

    private static final String TAG = "GetAppVersion";

    private VersionRepository versionRepository;

    @Inject
    public GetAppVersion(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public void execute(UseCaseCallback<String> callback) {
        Log.d(TAG, "execute: ");
        try {
            callback.onSuccess(versionRepository.getAppVersion());
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
