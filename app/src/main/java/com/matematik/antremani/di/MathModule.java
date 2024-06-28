package com.matematik.antremani.di;

import android.content.Context;
import android.content.SharedPreferences;
import com.matematik.antremani.repository.MathRepository;
import com.matematik.antremani.repository.MathRepositoryImpl;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class MathModule {

    @Singleton
    @Provides
    public static SharedPreferences injectSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences("com.matematik.antremani", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public static MathRepositoryImpl repositoryImplProvider(SharedPreferences sharedPreferences) { return new MathRepository(sharedPreferences); }

}
